package ru.karyeragame.paymentsystem.game.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.karyeragame.paymentsystem.game.entities.Game;

@Repository
public interface GamesRepository extends JpaRepository<Game, Long> {
}
