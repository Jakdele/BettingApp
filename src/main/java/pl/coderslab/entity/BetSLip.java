package pl.coderslab.entity;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.entity.enums.BetSlipType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class BetSLip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double finalOdds;

    private BigDecimal stake;

    private int counter;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "betSlip", cascade = CascadeType.ALL)
    private List<Bet> bets = new ArrayList<>();

    private BetSlipType betSlipType;
}
