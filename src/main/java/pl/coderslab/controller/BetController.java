package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Bet;
import pl.coderslab.entity.BetSLip;
import pl.coderslab.entity.Odd;
import pl.coderslab.entity.enums.BetStatus;
import pl.coderslab.entity.enums.BetType;
import pl.coderslab.service.BetService;
import pl.coderslab.service.GameService;
import pl.coderslab.service.OddService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static pl.coderslab.service.Utils.round;

@Controller
@RequestMapping("/bet")
public class BetController {
    @Autowired
    private GameService gameService;
    @Autowired
    private BetService betService;
    @Autowired
    private OddService oddservice;

    @PostMapping("/create/{id}")
    public String createBet(@PathVariable int id, HttpServletRequest request, HttpSession session) {
        Bet bet = new Bet();
        bet.setBetType(BetType.valueOf(request.getParameter("betType")));
        bet.setGame(gameService.getOne(id));
        bet.setStatus(BetStatus.PLACED);
        bet.setOdds(betService.getOdds(bet));
        List<Bet> bets = (List<Bet>) session.getAttribute("bets");
        if (bets == null) {
            bets = new ArrayList<>();
        }
        if (!bets.contains(bet)) {
            bets.add(bet);
        }
        session.setAttribute("finalOdds", round((oddservice.totalOdds(bets)), 2));
        session.setAttribute("bets", bets);
        return "redirect:/home";
    }

    @PostMapping("/remove/{counter}")
    public String removeBet(@PathVariable int counter, HttpServletRequest request, HttpSession session) {
        List<Bet> bets = (List<Bet>) session.getAttribute("bets");
        bets.remove(counter - 1);

        session.setAttribute("finalOdds", round((oddservice.totalOdds(bets)), 2));

        return "redirect:/home";
    }

}
