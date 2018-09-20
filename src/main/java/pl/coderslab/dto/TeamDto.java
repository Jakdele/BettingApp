package pl.coderslab.dto;

import pl.coderslab.entity.Game;
import pl.coderslab.entity.League;
import pl.coderslab.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TeamDto {
    private int id;

    private League league;

    private String name;

    private int standing;

    private List<Player> players = new ArrayList<>();

    private List<Game> games = new ArrayList<>();

    public TeamDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStanding() {
        return standing;
    }

    public void setStanding(int standing) {
        this.standing = standing;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
