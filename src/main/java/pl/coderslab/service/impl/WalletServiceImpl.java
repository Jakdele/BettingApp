package pl.coderslab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Transaction;
import pl.coderslab.entity.Wallet;
import pl.coderslab.entity.enums.TransactionType;
import pl.coderslab.repository.TransactionRepository;
import pl.coderslab.repository.WalletRepository;
import pl.coderslab.service.WalletService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class WalletServiceImpl implements WalletService {
    private WalletRepository walletRepository;
    private TransactionRepository transactionRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet save(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public void deposit(BigDecimal amount, Wallet wallet) {
        Transaction transaction = new Transaction(amount, TransactionType.DEPOSIT, wallet, LocalDateTime.now());
        wallet.setBalance(wallet.getBalance().add(amount));
        transactionRepository.save(transaction);
        walletRepository.save(wallet);
    }

    @Override
    public void withdraw(BigDecimal amount, Wallet wallet) {
        Transaction transaction = new Transaction(amount,TransactionType.WITHDRAW,wallet, LocalDateTime.now());
        wallet.setBalance(wallet.getBalance().subtract(amount));
        transactionRepository.save(transaction);
        walletRepository.save(wallet);
    }
}
