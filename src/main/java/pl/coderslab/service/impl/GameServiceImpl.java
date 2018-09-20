package pl.coderslab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.*;
import pl.coderslab.entity.enums.BetSlipType;
import pl.coderslab.entity.enums.BetStatus;
import pl.coderslab.entity.enums.BetType;
import pl.coderslab.repository.BetRepository;
import pl.coderslab.repository.BetSlipRepository;
import pl.coderslab.repository.GameRepository;
import pl.coderslab.repository.WalletRepository;
import pl.coderslab.service.GameService;
import pl.coderslab.service.LeagueService;
import pl.coderslab.service.OddService;
import pl.coderslab.service.TeamService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class GameServiceImpl implements GameService {
    Random randy = new Random();
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private BetSlipRepository betSlipRepository;
    @Autowired
    private BetRepository betRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameService gameService;
    @Autowired
    private LeagueService leagueService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private OddService oddService;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> getAll() {
        return gameRepository.findAll();
    }

    @Override
    public Game save(Game game) {
        return gameRepository.save(game);
    }




    @Override
    public Game getOne(int id) {
        return gameRepository.getOne(id);
    }

    @Override
    public void generateResults(Game game) {
        game.setAwayScore(randy.nextInt(5));
        game.setHomeScore(randy.nextInt(5));
        checkWinningBets(game);

    }

    public void checkWinningBets(Game game) {
        List<Bet> betsByGame = betRepository.findAllByGameId(game.getId());
        for (Bet bet : betsByGame) {
            getWinner(game, bet);
            BetSLip checkedSlip = bet.getBetSlip();
            checkedSlip.setCounter(checkedSlip.getCounter() - 1);
            betSlipRepository.save(checkedSlip);
            if (checkedSlip.getCounter() == 0) {
                checkBetSlip(checkedSlip);
            }
        }
//        List<BetSLip> betsToCheck = betSlipRepository
    }

    private void checkBetSlip(BetSLip checkedSlip) {
        List<Bet> betsToCheck = checkedSlip.getBets();
        for (Bet betFromSlip :  betsToCheck) {
            if (betFromSlip.getStatus().equals(BetStatus.LOST)) {
                checkedSlip.setBetSlipType(BetSlipType.LOST);
                betSlipRepository.save(checkedSlip);
                return;
            }
        }
        checkedSlip.setBetSlipType(BetSlipType.WON);
        resolveWonBet(checkedSlip);
        checkedSlip.setBetSlipType(BetSlipType.PAID);
        betSlipRepository.save(checkedSlip);

    }

    public void resolveWonBet(BetSLip checkedSlip) {
        Wallet wallet = checkedSlip.getUser().getWallet();
        BigDecimal winnings = new BigDecimal(checkedSlip.getFinalOdds());
        wallet.setBalance(wallet.getBalance().add(checkedSlip.getStake().multiply(winnings)));
        walletRepository.save((wallet));
    }

    public void getWinner(Game game, Bet bet){
            if (game.getHomeScore() > game.getAwayScore()&& bet.getBetType().equals(BetType.HOME)) {
                bet.setStatus(BetStatus.WON);
                betRepository.save(bet);
            } else if (game.getHomeScore() < game.getAwayScore()&& bet.getBetType().equals(BetType.AWAY)) {
                bet.setStatus(BetStatus.WON);
                betRepository.save(bet);
            } else if(game.getHomeScore() == game.getAwayScore()&& bet.getBetType().equals(BetType.DRAW)) {
                bet.setStatus(BetStatus.WON);
                betRepository.save(bet);
            } else {
                bet.setStatus(BetStatus.LOST);
                betRepository.save(bet);
            }
    }


    public void generateGames() {
        for (int i = 1; i < 1000; i++) {
            League league = leagueService.getOne(randy.nextInt(3) + 1);
            List<Team> teamsInLeague = teamService.findAllByLeague(league);
            int limit = teamsInLeague.size();
            Game game = new Game();
            game.setAwayTeam(teamsInLeague.get(randy.nextInt(limit)));
            game.setHomeTeam(teamsInLeague.get(randy.nextInt(limit)));
            while (game.getAwayTeam().equals(game.getHomeTeam())) {
                game.setHomeTeam(teamsInLeague.get(randy.nextInt(limit)));
            }
            game.setAwayScore(randy.nextInt(5));
            game.setHomeScore(randy.nextInt(5));
            game.setFinished(true);
            game.setLeague(league);
            game.setCountry(league.getCountry());
            game.setStartTime(LocalDateTime.now());
            gameService.save(game);

        }
    }

    @Override
    public List<Game> getUpcoming() {
        return gameRepository.getAllByStartTimeAfter(LocalDateTime.now());
    }

    @Override
    public void generateUpcomingGames() {
        LocalDateTime currentTime = LocalDateTime.now();


        for (int i = 1; i < 50; i++) {
            League league = leagueService.getOne(randy.nextInt(3) + 1);
            List<Team> teamsInLeague = teamService.findAllByLeague(league);
            int limit = teamsInLeague.size();
            Game game = new Game();
            game.setAwayTeam(teamsInLeague.get(randy.nextInt(limit)));
            game.setHomeTeam(teamsInLeague.get(randy.nextInt(limit)));
            while (game.getAwayTeam().equals(game.getHomeTeam())) {
                game.setHomeTeam(teamsInLeague.get(randy.nextInt(limit)));
            }
//            game.setAwayScore(randy.nextInt(3));
//            game.setHomeScore(randy.nextInt(3));
            game.setFinished(false);
            game.setLeague(league);
            game.setCountry(league.getCountry());
            game.setStartTime(currentTime.plusHours(randy.nextInt(24) + 1).plusDays(randy.nextInt(3)+2));
            Map<String, Double> odds = oddService.generateOdds(game.getHomeTeam().getId(),game.getAwayTeam().getId());
            Odd gameOds = new Odd(game);
            gameOds.setHomeOdds(odds.get("homeOdds"));
            gameOds.setDrawOdds(odds.get("drawOdds"));
            gameOds.setAwayOdds(odds.get("awayOdds"));
            game.setOdd(gameOds);
            gameService.save(game);
        }
    }

    @Override
    public List<Game> getAllByHomeTeamId(int id) {
        return gameRepository.getAllByHomeTeamId(id);
    }

    @Override
    public List<Game> getAllByAwayTeamId(int id) {
        return gameRepository.getAllByAwayTeamId(id);
    }


}
