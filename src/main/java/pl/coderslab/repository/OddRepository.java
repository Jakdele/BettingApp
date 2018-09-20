package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Odd;

public interface OddRepository extends JpaRepository<Odd, Integer> {
}
