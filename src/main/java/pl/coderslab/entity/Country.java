package pl.coderslab.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @JsonIgnoreProperties({"players", "league", "teams", "country"})
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<League> leagues = new ArrayList<>();

}
