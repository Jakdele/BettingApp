package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.League;

public interface LeagueRepository extends JpaRepository<League, Integer> {
}
