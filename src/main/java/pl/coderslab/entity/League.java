package pl.coderslab.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString(exclude = {"country", "teams", "games"})
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @JsonIgnoreProperties({"players", "league", "teams", "country"})
    @OneToMany(mappedBy = "league", fetch =  FetchType.LAZY)
    private List<Game>games = new ArrayList<>();

    @JsonIgnoreProperties({"players", "league", "teams", "country"})
    @OneToMany(mappedBy = "league", fetch = FetchType.LAZY)
    private List<Team> teams = new ArrayList<>();

    @JsonIgnoreProperties({"players", "league", "teams", "country"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;


}
