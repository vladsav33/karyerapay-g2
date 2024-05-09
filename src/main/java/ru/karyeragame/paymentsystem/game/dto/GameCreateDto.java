package ru.karyeragame.paymentsystem.game.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GameCreateDto {
    @NotNull
    @Size(min = 2, max = 100, message = "Размер поля name, мин 2, мах 100")
    private String name;
    @NotBlank(message = "Поле description не может быть пустым")
    @Size(min = 2, max = 2500, message = "Размер поля description, мин 2, мах 2500")
    private String description;
}
