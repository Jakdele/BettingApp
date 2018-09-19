package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Transaction;
import pl.coderslab.entity.Wallet;

import java.util.List;

public interface OperationRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findAllByWallet(Wallet wallet);
}


