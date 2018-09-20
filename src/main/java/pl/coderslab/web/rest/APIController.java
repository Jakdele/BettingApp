package pl.coderslab.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.entity.Game;
import pl.coderslab.service.CountryService;
import pl.coderslab.service.GameService;
import pl.coderslab.service.LeagueService;
import pl.coderslab.service.TeamService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class APIController {
    @Autowired
    private TeamService teamService;
    @Autowired
    private LeagueService leagueService;
    @Autowired
    private GameService gameService;
    @Autowired
    private CountryService countryService;

    @GetMapping("/countries")
    ResponseEntity getAllCountries() {
        return ResponseEntity.ok(countryService.getAll());
    }

    @GetMapping("/leagues")
    ResponseEntity getAllLeagues() {
        return ResponseEntity.ok(leagueService.getAll());
    }

    @GetMapping("/teams")
    ResponseEntity getAllTeams() {
        return ResponseEntity.ok(teamService.getAll());
    }

    @GetMapping("/games")
    ResponseEntity getAllGames() {
        return ResponseEntity.ok(gameService.getAll());
    }

    @GetMapping("/api/games/previous")
    ResponseEntity getPreviousGames(){return ResponseEntity.ok(gameService.findTop100ByIsFinishedIsTrue()); }
    public List<Game> showPreviousGame(){
        return gameService.findTop100ByIsFinishedIsTrue();
    }
    @GetMapping("/api/games/upcoming")
    public List<Game> showUpcomingGame(){
        return gameService.getUpcoming();
    }
    @GetMapping("/api/games/previous/league/{id}")
    public List<Game> showPreviousGamesFromLeague(@PathVariable int id){
        return gameService.findAllByIsFinishedIsTrueAndLeagueId(id);
    }

    @GetMapping("/api/games/upcoming/league/{id}")
    public List<Game> showUpcomingGamesFromLeague(@PathVariable int id){
        return gameService.findAllByIsFinishedIsTrueAndLeagueId(id);
    }
    @GetMapping("/api/games/{id}")
    public Game getGame(@PathVariable int id){
        return gameService.findFirstById(id);
    }
}
