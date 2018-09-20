package pl.coderslab.entity;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.entity.enums.BetStatus;
import pl.coderslab.entity.enums.BetType;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Game game;

    private double odds;

    @ManyToOne
    private BetSLip betSlip;

    private BetStatus status;

    private BetType betType;

    @Override
    public boolean equals(Object o) {
        try {
            int id1 = this.game.getId();
            int id2 = ((Bet) o).game.getId();
            boolean result = id1 == id2;
            return result;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGame().getId());
    }


}
