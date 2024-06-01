package ru.karyeragame.paymentsystem.payment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.karyeragame.paymentsystem.account.model.Account;
import ru.karyeragame.paymentsystem.account.repository.AccountRepository;
import ru.karyeragame.paymentsystem.createdGames.dto.CreatedGameDto;
import ru.karyeragame.paymentsystem.createdGames.service.CreatedGameService;
import ru.karyeragame.paymentsystem.exceptions.IncorrectData;
import ru.karyeragame.paymentsystem.payment.dto.PaymentDto;
import ru.karyeragame.paymentsystem.payment.mapper.PaymentMapper;
import ru.karyeragame.paymentsystem.payment.model.Payment;
import ru.karyeragame.paymentsystem.payment.repository.PaymentRepository;
import ru.karyeragame.paymentsystem.user.model.User;
import ru.karyeragame.paymentsystem.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final AccountRepository accountRepository;
    private final CreatedGameService createdGameService;

    @Override
    @Transactional
    public String payment(PaymentDto paymentDto, Long userFromId) {
        if (paymentDto.getFromAccount().equals(paymentDto.getToAccount())) {
            throw new IncorrectData("Указан одинаковый счёт списания и получения.");
        }
        User userFrom = userRepository.getById(userFromId);
        Account accountFrom = accountRepository.getById(userFrom.getId());

        if (accountFrom.getIsLocked()) {
            throw new IncorrectData("Ваш счет заблокирован");
        }

        if (!accountFrom.getId().equals(paymentDto.getFromAccount())) {
            throw new IncorrectData("Что-то пошло не так. Счёт списания не принадлежит пользователю.");
        }

        if (paymentDto.getAmount().compareTo(accountFrom.getAmount()) > 0) {
            throw new IncorrectData("На вашем счёте не достаточно средств.");
        }

        Account accountTo = accountRepository.getById(paymentDto.getToAccount());
        if (accountTo.getIsLocked()) {
            throw new IncorrectData("Счет получателя заблокирован");
        }

        User userTo = userRepository.getById(accountTo.getUserId());

        CreatedGameDto createdGameDtoFrom = createdGameService.getCreatedGameByUserIdWhereGameIsAlive(userFrom.getId());
        CreatedGameDto createdGameDtoTo = createdGameService.getCreatedGameByUserIdWhereGameIsAlive(userTo.getId());

        if (!createdGameDtoFrom.getGameId().equals(createdGameDtoTo.getGameId())) {
            throw new IncorrectData("Что-то пошло не так. Получатель в другой Игре.");
        }

        accountRepository.transfer(accountFrom.getId(), accountTo.getId(), paymentDto.getAmount());

        Payment payment = Payment.builder()
                .gameId(createdGameDtoFrom.getGameId())
                .fromAccount(accountFrom.getId())
                .toAccount(accountTo.getId())
                .amount(paymentDto.getAmount())
                .description(paymentDto.getDescription())
                .created(LocalDateTime.now())
                .build();
        paymentRepository.create(payment);

        return "Перевод выполнен.";
    }

    @Override
    public List<PaymentDto> getAll() {
        List<Payment> payments = paymentRepository.getAll();
        return payments.stream()
                .map(PaymentMapper::toPaymentDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentDto> getAllByEmail(String email) {
        List<Payment> payments = paymentRepository.getAllByEmail(email);
        return payments.stream()
                .map(PaymentMapper::toPaymentDto)
                .collect(Collectors.toList());
    }
}
