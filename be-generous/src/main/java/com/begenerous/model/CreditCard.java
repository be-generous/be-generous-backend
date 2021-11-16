package com.begenerous.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
@Table(
        name = "credit_card"
)
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {

    @Id
    @SequenceGenerator(
            name = "credit_card_sequence",
            sequenceName = "credit_card_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "credit_card_sequence"
    )
    @Column(
            name = "credit_card_id"
    )
    private Long creditCardId;

    @Column(
            name = "card_number",
            length = 20,
            nullable = false
    )
    private String cardNumber;

    @Column(
            name = "name",
            length = 26,
            nullable = false
    )
    private String name;

    @Column(
            name = "CVV",
            length = 3,
            nullable = false
    )
    private String CVV;

    @Column(
            name = "expire_date",
            nullable = false
    )
    private LocalDate expireDate;

    @Column(
            name = "balance",
            nullable = false
    )
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "creditCard", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Donation> donation = new ArrayList<>();
}
