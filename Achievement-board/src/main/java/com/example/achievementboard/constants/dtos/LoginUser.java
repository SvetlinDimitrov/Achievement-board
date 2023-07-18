package com.example.achievementboard.constants.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser {

    @NotBlank
    private String email;
    @NotBlank
    private String password;

    //TODO::Custom password validation (check if both password matches)
    private String confirmPassword;
}
