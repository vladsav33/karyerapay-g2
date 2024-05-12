package ru.karyeragame.paymentsystem.account.mapper;

import lombok.experimental.UtilityClass;
import ru.karyeragame.paymentsystem.account.dto.AccountDto;
import ru.karyeragame.paymentsystem.account.model.Account;

@UtilityClass
public class AccountMapper {
    public AccountDto toAccountDto(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .typeOfAccount(account.getTypeOfAccount())
                .name(account.getName())
                .userId(account.getUserId())
                .amount(account.getAmount())
                .build();
    }

    public Account toAccount(AccountDto accountDto) {
        return Account.builder()
                .id(accountDto.getId())
                .typeOfAccount(accountDto.getTypeOfAccount())
                .name(accountDto.getName())
                .userId(accountDto.getUserId())
                .amount(accountDto.getAmount())
                .build();
    }
}
