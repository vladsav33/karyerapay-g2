package ru.karyeragame.paymentsystem.game.services;


import ru.karyeragame.paymentsystem.game.dto.GameCreateDto;
import ru.karyeragame.paymentsystem.game.dto.GameOutputDto;
import ru.karyeragame.paymentsystem.game.dto.GameUpdateDto;

import java.util.List;

public interface GamesService {
    GameOutputDto createGame(GameCreateDto gameCreateDto, Long adminId);

    List<GameOutputDto> getAll();

    GameOutputDto getGame(Long gameId);

    void deleteGame(Long gameId);

    GameOutputDto updateGame(Long gameId, GameUpdateDto gameUpdateDto, Long adminId);
}
