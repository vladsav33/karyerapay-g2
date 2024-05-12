package ru.karyeragame.paymentsystem.startAmount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.karyeragame.paymentsystem.account.TypeOfAccount;
import ru.karyeragame.paymentsystem.startAmount.model.StartAmount;

import java.util.List;

@Repository
public interface StartAmountRepository extends JpaRepository<StartAmount, Long> {
    List<StartAmount> findByGameId(Long gameId);

    StartAmount findByGameIdAndTypeOfAccount(Long gameId, TypeOfAccount typeOfAccount);
}
