package com.example.achievementboard.domain.dtos.user;

import com.example.achievementboard.domain.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
public class UserView extends User{

    private Long id;
    private String username;
    private String email;
    private Integer age;
    private LocalDate birthDate;

    public UserView(UserEntity userEntity) {

        super(userEntity.getUsername(), userEntity.getPassword(), List.of());
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
        this.email = userEntity.getEmail();
        this.age = userEntity.getAge();
        this.birthDate = userEntity.getBirthDate();

    }
}
