package com.begenerous.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private String comment;
    private Boolean isAnonymous;
    private Long dateCreated;
    private Long userId;
    private Long charityId;
}
