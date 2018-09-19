package pl.coderslab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Bet;
import pl.coderslab.entity.BetSLip;
import pl.coderslab.entity.User;
import pl.coderslab.service.BetService;
import pl.coderslab.service.BetSlipService;
import pl.coderslab.service.UserService;
import pl.coderslab.service.utils.InsuffitientFundsException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Controller
@RequestMapping("/slip")
public class BetSlipController {
    @Autowired
    private BetSlipService betSlipService;
    @Autowired
    private UserService userService;
    @Autowired
    private BetService betService;

    @PostMapping("/create")
    public String createNewBetSlip(HttpSession session, HttpServletRequest request, Model model) {
        User user = userService.getCurrentUser();

        if(user.getWallet().getBalance().compareTo(new BigDecimal(request.getParameter("stake")))<0){
            session.setAttribute("fundsError", "Insufficient funds on your account to place that bet");
            return "redirect:/home";
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

}
