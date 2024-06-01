package ru.karyeragame.paymentsystem.createdGames.service;

import ru.karyeragame.paymentsystem.account.TypeOfAccount;
import ru.karyeragame.paymentsystem.createdGames.dto.CreatedGameDto;
import ru.karyeragame.paymentsystem.createdGames.dto.CreatedGameOutDto;

import java.util.List;

public interface CreatedGameService {
    List<CreatedGameOutDto> createCreatedGame(List<CreatedGameDto> createdGameDtoList);

    List<CreatedGameDto> getCreatedGame(Long gameId);

    CreatedGameDto getCreatedGameTypeOfAccount(Long gameId, TypeOfAccount type);

    CreatedGameDto getCreatedGameByUserIdWhereGameIsAlive(Long userId);
}
