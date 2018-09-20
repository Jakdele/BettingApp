package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Game;

import java.time.LocalDateTime;
import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {

    List<Game> getAllByStartTimeAfterAndFinishedIsFalse(LocalDateTime date);

    List<Game> getAllByHomeTeamId(int id);

    List<Game> getAllByAwayTeamId(int id);

    List<Game> getAllByLiveGameIsTrue();

}
