package ru.karyeragame.paymentsystem.account.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
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
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Поле gameId не может быть пустым")
    @PositiveOrZero(message = "Поле gameId не может быть отрицательным")
    private Long gameId;

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Поле type не может быть пустым")
    private TypeOfAccount typeOfAccount;

    @NotBlank(message = "Поле name не может быть пустым")
    @Size(min = 2, max = 100, message = "Размер поля name, мин 2, мах 100")
    private String name;

    @NotNull(message = "Поле userId не может быть пустым")
    @PositiveOrZero(message = "Поле userId не может быть отрицательным")
    private Long userId;

    @NotNull(message = "Поле amount не может быть пустым")
    @DecimalMin(value = "0.0", inclusive = true, message = "Поле amount не может быть отрицательным")
    @Digits(integer = 12, fraction = 2, message = "Указано не корректное значение. Более 2х знаков после запятой или слишком большое число.")
    private BigDecimal amount;

    @NotNull(message = "Поле isNonLocked не может быть пустым")
    private Boolean isLocked;
}
