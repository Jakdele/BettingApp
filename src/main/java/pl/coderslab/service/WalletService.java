package pl.coderslab.service;

import pl.coderslab.entity.Wallet;

import java.math.BigDecimal;

public interface WalletService {


    Wallet save(Wallet wallet);

    void deposit(BigDecimal amount, Wallet wallet);

    void withdraw(BigDecimal amount, Wallet wallet);

}
