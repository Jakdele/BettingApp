package pl.coderslab.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private User user;

    private BigDecimal balance = BigDecimal.ZERO;

    @OneToMany(mappedBy = "wallet")
    private List<Transaction> transactions = new ArrayList<>();




}
