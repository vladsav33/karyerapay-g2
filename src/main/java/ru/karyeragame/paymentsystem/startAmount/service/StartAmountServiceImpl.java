package ru.karyeragame.paymentsystem.startAmount.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.karyeragame.paymentsystem.account.TypeOfAccount;
import ru.karyeragame.paymentsystem.exceptions.NotFoundException;
import ru.karyeragame.paymentsystem.startAmount.dto.StartAmountDto;
import ru.karyeragame.paymentsystem.startAmount.mapper.StartAmountMapper;
import ru.karyeragame.paymentsystem.startAmount.model.StartAmount;
import ru.karyeragame.paymentsystem.startAmount.repository.StartAmountRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StartAmountServiceImpl implements StartAmountService {
    private final StartAmountRepository startAmountRepository;

    @Override
    public List<StartAmountDto> createStartAmounts(List<StartAmountDto> startAmountDtoList) {
        if (startAmountDtoList == null || startAmountDtoList.isEmpty()) {
            throw new NotFoundException("Отсутствуют данные стартовой суммы.");
        }

        List<StartAmount> startAmountList = startAmountRepository.saveAll(
                StartAmountMapper.toStartAmountList(startAmountDtoList));
        return StartAmountMapper.toStartAmountDtoList(startAmountList);
    }

    @Override
    public List<StartAmountDto> getStartAmountsGame(Long gameId) {
        List<StartAmount> startAmounts = startAmountRepository.findByGameId(gameId);

        return StartAmountMapper.toStartAmountDtoList(startAmounts);
    }

    @Override
    public StartAmountDto getStartAmountTypeOfAccount(Long gameId, TypeOfAccount type) {
        return StartAmountMapper.startAmountDto(startAmountRepository.findByGameIdAndTypeOfAccount(gameId, type));
    }
}
