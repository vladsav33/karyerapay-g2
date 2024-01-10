package ru.karyeragame.paymentsystem.game.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import ru.karyeragame.paymentsystem.user.model.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "games")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private long id;
    @NotNull
    @Size(min = 5, max = 100)
    private String name;
    @Nullable
    @Size(min = 5, max = 2500)
    private String description;
    @Column(name = "is_Alive")
    private boolean isAlive;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private User admin;
    @Column(name = "created_on")
    @NotNull
    private LocalDateTime creationDate;
}
