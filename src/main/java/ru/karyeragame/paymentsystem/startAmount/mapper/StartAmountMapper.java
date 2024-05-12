package ru.karyeragame.paymentsystem.startAmount.mapper;

import lombok.experimental.UtilityClass;
import ru.karyeragame.paymentsystem.startAmount.dto.StartAmountDto;
import ru.karyeragame.paymentsystem.startAmount.model.StartAmount;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class StartAmountMapper {

    public StartAmount toStartAmount(StartAmountDto startAmountDto) {
        return StartAmount.builder()
                .gameId(startAmountDto.getGameId())
                .typeOfAccount(startAmountDto.getTypeOfAccount())
                .startAmount(startAmountDto.getStartAmount())
                .build();
    }

    public StartAmountDto startAmountDto(StartAmount startAmount) {
        return StartAmountDto.builder()
                .gameId(startAmount.getGameId())
                .typeOfAccount(startAmount.getTypeOfAccount())
                .startAmount(startAmount.getStartAmount())
                .build();
    }

    public List<StartAmount> toStartAmountList(List<StartAmountDto> startAmountDtoList) {
        return startAmountDtoList.stream()
                .map(StartAmountMapper::toStartAmount)
                .collect(Collectors.toList());
    }

    public List<StartAmountDto> toStartAmountDtoList(List<StartAmount> startAmountList) {
        return startAmountList.stream()
                .map(StartAmountMapper::startAmountDto)
                .collect(Collectors.toList());
    }
}
