package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.entity.Transaction;
import pl.coderslab.entity.User;
import pl.coderslab.service.TransactionService;
import pl.coderslab.service.UserService;

import java.util.List;

@Controller
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;

    @GetMapping("/user/wallet")
    public String userWallet(Model model) {
        User user = userService.getCurrentUser();
        List<Transaction> transactions = transactionService.findAllbyWalletId(user.getWallet().getId());
        model.addAttribute("user", user);
        model.addAttribute("transactionHistory",transactions);
        return "user/wallet";
    }
}
