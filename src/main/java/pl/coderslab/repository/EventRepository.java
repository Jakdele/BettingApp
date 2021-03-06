package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Bet;
import pl.coderslab.entity.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

}

