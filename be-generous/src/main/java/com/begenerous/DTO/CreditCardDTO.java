package com.begenerous.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardDTO {

    // TODO add validation
    private Long creditCardId;
    private String cardNumber;
    private String name;
    private String CVV;
    private LocalDate expireDate;
    private Double balance;
    private Long userId;
    private Collection<Long> donationIds;
}
