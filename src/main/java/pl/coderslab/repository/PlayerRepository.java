package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
