package ru.karyeragame.paymentsystem.startAmount.controller;

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
import ru.karyeragame.paymentsystem.startAmount.dto.StartAmountDto;
import ru.karyeragame.paymentsystem.startAmount.service.StartAmountService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/startAmount")
public class StartAmountController {
    private final StartAmountService startAmountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<StartAmountDto> createStartAmounts(@RequestBody
                                                   @NotNull
                                                   @NotEmpty
                                                   @Valid List<StartAmountDto> startAmountDtoList) {
        return startAmountService.createStartAmounts(startAmountDtoList);
    }

    @GetMapping("/id/{gameId}")
    @ResponseStatus(HttpStatus.OK)
    public List<StartAmountDto> getStartAmountsGame(@PathVariable @Positive Long gameId) {
        return startAmountService.getStartAmountsGame(gameId);
    }

    @GetMapping("/id/{gameId}/type/{type}")
    @ResponseStatus(HttpStatus.OK)
    public StartAmountDto getStartAmountTypeOfAccount(@PathVariable @Positive Long gameId,
                                                      @PathVariable TypeOfAccount type) {
        return startAmountService.getStartAmountTypeOfAccount(gameId, type);
    }
}
