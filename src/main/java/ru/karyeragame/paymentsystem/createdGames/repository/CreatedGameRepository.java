package ru.karyeragame.paymentsystem.createdGames.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.karyeragame.paymentsystem.account.TypeOfAccount;
import ru.karyeragame.paymentsystem.createdGames.model.CreatedGame;

import java.util.List;

@Repository
public interface CreatedGameRepository extends JpaRepository<CreatedGame, Long> {
    List<CreatedGame> findByGameId(Long gameId);

    CreatedGame findByGameIdAndTypeOfAccount(Long gameId, TypeOfAccount typeOfAccount);

    @Query(value = "select new CreatedGame(c.gameId, c.userId, c.typeOfAccount) from CreatedGame c, Game g where c.gameId = g.id and g.isAlive = true and c.userId = ?1")
    CreatedGame byUserIdWhereGameIsAlive(Long userId);

//    @Query(value = "select exists from CreatedGame c, Game g where c.gameId = g.id and g.isAlive = true and c.userId = ?1")
    @Query(value = "select exists (select c.userId from CreatedGame c, Game g where c.gameId = g.id and g.isAlive = true and c.userId = ?1)")
    boolean userExistInIsAliveGame(Long userId);
}