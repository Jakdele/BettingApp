package pl.coderslab.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    @OneToMany(fetch = FetchType.LAZY)
    private List<Event>events = new ArrayList<>();

    @OneToMany
    @JsonIgnoreProperties({"players", "league", "teams", "country"})
    private List<Player> goalScorer = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Odd odd;


//    @Column(nullable = true)
//    @ColumnDefault("'0.0'")
//    private double homeOdds;
//    @Column(nullable = true)
//    @ColumnDefault("'0.0'")
//    private double drawOdds;
//    @Column(nullable = true)
//    @ColumnDefault("'0.0'")
//    private double awayOdds;

}
