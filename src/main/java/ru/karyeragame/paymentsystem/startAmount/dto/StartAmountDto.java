package ru.karyeragame.paymentsystem.startAmount.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.karyeragame.paymentsystem.account.TypeOfAccount;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StartAmountDto {
    @NotNull(message = "Поле userId не может быть пустым")
    private Long gameId;

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Поле type не может быть пустым")
    private TypeOfAccount typeOfAccount;

    @NotNull(message = "Поле startAmount не может быть пустым")
    @DecimalMin(value = "0.0", inclusive = true, message = "Поле startAmount не может быть отрицательным")
    @Digits(integer = 12, fraction = 2, message = "Указано не корректное значение. Более 2х знаков после запятой или слишком большое число.")
    private BigDecimal startAmount;
}
