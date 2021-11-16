package com.begenerous.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCreditCardDTO {

    private Long creditCardId;
    private String cardNumber;
    private String name;
    private Double balance;
}
