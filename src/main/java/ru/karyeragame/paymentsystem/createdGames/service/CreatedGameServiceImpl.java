package ru.karyeragame.paymentsystem.createdGames.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.karyeragame.paymentsystem.account.TypeOfAccount;
import ru.karyeragame.paymentsystem.account.dto.AccountDto;
import ru.karyeragame.paymentsystem.account.service.AccountService;
import ru.karyeragame.paymentsystem.common.exception.ObjectNotFoundException;
import ru.karyeragame.paymentsystem.createdGames.dto.CreatedGameDto;
import ru.karyeragame.paymentsystem.createdGames.dto.CreatedGameOutDto;
import ru.karyeragame.paymentsystem.createdGames.mapper.CreatedGameMapper;
import ru.karyeragame.paymentsystem.createdGames.model.CreatedGame;
import ru.karyeragame.paymentsystem.createdGames.repository.CreatedGameRepository;
import ru.karyeragame.paymentsystem.exceptions.IncorrectData;
import ru.karyeragame.paymentsystem.exceptions.NotFoundException;
import ru.karyeragame.paymentsystem.startAmount.dto.StartAmountDto;
import ru.karyeragame.paymentsystem.startAmount.service.StartAmountService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static ru.karyeragame.paymentsystem.constants.Constants.START_AMOUNT_DEFAULT;

@Service
@RequiredArgsConstructor
public class CreatedGameServiceImpl implements CreatedGameService {
    private final CreatedGameRepository createdGameRepository;
    private final AccountService accountService;
    private final StartAmountService startAmountService;

    @Override
    public List<CreatedGameOutDto> createCreatedGame(List<CreatedGameDto> createdGameDtoList) {
        if (createdGameDtoList == null || createdGameDtoList.isEmpty()) {
            throw new NotFoundException("Отсутствуют данные для создания игры.");
        }

        List<StartAmountDto> startAmount = startAmountService.getStartAmountsGame(createdGameDtoList.get(0).getGameId());

        List<AccountDto> list = createdGameDtoList.stream()
                .map(CreatedGameMapper::toAccountDto)
                .peek(accountDto -> accountDto.setAmount(findStartAmount(startAmount, accountDto.getTypeOfAccount())))
                .toList();

        List<AccountDto> createdAccounts = new ArrayList<>();
        List<CreatedGameOutDto> notCreatedAccounts = new ArrayList<>();


        for (int i = 0; i < list.size(); i++) {
            try {
                createdAccounts.add(accountService.createAccount(list.get(i)));
            } catch (IncorrectData | ObjectNotFoundException | NotFoundException e) {
                CreatedGameOutDto notCreatedAccount = CreatedGameOutDto.builder()
                        .created("Error")
                        .gameId(list.get(i).getGameId())
                        .userId(list.get(i).getUserId())
                        .typeOfAccount(list.get(i).getTypeOfAccount())
                        .comment(e.getMessage())
                        .build();
                notCreatedAccounts.add(notCreatedAccount);
            }
        }

        List<CreatedGame> createdGameList = createdGameRepository
                .saveAll(CreatedGameMapper.toCreatedGameListFromAccountDtoList(createdAccounts));

        List<CreatedGameOutDto> createdGameOut = createdGameList.stream()
                .map(CreatedGameMapper::toCreatedGameOutDto)
                .peek(createdGameOutDto -> createdGameOutDto.setCreated("Ok"))
                .toList();

        List<CreatedGameOutDto> result = Stream.concat(createdGameOut.stream(), notCreatedAccounts.stream())
                .toList();
        return result;
    }

    private BigDecimal findStartAmount(List<StartAmountDto> startAmount, TypeOfAccount typeOfAccount) {

        BigDecimal bigDecimal = new BigDecimal(START_AMOUNT_DEFAULT).setScale(2, RoundingMode.HALF_UP);

        for (int i = 0; i < startAmount.size(); i++) {
            if (startAmount.get(i).getTypeOfAccount().equals(typeOfAccount)) {
                bigDecimal = startAmount.get(i).getStartAmount();
            }
        }
        return bigDecimal;
    }

    @Override
    public List<CreatedGameDto> getCreatedGame(Long gameId) {
        List<CreatedGame> createdGame = createdGameRepository.findByGameId(gameId);

        return CreatedGameMapper.toCreatedGameDtoList(createdGame);
    }

    @Override
    public CreatedGameDto getCreatedGameTypeOfAccount(Long gameId, TypeOfAccount type) {
        return CreatedGameMapper.toCreatedGameDto(createdGameRepository.findByGameIdAndTypeOfAccount(gameId, type));
    }

    @Override
    public CreatedGameDto getCreatedGameByUserIdWhereGameIsAlive(Long userId) {
        CreatedGame createdGame = createdGameRepository.byUserIdWhereGameIsAlive(userId);
        if (createdGame == null) {
            throw new NotFoundException("Пользователь с ид " + userId + " не состоит в действующей игре.");
        }
        return CreatedGameMapper.toCreatedGameDto(createdGame);
    }
}
