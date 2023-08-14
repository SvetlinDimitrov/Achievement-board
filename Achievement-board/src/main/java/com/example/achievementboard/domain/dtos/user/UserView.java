package com.example.achievementboard.domain.dtos.user;

import com.example.achievementboard.domain.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserView userView = (UserView) o;
        return Objects.equals(id, userView.id) && Objects.equals(username, userView.username) && Objects.equals(email, userView.email) && Objects.equals(age, userView.age) && Objects.equals(birthDate, userView.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, username, email, age, birthDate);
    }
}
