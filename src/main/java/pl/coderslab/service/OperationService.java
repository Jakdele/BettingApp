package pl.coderslab.service;

import pl.coderslab.entity.Transaction;
import pl.coderslab.entity.Wallet;

import java.util.List;

public interface OperationService {
    List<Transaction> getAll();
    List<Transaction> getAllByWallet(Wallet wallet);
}
