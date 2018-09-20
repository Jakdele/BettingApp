package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.entity.Game;
import pl.coderslab.service.GameService;

import java.util.List;

@Controller
public class LivescoreController {
    @Autowired
    private GameService gameService;

    @ModelAttribute("liveGames")
    public List<Game> liveGames(){
        return gameService.getLiveGames();
    }

    @GetMapping("/livescore")
    public String livescores(Model model){
        return "livescore";
    }
}
