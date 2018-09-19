package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Transaction;
import pl.coderslab.entity.User;
import pl.coderslab.entity.Wallet;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByWalletId(int id);

}

