package com.begenerous.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(
        name = "donation"
)
@AllArgsConstructor
@NoArgsConstructor
public class Donation {

    @Id
    @SequenceGenerator(
            name = "donation_sequence",
            sequenceName = "donation_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "donation_sequence"
    )
    @Column(
            name = "donation_id"
    )
    private Long donationId;

    @Column(
            name = "amount"
    )
    private Double amount;

    @Column(
            name = "message"
    )
    private String message;

    @Column(
            name = "date"
    )
    private Long transactionDate;

    @ManyToOne
    @JoinColumn(name = "credit_card_id", nullable = false)
    private CreditCard creditCard;

    @ManyToOne
    @JoinColumn(name = "charity_id", nullable = false)
    private Charity charity;

    public Donation(Double amount, String message, long transactionDate) {
        this.amount = amount;
        this.message = message;
        this.transactionDate = transactionDate;
    }
}
