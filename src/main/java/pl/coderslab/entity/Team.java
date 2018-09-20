package pl.coderslab.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@ToString(exclude = {"players", "games"})
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnoreProperties({"players", "league", "teams", "country"})
    @ManyToOne
    private League league;

    @NotBlank
    private String name;

    private int standing;

    @NotNull
    @JsonIgnoreProperties({"players", "league", "teams", "country"})
    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<Player> players = new ArrayList<>();

    @JsonIgnoreProperties({"county", "league", "teams", "country", "team"})
    @OneToMany(fetch = FetchType.LAZY)
    private List<Game> games = new ArrayList<>();


}
