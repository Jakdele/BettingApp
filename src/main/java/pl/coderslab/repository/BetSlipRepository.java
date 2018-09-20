package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.BetSLip;
import pl.coderslab.entity.User;

import java.util.List;

public interface BetSlipRepository extends JpaRepository<BetSLip, Integer> {
    List<BetSLip> findAllByUser(User user);

}
