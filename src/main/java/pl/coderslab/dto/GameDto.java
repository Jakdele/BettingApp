package pl.coderslab.dto;

import pl.coderslab.entity.Country;
import pl.coderslab.entity.League;
import pl.coderslab.entity.Player;
import pl.coderslab.entity.Team;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GameDto {
    private int id;
    private LocalDateTime startTime;
    private boolean finished;
    private Country country;
    private Team homeTeam;
    private int homeScore;
    private Team awayTeam;
    private int awayScore;
    private League league;
    private List<Player> goalScorer = new ArrayList<>();

    public GameDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public List<Player> getGoalScorer() {
        return goalScorer;
    }

    public void setGoalScorer(List<Player> goalScorer) {
        this.goalScorer = goalScorer;
    }
}

