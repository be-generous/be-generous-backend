package com.begenerous.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharityDTO {

    // TODO add validation
    private Long charityId;
    private Double goalAmount;
    private Double currentAmount;
    private String coverImageURL;
    private String title;
    private String description;
    private Long dateCreated;
    private Long userId;
}
