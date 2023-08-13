package com.example.achievementboard.domain.dtos.user;

import com.example.achievementboard.domain.dtos.achievement.AchievementView;
import com.example.achievementboard.domain.dtos.goal.GoalView;
import com.example.achievementboard.domain.dtos.routine.RoutineView;
import com.example.achievementboard.domain.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserView {

    private Long id;
    private String username;
    private String email;
    private Integer age;
    private LocalDate birthDate;
    private String password;
    private List<AchievementView> achievementViews = new ArrayList<>();
    private List<GoalView> goalViews = new ArrayList<>();
    private List<RoutineView> routineViews = new ArrayList<>();

    public UserView(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
        this.email = userEntity.getEmail();
        this.age = userEntity.getAge();
        this.birthDate = userEntity.getBirthDate();
        this.password = userEntity.getPassword();
        this.achievementViews = userEntity.getAchievementEntities().stream().map(AchievementView::new).toList();
        this.goalViews = userEntity.getGoalEntities().stream().map(GoalView::new).toList();
        this.routineViews = userEntity.getRoutineEntities().stream().map(RoutineView::new).toList();

    }
}
