package pl.coderslab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.BetSLip;
import pl.coderslab.entity.Game;
import pl.coderslab.entity.User;
import pl.coderslab.service.BetService;
import pl.coderslab.service.BetSlipService;
import pl.coderslab.service.GameService;
import pl.coderslab.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/slip")
public class BetSlipController {
    @Autowired
    private BetSlipService betSlipService;
    @Autowired
    private UserService userService;
    @Autowired
    private BetService betService;
    @Autowired
    private GameService gameService;

    @PostMapping("/create")
    public String createNewBetSlip(HttpSession session, HttpServletRequest request, Model model) {
        User user = userService.getCurrentUser();

        if(user.getWallet().getBalance().compareTo(new BigDecimal(request.getParameter("stake")))<0){
            model.addAttribute("fundsError", "Insufficient funds on your account to place that bet");
            return "home";
            }
        BetSLip betSLip = new BetSLip();
        betSLip.setUser(user);
        betSLip.setStake(new BigDecimal(request.getParameter("stake")));
        betSlipService.saveNewSlip(betSLip, session);
        return "redirect:/home";

    }

    @GetMapping("/details/{id}")
    public String betDetails(@PathVariable int id, Model model){
        model.addAttribute("slipBets" ,betService.findAllByBetSlipId(id));
        return "bet/details";
    }
    @ModelAttribute
    public void homeAttributes(Model model, HttpSession session){
        User user = userService.getCurrentUser();
        List<Game> upcomingGames = gameService.getUpcoming();
        model.addAttribute("user",user);
        model.addAttribute("upcomingGames",upcomingGames);
    }
}
