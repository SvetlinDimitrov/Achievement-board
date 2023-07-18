package com.example.achievementboard.entity;

import com.example.achievementboard.constants.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{
    @Column
    private String username;
    @Column
    private String email;
    @Column
    private Integer age;
    @Column
    private LocalDate birthDate;
    @Column
    private String password;

    @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY , cascade = CascadeType.MERGE)
    private List<Achievement> achievements = new ArrayList<>();

    @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY , cascade = CascadeType.MERGE)
    private List<Goal> goals = new ArrayList<>();

    @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY , cascade = CascadeType.MERGE)
    private List<Routine> routines = new ArrayList<>();
}
