package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Bet;
import pl.coderslab.entity.Game;
import pl.coderslab.entity.User;
import pl.coderslab.service.GameService;
import pl.coderslab.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class HomePageController {
    @Autowired
    private GameService gameService;
    @Autowired
    private UserService userService;

    @ModelAttribute
    public void homeAttributes(Model model, HttpSession session){
        User user = userService.getCurrentUser();
        List<Game> upcomingGames = gameService.getUpcoming();
        session.setAttribute("user", user);
        model.addAttribute("user",user);
        model.addAttribute("upcomingGames",upcomingGames);
    }



    @GetMapping("/home")
    public String homePage(Model model, HttpSession session) {
        return "home";
    }

    @GetMapping("/logoutpage")
    public String logOut() {
        return "logout";
    }


}
