package pl.coderslab.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString(exclude = {"game"})
@NoArgsConstructor
public class Odd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = true)
    @ColumnDefault("'0.0'")
    private double homeOdds;
    @Column(nullable = true)
    @ColumnDefault("'0.0'")
    private double drawOdds;
    @Column(nullable = true)
    @ColumnDefault("'0.0'")
    private double awayOdds;

    @OneToOne
    private Game game;

    public Odd(Game game) {
        this.game = game;
    }
}
