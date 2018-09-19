package pl.coderslab.service;

import pl.coderslab.entity.Bet;
import pl.coderslab.entity.Odd;

import java.util.List;
import java.util.Map;

public interface OddService {
    Odd save(Odd odd);
    public Map<String,Double> generateOdds(int id1, int id2);

    double totalOdds(List<Bet> bets);
}
