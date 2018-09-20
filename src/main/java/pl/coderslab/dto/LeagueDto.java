package pl.coderslab.dto;

import pl.coderslab.entity.Country;
import pl.coderslab.entity.Game;
import pl.coderslab.entity.Team;

import java.util.ArrayList;
import java.util.List;

public class LeagueDto {

    private int id;

    private String name;

    private List<Game> games = new ArrayList<>();

    private List<Team> teams = new ArrayList<>();

    private Country country;

    public LeagueDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
