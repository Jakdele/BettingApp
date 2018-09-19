package pl.coderslab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Game;
import pl.coderslab.entity.League;
import pl.coderslab.entity.Odd;
import pl.coderslab.entity.Team;
import pl.coderslab.repository.GameRepository;
import pl.coderslab.service.GameService;
import pl.coderslab.service.LeagueService;
import pl.coderslab.service.OddService;
import pl.coderslab.service.TeamService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class GameServiceImpl implements GameService {
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


    public void generateGames() {
        Random randy = new Random();

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
        Random randy = new Random();
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
            game.setStartTime(currentTime.plusHours(randy.nextInt(24) + 1).plusDays(randy.nextInt(3)));
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
