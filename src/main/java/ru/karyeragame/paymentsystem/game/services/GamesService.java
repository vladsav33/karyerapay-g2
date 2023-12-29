package ru.karyeragame.paymentsystem.game.services;


import ru.karyeragame.paymentsystem.game.dto.GameCreateDto;
import ru.karyeragame.paymentsystem.game.dto.GameOutputDto;

public interface GamesService {
    GameOutputDto createGame(GameCreateDto gameCreateDto);
}
