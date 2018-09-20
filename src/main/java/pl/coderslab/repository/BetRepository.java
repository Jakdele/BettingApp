package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Bet;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet, Integer> {
    List<Bet> findAllByBetSlipId(int id);
    List<Bet> findAllByGameId(int id);

}

