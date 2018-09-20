package pl.coderslab.service;

import pl.coderslab.entity.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> findAllbyWalletId (int  id);

    Transaction save(Transaction transaction);
}
