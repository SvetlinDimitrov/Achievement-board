package com.example.achievementboard.constants.dtos;

import com.example.achievementboard.constants.exeptions.samePasswords.SamePasswordConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SamePasswordConstraint
public class LoginUser {

    @NotBlank(message = "You need to fill this")
    @Email
    private String email;

    @NotBlank(message = "You need to fill this")
    private String password;

    private String confirmPassword;
}
