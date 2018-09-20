package pl.coderslab.service.impl;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.*;
import pl.coderslab.entity.enums.BetSlipType;
import pl.coderslab.entity.enums.TransactionType;
import pl.coderslab.repository.BetSlipRepository;
import pl.coderslab.repository.TransactionRepository;
import pl.coderslab.repository.WalletRepository;
import pl.coderslab.service.BetSlipService;
import pl.coderslab.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class BetSlipServiceImpl implements BetSlipService {
    private BetSlipRepository betSlipRepository;
    private UserService userService;
    private WalletRepository walletRepository;
    private TransactionRepository transactionRepository;

    public BetSlipServiceImpl(BetSlipRepository betSlipRepository, UserService userService, WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.betSlipRepository = betSlipRepository;
        this.userService = userService;
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<BetSLip> getAll() {
        return betSlipRepository.findAll();
    }

    @Override
    public BetSLip save(BetSLip betSLip) {
        return betSlipRepository.save(betSLip);
    }

    @Override
    public List<BetSLip> getAllByUser(User user) {
        return betSlipRepository.findAllByUser(user);
    }

    @Override
    public void saveNewSlip(BetSLip betSLip, HttpSession session) {
       List<Bet> bets = (List<Bet>) session.getAttribute("bets");
        for (Bet bet:bets) {
            bet.setBetSlip(betSLip);
        }
        Wallet wallet = walletRepository.findByUser(betSLip.getUser());
        wallet.setBalance(wallet.getBalance().subtract(betSLip.getStake()));
        Transaction transaction = new Transaction(betSLip.getStake(),TransactionType.PLACED_BET,wallet);
        transactionRepository.save(transaction);
       betSLip.setBets(bets);
       betSLip.setCounter(bets.size());
       betSLip.setFinalOdds((Double) session.getAttribute("finalOdds"));
       betSLip.setBetSlipType(BetSlipType.OPEN);
       betSlipRepository.save(betSLip);


       session.removeAttribute("bets");
       session.removeAttribute("finalOdds");
    }
}
