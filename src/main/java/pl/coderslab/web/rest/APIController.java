package pl.coderslab.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.service.CountryService;
import pl.coderslab.service.GameService;
import pl.coderslab.service.LeagueService;
import pl.coderslab.service.TeamService;

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
}
