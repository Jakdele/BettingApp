package pl.coderslab.entity;


import lombok.Getter;
import lombok.Setter;
import pl.coderslab.entity.enums.TransactionType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private BigDecimal amount;

    @NotBlank
    private TransactionType transactionType;

    @ManyToOne
    private Wallet wallet;


    @Column(columnDefinition = "Datetime")
    private LocalDateTime created;

    public Transaction(BigDecimal amount, @NotBlank TransactionType transactionType, Wallet wallet, LocalDateTime created) {
        this.amount = amount;
        this.transactionType = transactionType;
        this.wallet = wallet;
        this.created = created;
    }

    public Transaction() {
    }
}
