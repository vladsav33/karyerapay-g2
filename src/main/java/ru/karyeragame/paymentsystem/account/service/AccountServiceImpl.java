package ru.karyeragame.paymentsystem.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.karyeragame.paymentsystem.account.TypeOfAccount;
import ru.karyeragame.paymentsystem.account.dto.AccountDto;
import ru.karyeragame.paymentsystem.account.mapper.AccountMapper;
import ru.karyeragame.paymentsystem.account.model.Account;
import ru.karyeragame.paymentsystem.account.repository.AccountRepository;
import ru.karyeragame.paymentsystem.createdGames.repository.CreatedGameRepository;
import ru.karyeragame.paymentsystem.exceptions.IncorrectData;
import ru.karyeragame.paymentsystem.game.dto.GameOutputDto;
import ru.karyeragame.paymentsystem.game.services.GamesService;
import ru.karyeragame.paymentsystem.startAmount.service.StartAmountService;
import ru.karyeragame.paymentsystem.user.Role;
import ru.karyeragame.paymentsystem.user.model.User;
import ru.karyeragame.paymentsystem.user.service.UserService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import static ru.karyeragame.paymentsystem.constants.Constants.START_AMOUNT_DEFAULT;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserService userService;
    private final GamesService gamesService;
    private final CreatedGameRepository createdGameRepository;
    private final StartAmountService startAmountService;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        User user = userService.findById(accountDto.getUserId());
        GameOutputDto game = gamesService.getGame(accountDto.getGameId());

        if (!game.isAlive()) {
            throw new IncorrectData("Игра не активна с ид: " + game.getId());
        }

        if (createdGameRepository.userExistInIsAliveGame(user.getId())) {
            throw new IncorrectData("У пользователя с ид: " + user.getId() + " уже есть действующая игра.");
        }

        if (accountDto.getTypeOfAccount().equals(TypeOfAccount.ADMIN)) {
            if (!user.getRole().equals(Role.ADMIN)) {
                throw new IncorrectData("Пользователь с ид: " + user.getId() + " имеет роль: " + user.getRole() +
                        " и не может иметь счёт: " + accountDto.getTypeOfAccount());
            }
        }

        String name =
                switch (accountDto.getTypeOfAccount()) {
                    case PERSONAL -> user.getNickname();
                    case ADMIN -> accountDto.getTypeOfAccount().toString().toUpperCase();
                    default -> accountDto.getTypeOfAccount().toString().toUpperCase() + " - " + user.getNickname();
                };

        Account account = Account.builder()
                .gameId(accountDto.getGameId())
                .typeOfAccount(accountDto.getTypeOfAccount())
                .name(name)
                .userId(accountDto.getUserId())
                .amount(accountDto.getAmount() != null ? accountDto.getAmount()
                        : new BigDecimal(START_AMOUNT_DEFAULT).setScale(2, RoundingMode.HALF_UP))
                .isLocked(false)
                .build();
        account = accountRepository.create(account);

        return AccountMapper.toAccountDto(account);
    }

    @Override
    public List<AccountDto> getAll() {
        return accountRepository.getAll().stream()
                .map(AccountMapper::toAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto getAccount(Long accountId) {
        return AccountMapper.toAccountDto(accountRepository.getById(accountId));
    }

    @Override
    public void deleteAccount(Long accountId) {
        accountRepository.deleteAccount(accountId);
    }

    @Override
    public List<AccountDto> getAllByEmail(String email) {
        List<Account> accounts = accountRepository.getAllByEmail(email);
        return accounts.stream()
                .map(AccountMapper::toAccountDto)
                .collect(Collectors.toList());
    }
}
