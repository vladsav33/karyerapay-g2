package ru.karyeragame.paymentsystem.createdGames.mapper;

import lombok.experimental.UtilityClass;
import ru.karyeragame.paymentsystem.account.dto.AccountDto;
import ru.karyeragame.paymentsystem.createdGames.dto.CreatedGameDto;
import ru.karyeragame.paymentsystem.createdGames.dto.CreatedGameOutDto;
import ru.karyeragame.paymentsystem.createdGames.model.CreatedGame;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CreatedGameMapper {

    public CreatedGame toCreatedGame(CreatedGameDto createdGameDto) {
        return CreatedGame.builder()
                .gameId(createdGameDto.getGameId())
                .userId(createdGameDto.getUserId())
                .typeOfAccount(createdGameDto.getTypeOfAccount())
                .build();
    }

    public CreatedGameDto toCreatedGameDto(CreatedGame createdGame) {
        return CreatedGameDto.builder()
                .gameId(createdGame.getGameId())
                .userId(createdGame.getUserId())
                .typeOfAccount(createdGame.getTypeOfAccount())
                .build();
    }

    public AccountDto toAccountDto(CreatedGameDto createdGameDto) {
        return AccountDto.builder()
                .gameId(createdGameDto.getGameId())
                .typeOfAccount(createdGameDto.getTypeOfAccount())
                .userId(createdGameDto.getUserId())
                .build();
    }

    public CreatedGame toCreatedGameFromAccountDto(AccountDto accountDto) {
        return CreatedGame.builder()
                .gameId(accountDto.getGameId())
                .userId(accountDto.getUserId())
                .typeOfAccount(accountDto.getTypeOfAccount())
                .build();
    }

    public List<CreatedGame> toCreatedGameListFromAccountDtoList(List<AccountDto> accountDtoDtoList) {
        return accountDtoDtoList.stream()
                .map(CreatedGameMapper::toCreatedGameFromAccountDto)
                .collect(Collectors.toList());
    }


    public List<CreatedGame> toCreatedGameList(List<CreatedGameDto> createdGameDtoList) {
        return createdGameDtoList.stream()
                .map(CreatedGameMapper::toCreatedGame)
                .collect(Collectors.toList());
    }

    public List<CreatedGameDto> toCreatedGameDtoList(List<CreatedGame> createdGameList) {
        return createdGameList.stream()
                .map(CreatedGameMapper::toCreatedGameDto)
                .collect(Collectors.toList());
    }

    public CreatedGameOutDto toCreatedGameOutDto(CreatedGame createdGame) {
        return CreatedGameOutDto.builder()
                .gameId(createdGame.getGameId())
                .userId(createdGame.getUserId())
                .typeOfAccount(createdGame.getTypeOfAccount())
                .build();
    }
}
