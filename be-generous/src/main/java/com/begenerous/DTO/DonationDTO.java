package com.begenerous.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonationDTO {

    private Long donationId;
    private Double amount;
    private String message;
    private Long transactionDate;
    private Long creditCardId;
    private Long charityId;
}
