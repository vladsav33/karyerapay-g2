package ru.karyeragame.paymentsystem.game.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GameUpdateDto {
    @Size(min = 2, max = 100, message = "Размер поля name, мин 2, мах 100")
    private String name;
    @Size(min = 2, max = 2500, message = "Размер поля description, мин 2, мах 2500")
    private String description;
    private Boolean isAlive;
}
