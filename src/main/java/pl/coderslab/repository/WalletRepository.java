package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.User;
import pl.coderslab.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {

    Wallet findByUser(User user);

}

