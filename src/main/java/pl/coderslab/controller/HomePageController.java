package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Game;
import pl.coderslab.service.GameService;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class HomePageController {
    @Autowired
    private GameService gameService;

    @ModelAttribute("upcomingGames")
    public List<Game> upcomingGames(){
        return gameService.getUpcoming();
    }



    @GetMapping("/home")
    public String homePage(Model model, HttpSession session) {
        session.removeAttribute("fundsError");
        return "home";
    }

    @RequestMapping("/logoutpage")
    public String logOut() {
        return "logout";
    }


}
