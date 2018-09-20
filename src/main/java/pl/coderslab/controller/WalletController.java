package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.User;
import pl.coderslab.entity.Wallet;
import pl.coderslab.service.OperationService;
import pl.coderslab.service.TransactionService;
import pl.coderslab.service.UserService;
import pl.coderslab.service.WalletService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Controller
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private UserService userService;
    @Autowired
    private OperationService operationService;
    @Autowired
    private WalletService walletService;
    @Autowired
    private TransactionService transactionService;


    @PostMapping("/withdraw")
    public String withdrawFunds(Model model, HttpServletRequest request) {
        Wallet wallet = userService.getCurrentUser().getWallet();
        BigDecimal amount = new BigDecimal(request.getParameter("withdraw"));
        if (wallet.getBalance().compareTo(amount) < 0) {
            User user = userService.getCurrentUser();
            model.addAttribute("user", user);
            return "user/panel";
        }

        walletService.withdraw(amount, wallet);

        return "redirect:/user/panel";
    }

    @PostMapping("/deposit")
    public String depositFunds( Model model, HttpServletRequest request) {
        Wallet wallet = userService.getCurrentUser().getWallet();
        BigDecimal amount = new BigDecimal(request.getParameter("deposit"));
        walletService.deposit(amount, wallet);

        return "redirect:/user/panel";
    }
}
