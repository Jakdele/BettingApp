package pl.coderslab.entity;

import lombok.Data;
import pl.coderslab.entity.enums.BetSlipType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class BetSLip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double finalOdds;

    private boolean active;

    private boolean won;

    private BigDecimal stake;

    private boolean completed;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "betSlip", cascade = CascadeType.ALL)
    private List<Bet> bets = new ArrayList<>();

    private BetSlipType betSlipType;
}
