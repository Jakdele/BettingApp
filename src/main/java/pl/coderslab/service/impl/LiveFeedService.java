package pl.coderslab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Event;
import pl.coderslab.entity.Game;
import pl.coderslab.entity.Team;
import pl.coderslab.entity.enums.EventType;
import pl.coderslab.repository.EventRepository;
import pl.coderslab.repository.GameRepository;
import pl.coderslab.service.GameService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class LiveFeedService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameService gameService;
    @Autowired
    private EventRepository eventRepository;


    @Scheduled(fixedRate = 2000, initialDelay = 5000)
    public void generateLiveGames() {
        List<Game> upcoming = gameService.getUpcoming();
        for (int i = 0; i < upcoming.size(); i++) {
            Game liveGame = upcoming.get(i);
            if(liveGame.isFinished())break;
            liveGame.setLiveGame(true);
            gameRepository.save(liveGame);
            generateLiveEvents(liveGame);
            try {
                TimeUnit.MINUTES.sleep(1l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void generateLiveEvents(Game game) {
        List<Event> gameEvents = new ArrayList<>();
        for (EventType eventType : EventType.values()) {
            Event liveEvent = new Event(eventType, LocalDateTime.now());
            switch (eventType) {
                case FAUL:
                    liveEvent.setTeamName(randomTeam(game).getName());
                    break;
                case GOAL:
                    Team team = randomTeam(game);
                    addScore(team, game);
                    liveEvent.setTeamName(team.getName());
                    break;
                case CORNER:
                    liveEvent.setTeamName(randomTeam(game).getName());
                    break;
                case PENALTY:
                    liveEvent.setTeamName(randomTeam(game).getName());
                    break;
            }
            eventRepository.save(liveEvent);
            gameEvents.add(liveEvent);
            game.setEvents(gameEvents);
            gameRepository.save(game);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        game.setLiveGame(false);
        game.setFinished(true);
        gameService.checkWinningBets(game);
        gameRepository.save(game);

    }

    private void addScore(Team team, Game game) {
        if(team.equals(game.getAwayTeam())){
            game.setAwayScore(game.getAwayScore()+1);
        } else {
            game.setHomeScore(game.getHomeScore()+1);
        }
        gameRepository.save(game);
    }


    public Team randomTeam(Game game) {
        Random randy = new Random();
        int i = randy.nextInt();
        if (i == 0) {
            return game.getHomeTeam();
        } else {
            return game.getAwayTeam();
        }
    }


}
