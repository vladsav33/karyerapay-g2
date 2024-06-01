package ru.karyeragame.paymentsystem.createdGames.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.karyeragame.paymentsystem.account.TypeOfAccount;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatedGameOutDto {

    private String created;

    @NotNull(message = "Поле gameId не может быть пустым")
    private Long gameId;

    @NotNull(message = "Поле userId не может быть пустым")
    private Long userId;

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Поле typeOfAccount не может быть пустым")
    private TypeOfAccount typeOfAccount;

    private String comment;
}
