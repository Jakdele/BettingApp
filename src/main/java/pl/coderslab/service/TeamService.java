package pl.coderslab.service;

import pl.coderslab.entity.League;
import pl.coderslab.entity.Team;

import java.util.List;

public interface TeamService {
    List<Team> getAll();
    List<Team> findAllByLeague(League league);
}
