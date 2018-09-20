package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Game;
import pl.coderslab.service.GameService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private GameService gameService;

    @GetMapping("/panel")
    public String adminPanel() {
        return "admin/admin_panel";
    }

    @GetMapping("/finish")
    public String finishAllUnresolved() {
        List<Game> games = gameService.getUpcoming();
        for (Game game : games) {
            gameService.generateResults(game);
            game.setFinished(true);
            gameService.save(game);
        }
        return "admin/admin_panel";
    }

}
