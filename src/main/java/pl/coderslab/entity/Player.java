package pl.coderslab.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Entity
@Data

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
