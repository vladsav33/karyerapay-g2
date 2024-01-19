package ru.karyeragame.paymentsystem.game.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.karyeragame.paymentsystem.game.dto.GameCreateDto;
import ru.karyeragame.paymentsystem.game.dto.GameOutputDto;
import ru.karyeragame.paymentsystem.game.dto.GameUpdateDto;
import ru.karyeragame.paymentsystem.game.services.GamesService;
import ru.karyeragame.paymentsystem.user.dto.UserFullDto;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/games")
@Slf4j
public class GamesAdminController {

    private final GamesService gamesService;

    @Autowired
    public GamesAdminController(GamesService gamesService) {
        this.gamesService = gamesService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    GameOutputDto createGame(@RequestBody @Valid GameCreateDto gameCreateDto, Authentication authentication) {
        GameOutputDto response = gamesService.createGame(gameCreateDto,
                ((UserFullDto) authentication.getPrincipal()).getId());

        log.info(String.format("Time: %s. Method: %s. Income data: %s. User Role: %s. User Id: %s",
                LocalDateTime.now(),
                Thread.currentThread().getStackTrace()[0].getMethodName(),
                gameCreateDto,
                ((UserFullDto) authentication.getPrincipal()).getRole(),
                ((UserFullDto) authentication.getPrincipal()).getId()));
        return response;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GameOutputDto> getAll() {
        return gamesService.getAll();
    }

    @GetMapping("/{gameId}")
    @ResponseStatus(HttpStatus.OK)
    public GameOutputDto getGame(@PathVariable @Positive Long gameId) {
        return gamesService.getGame(gameId);
    }

    @PatchMapping("{gameId}")
    public GameOutputDto updateGame(@PathVariable @Positive Long gameId,
                                    @Valid @RequestBody GameUpdateDto gameUpdateDto,
                                    Authentication authentication) {
        return gamesService.updateGame(gameId, gameUpdateDto, ((UserFullDto) authentication.getPrincipal()).getId());
    }

    @DeleteMapping("/{gameId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable @Positive Long gameId) {
        gamesService.deleteGame(gameId);
    }
}
