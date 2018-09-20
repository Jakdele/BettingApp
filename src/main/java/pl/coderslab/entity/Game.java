package pl.coderslab.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = {"country", "league"})
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "DATETIME")
    private LocalDateTime startTime;

    private boolean finished;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"players", "league", "teams", "country"})
    private Country country;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"players", "league", "teams", "country"})
    private Team homeTeam;

    private int homeScore;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"players", "league", "teams", "country"})
    private Team awayTeam;

    private int awayScore;

    @OneToMany(mappedBy = "game")
    private List<Bet> bets = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"players", "league", "teams", "country"})
    private League league;

    @OneToMany
    private List<Event>events = new ArrayList<>();

    @ColumnDefault("false")
    private boolean liveGame = false;

    @OneToMany
    @JsonIgnoreProperties({"players", "league", "teams", "country"})
    private List<Player> goalScorer = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Odd odd;


}
