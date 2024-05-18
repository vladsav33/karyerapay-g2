package ru.karyeragame.paymentsystem.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.karyeragame.paymentsystem.account.dto.AccountDto;
import ru.karyeragame.paymentsystem.account.mapper.AccountMapper;
import ru.karyeragame.paymentsystem.account.model.Account;
import ru.karyeragame.paymentsystem.account.repository.AccountRepository;
import ru.karyeragame.paymentsystem.game.services.GamesService;
import ru.karyeragame.paymentsystem.user.model.User;
import ru.karyeragame.paymentsystem.user.service.UserService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserService userService;
    private final GamesService gamesService;

    /*todo исправить назначение стартового капитала после реализации создании игры*/

    private static final String START_CAPITAL = "1000";

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        /*todo mapping, возможно создание счёта привязать к созданию игры */
        User user = userService.findById(accountDto.getUserId());
        gamesService.getGame(accountDto.getGameId());

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
                .amount(new BigDecimal(START_CAPITAL).setScale(2, RoundingMode.HALF_UP))
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
