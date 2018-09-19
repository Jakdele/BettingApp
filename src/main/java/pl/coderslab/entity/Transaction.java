package pl.coderslab.entity;


import lombok.Data;
import pl.coderslab.entity.enums.TransactionType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private BigDecimal amount;

    @NotBlank
    private TransactionType transactionType;

    @ManyToOne
    private Wallet wallet;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "Datetime default CURRENT_TIMESTAMP")
    private Date created = new Date();

    public Transaction(BigDecimal amount, @NotBlank TransactionType transactionType, Wallet wallet) {
        this.amount = amount;
        this.transactionType = transactionType;
        this.wallet = wallet;
    }
}
