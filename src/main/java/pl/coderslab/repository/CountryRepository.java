package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
