package pl.coderslab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Game;
import pl.coderslab.service.GameService;

import java.util.List;

@Controller
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameService gameService;


    @ModelAttribute("upcomingGames")
    public List<Game> upcomingGames(){
       return gameService.getUpcoming();
    }

    @GetMapping("/{id}/details")
    public String gameDetails(@PathVariable int id, Model model){
        Game game = gameService.getOne(id);
        model.addAttribute("game", game);
        return "game/details";
    }

    @GetMapping("/generate")
    public String generateGames(){
        gameService.generateGames();
        return "home";
    }
    @GetMapping("/generate-upcoming")
    public String generateUpcomingGames(){
        gameService.generateUpcomingGames();
        return "home";
    }

}
