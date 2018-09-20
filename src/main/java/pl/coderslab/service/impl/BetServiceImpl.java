package pl.coderslab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Bet;
import pl.coderslab.repository.BetRepository;
import pl.coderslab.repository.GameRepository;
import pl.coderslab.service.BetService;

import java.util.List;

@Service
public class BetServiceImpl implements BetService {
    private BetRepository betRepository;
    private GameRepository gameRepository;


    @Autowired
    public BetServiceImpl(BetRepository betRepository) {
        this.betRepository = betRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Bet> getAll() {
        return betRepository.findAll();
    }

    @Override
    public double getOdds(Bet bet) {
        switch (bet.getBetType()){
            case AWAY:
                return bet.getGame().getOdd().getAwayOdds();
            case DRAW:
               return bet.getGame().getOdd().getDrawOdds();
            case HOME:
               return bet.getGame().getOdd().getHomeOdds();

        }
        return 0;
    }

    @Override
    public List<Bet> findAllByBetSlipId(int id) {
        return betRepository.findAllByBetSlipId(id);
    }

    @Override
    public List<Bet> findAllByGameId(int id) {
        return betRepository.findAllByGameId(id);
    }

}
