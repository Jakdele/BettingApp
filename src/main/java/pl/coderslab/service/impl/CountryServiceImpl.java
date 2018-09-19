package pl.coderslab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Country;
import pl.coderslab.repository.CountryRepository;
import pl.coderslab.service.CountryService;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private CountryRepository countryRepository;


    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }



    @Override
    public List<Country> getAll() {
        return countryRepository.findAll();
    }
}
