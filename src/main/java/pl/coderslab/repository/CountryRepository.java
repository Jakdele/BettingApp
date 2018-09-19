package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Country;
import pl.coderslab.entity.Event;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
