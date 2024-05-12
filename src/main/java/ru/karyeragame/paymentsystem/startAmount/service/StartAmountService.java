package ru.karyeragame.paymentsystem.startAmount.service;

import ru.karyeragame.paymentsystem.account.TypeOfAccount;
import ru.karyeragame.paymentsystem.startAmount.dto.StartAmountDto;

import java.util.List;

public interface StartAmountService {
    List<StartAmountDto> createStartAmounts(List<StartAmountDto> startAmountDtoList);

    List<StartAmountDto> getStartAmountsGame(Long gameId);

    StartAmountDto getStartAmountTypeOfAccount(Long gameId, TypeOfAccount type);
}
