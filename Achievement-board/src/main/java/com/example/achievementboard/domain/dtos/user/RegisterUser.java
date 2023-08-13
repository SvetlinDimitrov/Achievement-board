package com.example.achievementboard.domain.dtos.user;


import com.example.achievementboard.domain.constants.exeptions.dataChecker.AtLeast18YearsOldConstrain;
import com.example.achievementboard.domain.constants.exeptions.emailChecker.NotUsedEmailConstraint;
import com.example.achievementboard.domain.entity.UserEntity;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUser {

    @NotBlank(message = "You need to fill this")
    @Size(message = "Your username must be at least 4 chars long" , min = 4)
    private String username;

    @NotBlank(message = "You need to fill this")
    @Email
    @NotUsedEmailConstraint
    private String email;

    @NotNull(message = "You need to fill this")
    @Past
    @AtLeast18YearsOldConstrain
    private LocalDate birthDate;

    @NotBlank(message = "You need to fill this")
    @Size(message = "Your password must be at least 5 chars long" , min = 5)
    private String password;

    public UserEntity toUser(){
        Period p = Period.between(birthDate, LocalDate.now());

        return UserEntity.builder()
                .username(username)
                .email(email)
                .age(p.getYears())
                .birthDate(birthDate)
                .password(password)
                .build();
    }
}
