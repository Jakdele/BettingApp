package pl.coderslab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Bet;
import pl.coderslab.entity.Game;
import pl.coderslab.entity.Odd;
import pl.coderslab.repository.GameRepository;
import pl.coderslab.repository.OddRepository;
import pl.coderslab.service.OddService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pl.coderslab.service.Utils.round;

@Service
public class OddServiceImpl implements OddService {

    private OddRepository oddRepository;
    private GameRepository gameRepository;

    @Autowired
    public OddServiceImpl(OddRepository oddRepository, GameRepository gameRepository) {
        this.oddRepository = oddRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public Odd save(Odd odd) {
        return oddRepository.save(odd);
    }

    public Map<String, Double> generateOdds(int id1, int id2) {
        Map<String, Double> results = new HashMap<>();
        final double MARGIN = 1.08d;
        List<Game> homeGames = gameRepository.getAllByHomeTeamId(id1);
        List<Game> awayGames = gameRepository.getAllByAwayTeamId(id2);



        double totalGames = homeGames.size()+awayGames.size();
        double homeWin = 0d;
        double homeLoss= 0d;
        double homeTie = 0d;
        double awayWin = 0d;
        double awayLoss= 0d;
        double awayTie = 0d;


        for (Game game : homeGames) {
            if (game.getHomeScore() > game.getAwayScore()) {
                homeWin += 1d;
            } else if (game.getHomeScore() < game.getAwayScore()) {
                homeLoss += 1d;
            } else {
                homeTie += 1d;
            }
        }
        for (Game game : awayGames) {
            if (game.getAwayScore() > game.getHomeScore()) {
                awayWin += 1d;
            } else if (game.getAwayScore() < game.getHomeScore()) {
                awayLoss += 1d;
            } else {
                awayTie += 1d;
            }
        }
        results.put("homeOdds", round((1d)/(MARGIN*(homeWin+awayLoss)/(totalGames)),2));
        results.put("drawOdds", round((1d)/(MARGIN*(homeTie+awayTie)/(totalGames)),2));
        results.put("awayOdds", round((1d)/(MARGIN*(homeLoss+awayWin)/(totalGames)),2));



        return results;
    }

    @Override
    public double totalOdds(List<Bet> bets) {
        double finalOdds = 1.0d;

        for (Bet betFromList: bets) {
            finalOdds *= betFromList.getOdds();
        }
        return finalOdds;
    }


}
