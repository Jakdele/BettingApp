package pl.coderslab.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Getter
@Setter
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    @JsonIgnoreProperties({"players", "league", "teams", "country"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

}
