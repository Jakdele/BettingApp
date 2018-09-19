package pl.coderslab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.League;
import pl.coderslab.repository.LeagueRepository;
import pl.coderslab.service.LeagueService;

import java.util.List;

@Service
public class LeagueServiceImpl implements LeagueService {
    private LeagueRepository leagueRepository;

    @Autowired
    public LeagueServiceImpl(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    @Override
    public List<League> getAll() {
        return leagueRepository.findAll();
    }

    @Override
    public League getOne(int id) {
        return leagueRepository.getOne(id);
    }
}
