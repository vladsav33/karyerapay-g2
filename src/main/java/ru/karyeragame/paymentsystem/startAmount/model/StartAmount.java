package ru.karyeragame.paymentsystem.startAmount.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
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
@Entity
@Table(name = "start_amounts")
@IdClass(StartAmountId.class)
public class StartAmount {
    @Id
    @Column(name = "game_id")
    @NotNull(message = "Поле gameId не может быть пустым")
    private Long gameId;

    @Id
    @Column(name = "type_Of_Account")
    @Enumerated(EnumType.STRING)
    private TypeOfAccount typeOfAccount;

    @Column(name = "start_Amount")
    @NotNull(message = "Поле startAmount не может быть пустым")
    @DecimalMin(value = "0.0", inclusive = true, message = "Поле startAmount не может быть отрицательным")
    @Digits(integer = 12, fraction = 2, message = "Указано не корректное значение. Более 2х знаков после запятой или слишком большое число.")
    private BigDecimal startAmount;
}
