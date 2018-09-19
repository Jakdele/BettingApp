package pl.coderslab.service;

import pl.coderslab.entity.Bet;

import java.util.List;

public interface BetService {
    List<Bet> getAll();

    double getOdds(Bet bet);

    List<Bet> findAllByBetSlipId(int id);
}
