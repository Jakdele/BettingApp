package pl.coderslab.service.impl;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.Transaction;
import pl.coderslab.repository.TransactionRepository;
import pl.coderslab.service.TransactionService;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }



    @Override
    public List<Transaction> findAllbyWalletId(int id) {
        return transactionRepository.findByWalletId(id);
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
