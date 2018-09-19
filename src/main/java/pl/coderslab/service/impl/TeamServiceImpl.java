package pl.coderslab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.League;
import pl.coderslab.entity.Team;
import pl.coderslab.repository.TeamRepository;
import pl.coderslab.service.TeamService;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    @Override
    public List<Team> findAllByLeague(League league) {
        return teamRepository.findAllByLeague(league);
    }
}
