package pl.coderslab.service;

import pl.coderslab.entity.Game;

import java.util.List;

public interface GameService {
    List<Game> getAll();

    Game save(Game game);

    void generateGames();

    List<Game> getUpcoming();

    List<Game> getLiveGames();

    void generateUpcomingGames();

    List<Game> getAllByHomeTeamId(int id);


    List<Game> getAllByAwayTeamId(int id);

    Game getOne(int id);

    void generateResults(Game game);

    void checkWinningBets(Game game);

    List<Game> findAllByIsFinishedIsTrueAndLeagueId(int id);

    List<Game> findTop100ByIsFinishedIsTrue();

    Game findFirstById(int id);
}
