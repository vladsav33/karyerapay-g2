package ru.karyeragame.paymentsystem.createdGames.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.karyeragame.paymentsystem.account.TypeOfAccount;
import ru.karyeragame.paymentsystem.createdGames.dto.CreatedGameDto;
import ru.karyeragame.paymentsystem.createdGames.dto.CreatedGameOutDto;
import ru.karyeragame.paymentsystem.createdGames.service.CreatedGameService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/createdGames")
public class CreatedGameController {
    private final CreatedGameService createdGameService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public List<CreatedGameOutDto> createCreatedGame(@RequestBody
                                                     @NotNull
                                                     @NotEmpty(message = "Нет данных для создания игры")
                                                     List<@Valid CreatedGameDto> createdGameDtoList) {
        return createdGameService.createCreatedGame(createdGameDtoList);
    }

    @GetMapping("/gameId/{gameId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CreatedGameDto> getCreatedGame(@PathVariable @Positive Long gameId) {
        return createdGameService.getCreatedGame(gameId);
    }

    @GetMapping("/userId/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public CreatedGameDto getCreatedGameByUserIdWhereGameIsAlive(@PathVariable @Positive Long userId) {
        return createdGameService.getCreatedGameByUserIdWhereGameIsAlive(userId);
    }

    @GetMapping("/gameId/{gameId}/type/{type}")
    @ResponseStatus(HttpStatus.OK)
    public CreatedGameDto getCreatedGameTypeOfAccount(@PathVariable @Positive Long gameId,
                                                      @PathVariable TypeOfAccount type) {
        return createdGameService.getCreatedGameTypeOfAccount(gameId, type);
    }
}
