package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Transaction;
import pl.coderslab.entity.enums.TransactionType;
import pl.coderslab.entity.User;
import pl.coderslab.entity.Wallet;
import pl.coderslab.service.OperationService;
import pl.coderslab.service.UserService;
import pl.coderslab.service.WalletService;

import javax.validation.Valid;

@Controller
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private UserService userService;
    @Autowired
    private OperationService operationService;
    @Autowired
    private WalletService walletService;


    @PostMapping("/withdraw")
    public String withdrawFunds(@AuthenticationPrincipal User user, @Valid Transaction transaction, Model model) {
        Wallet wallet = user.getWallet();
        if (transaction.getAmount().compareTo(wallet.getBalance()) < 0) {
            return "Not enough funds";
        }
        wallet.setBalance(wallet.getBalance().subtract(transaction.getAmount()));
        transaction.setTransactionType(TransactionType.WITHDRAW);
        transaction.setWallet(wallet);

        return "redirect:/user/balance";
    }

    @PostMapping("/deposit")
    public String depositFunds(@AuthenticationPrincipal User user, @Valid Transaction transaction, Model model) {
        Wallet wallet = user.getWallet();

        wallet.setBalance(wallet.getBalance().add(transaction.getAmount()));
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setWallet(wallet);

        return "redirect:/user/balance";
    }
}
