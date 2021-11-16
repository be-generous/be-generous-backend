package com.begenerous.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserDTO {

    private Long userId;
    private String email;
    private String fullName;
    private String avatarURL;
}
