package com.example.achievementboard.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "user" , cascade = {CascadeType.MERGE , CascadeType.REMOVE} , fetch = FetchType.EAGER)
    private List<Achievement> achievements = new ArrayList<>();

    @OneToMany(mappedBy = "user" , cascade = {CascadeType.MERGE , CascadeType.REMOVE} , fetch = FetchType.EAGER)
    private List<Goal> goals = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE , CascadeType.REMOVE}  , fetch = FetchType.EAGER)
    private List<Routine> routines = new ArrayList<>();
}
