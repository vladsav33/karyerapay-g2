package ru.karyeragame.paymentsystem.game.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.karyeragame.paymentsystem.exceptions.NotFoundException;
import ru.karyeragame.paymentsystem.game.dto.GameCreateDto;
import ru.karyeragame.paymentsystem.game.dto.GameOutputDto;
import ru.karyeragame.paymentsystem.game.dto.GameUpdateDto;
import ru.karyeragame.paymentsystem.game.entities.Game;
import ru.karyeragame.paymentsystem.game.mappers.GameMapper;
import ru.karyeragame.paymentsystem.game.repositories.GamesRepository;
import ru.karyeragame.paymentsystem.user.model.User;
import ru.karyeragame.paymentsystem.user.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GamesServiceImpl implements GamesService {
    private final GamesRepository gamesRepository;
    private final UserService userService;
    private final GameMapper gameMapper;

    @Autowired
    public GamesServiceImpl(GamesRepository gamesRepository, UserService userService, GameMapper gameMapper) {
        this.gamesRepository = gamesRepository;
        this.userService = userService;
        this.gameMapper = gameMapper;
    }

    @Override
    public GameOutputDto createGame(GameCreateDto gameCreateDto, Long adminId) {
        User admin = userService.findById(adminId);
        Game game = gameMapper.gameCreateDtoToGame(gameCreateDto, admin);
        game.setCreationDate(LocalDateTime.now());
        game.setAlive(true);
        game = gamesRepository.save(game);
        return gameMapper.gameToGameOutputDto(game);
    }

    @Override
    public List<GameOutputDto> getAll() {
        return gamesRepository.findAll().stream()
                .map(gameMapper::gameToGameOutputDto)
                .collect(Collectors.toList());
    }

    @Override
    public GameOutputDto getGame(Long gameId) {
        Game game = gamesRepository.findById(gameId)
                .orElseThrow(() -> new NotFoundException("Игра не найдена с id: " + gameId));
        return gameMapper.gameToGameOutputDto(game);
    }

    @Override
    public void deleteGame(Long gameId) {
        gamesRepository.findById(gameId)
                .orElseThrow(() -> new NotFoundException("Игра не найдена с id: " + gameId));
        gamesRepository.deleteById(gameId);
    }

    @Override
    public GameOutputDto updateGame(Long gameId, GameUpdateDto gameUpdateDto, Long adminId) {
        User admin = userService.findById(adminId);
        Game game = gamesRepository.findById(gameId)
                .orElseThrow(() -> new NotFoundException("Игра не найдена с id: " + gameId));
        if (gameUpdateDto.getName() == null
                && gameUpdateDto.getDescription() == null
                && gameUpdateDto.getIsAlive() == null) {
            return gameMapper.gameToGameOutputDto(game);
        }
        if (gameUpdateDto.getName() != null) {
            game.setName(gameUpdateDto.getName());
        }
        if (gameUpdateDto.getDescription() != null) {
            game.setDescription(gameUpdateDto.getDescription());
        }
        if (gameUpdateDto.getIsAlive() != null) {
            game.setAlive(gameUpdateDto.getIsAlive());
        }
        game.setAdmin(admin);
        return gameMapper.gameToGameOutputDto(gamesRepository.save(game));
    }
}