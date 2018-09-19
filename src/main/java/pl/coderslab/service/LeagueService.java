package pl.coderslab.service;

import pl.coderslab.entity.League;

import java.util.List;

public interface LeagueService {
    List<League> getAll();

    League getOne(int id);
}
