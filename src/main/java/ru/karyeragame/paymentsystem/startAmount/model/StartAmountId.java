package ru.karyeragame.paymentsystem.startAmount.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.karyeragame.paymentsystem.account.TypeOfAccount;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class StartAmountId implements Serializable {
    private Long gameId;
    private TypeOfAccount typeOfAccount;
}
