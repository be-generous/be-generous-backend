package com.begenerous.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUserDTO {

    private Long userId = 0L;
    @Email
    private String email = "";
    private String password = "";
    private String fullName = "";
    private String avatarURL = "";
}
