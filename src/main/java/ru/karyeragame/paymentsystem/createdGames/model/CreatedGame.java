package ru.karyeragame.paymentsystem.createdGames.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.karyeragame.paymentsystem.account.TypeOfAccount;

@Getter
@Setter
@Builder
//@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "created_games")
@IdClass(CreatedGameId.class)
public class CreatedGame {
    @Id
    @Column(name = "game_id")
    @NotNull(message = "Поле gameId не может быть пустым")
    private Long gameId;

    @Id
    @Column(name = "user_id")
    @NotNull(message = "Поле userId не может быть пустым")
    private Long userId;

    @Id
    @Column(name = "type_Of_Account")
    @Enumerated(EnumType.STRING)
    private TypeOfAccount typeOfAccount;

    public CreatedGame(Long gameId, Long userId, TypeOfAccount typeOfAccount) {
        this.gameId = gameId;
        this.userId = userId;
        this.typeOfAccount = typeOfAccount;
    }
}
