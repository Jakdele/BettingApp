package pl.coderslab.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.service.CountryService;
import pl.coderslab.service.GameService;
import pl.coderslab.service.LeagueService;
import pl.coderslab.service.TeamService;

@Controller
public class ViewController {
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

//        @GetMapping("/show-countries")
//        public String get();

        @GetMapping("/show-leagues")
        ResponseEntity getAllLeagues() {
            return ResponseEntity.ok(leagueService.getAll());
        }

        @GetMapping("/show-games")
        ResponseEntity getAllGames() {
            return ResponseEntity.ok(gameService.getAll());
        }
    }
}
