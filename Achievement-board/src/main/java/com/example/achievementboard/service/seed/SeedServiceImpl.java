package com.example.achievementboard.service.seed;

import com.example.achievementboard.domain.constants.enums.DaysOfTheWeek;
import com.example.achievementboard.domain.constants.enums.Difficulty;
import com.example.achievementboard.domain.constants.enums.Importance;
import com.example.achievementboard.domain.constants.exception.AchievementNotFoundException;
import com.example.achievementboard.domain.constants.exception.GoalNotFoundException;
import com.example.achievementboard.domain.constants.exception.RoutineNotFoundException;
import com.example.achievementboard.domain.constants.exception.UserNotFoundException;
import com.example.achievementboard.domain.entity.AchievementEntity;
import com.example.achievementboard.domain.entity.GoalEntity;
import com.example.achievementboard.domain.entity.RoutineEntity;
import com.example.achievementboard.domain.entity.UserEntity;
import com.example.achievementboard.service.achievement.AchievementService;
import com.example.achievementboard.service.goal.GoalService;
import com.example.achievementboard.service.routine.RoutineService;
import com.example.achievementboard.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class SeedServiceImpl implements SeedService {

    private final UserService userService;
    private final GoalService goalService;
    private final AchievementService achievementService;
    private final RoutineService routineService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void seedUser() {
        if (userService.isEmpty()) {
            UserEntity build = UserEntity.builder()
                    .username("4orapa")
                    .email("4orapa@abv.bg")
                    .age(17)
                    .birthDate(LocalDate.now())
                    .password(passwordEncoder.encode("4orapa"))
                    .build();
            userService.save(build);

        }
    }

    @Override
    public void seedGoals() throws UserNotFoundException, RoutineNotFoundException, GoalNotFoundException, AchievementNotFoundException {
        if (goalService.isEmpty()) {
            goalService.saveAll(List.of(
                    GoalEntity.builder()
                            .name("Clime Everest")
                            .importance(Importance.HIGH)
                            .descriptionHowToDoIt("i will walk 15k steps per day")
                            .descriptionWhyYouWantToAchieveIt("i want to be climber")
                            .endDate(LocalDate.parse("2026-12-27"))
                            .beginDate(LocalDate.now())
                            .category("sport")
                            .userEntity(userService.getById(1L))
                            .difficulty(Difficulty.HARD)
                            .routineEntity(routineService.getRandomRoutine())
                            .build(),
                    GoalEntity.builder()
                            .name("Get Jacked")
                            .importance(Importance.LOW)
                            .descriptionHowToDoIt("i will train 5 times a week")
                            .descriptionWhyYouWantToAchieveIt("i want to have a girl")
                            .endDate(LocalDate.parse("2025-12-27"))
                            .beginDate(LocalDate.now())
                            .category("sport")
                            .userEntity(userService.getById(1L))
                            .difficulty(Difficulty.MEDIUM)
                            .routineEntity(routineService.getRandomRoutine())
                            .build(),
                    GoalEntity.builder()
                            .name("Meditation")
                            .importance(Importance.MEDIUM)
                            .descriptionHowToDoIt("I want to reduce my stress")
                            .descriptionWhyYouWantToAchieveIt("i want to become one with the nature")
                            .endDate(LocalDate.parse("2024-12-27"))
                            .beginDate(LocalDate.now())
                            .category("sport")
                            .userEntity(userService.getById(1L))
                            .difficulty(Difficulty.EASY)
                            .routineEntity(routineService.getRandomRoutine())
                            .build()

            ));
        }
    }

    @Override
    public void seedAchievement() throws UserNotFoundException, RoutineNotFoundException, GoalNotFoundException, AchievementNotFoundException {
        if (achievementService.isEmpty()) {
            achievementService.saveAll(List.of(
                    AchievementEntity.builder()
                            .name("Maraton 20km")
                            .descriptionHowItWasSucceeded("ï was a beast and i just show it")
                            .startDate(LocalDate.parse("2022-09-22"))
                            .endDate(LocalDate.parse("2022-12-27"))
                            .routineEntity(routineService.getRandomRoutine())
                            .userEntity(userService.getById(1L))
                            .difficulty(Difficulty.MEDIUM)
                            .build(),
                    AchievementEntity.builder()
                            .name("Meditation 1hour")
                            .descriptionHowItWasSucceeded("i was calm and relax , one with the floe")
                            .startDate(LocalDate.parse("2021-11-01"))
                            .endDate(LocalDate.parse("2021-11-23"))
                            .routineEntity(routineService.getRandomRoutine())
                            .userEntity(userService.getById(1L))
                            .difficulty(Difficulty.EASY)
                            .build(),
                    AchievementEntity.builder()
                            .name("Six Pack")
                            .descriptionHowItWasSucceeded("never skip core day")
                            .startDate(LocalDate.parse("2023-01-27"))
                            .endDate(LocalDate.parse("2023-12-27"))
                            .routineEntity(routineService.getRandomRoutine())
                            .userEntity(userService.getById(1L))
                            .difficulty(Difficulty.HARD)
                            .build()
            ));


        }
    }

    @Override
    public void seedRoutine() throws UserNotFoundException, RoutineNotFoundException, GoalNotFoundException, AchievementNotFoundException {
        if (routineService.isEmpty()) {
            routineService.saveAll(List.of(
                    RoutineEntity.builder()
                            .name("Meditation")
                            .descriptionHowToDoIt("meditate 20 min every morning")
                            .hoursToSpend(0.20)
                            .difficulty(Difficulty.EASY)
                            .userEntity(userService.getById(1L))
                            .days(Arrays.stream(DaysOfTheWeek.values()).toList())
                            .build(),
                    RoutineEntity.builder()
                            .name("Gym")
                            .descriptionHowToDoIt("just gonna lift")
                            .hoursToSpend(1.4)
                            .difficulty(Difficulty.MEDIUM)
                            .userEntity(userService.getById(1L))
                            .days(List.of(DaysOfTheWeek.MONDAY, DaysOfTheWeek.TUESDAY, DaysOfTheWeek.FRIDAY, DaysOfTheWeek.SATURDAY))
                            .build(),
                    RoutineEntity.builder()
                            .name("Walk")
                            .descriptionHowToDoIt("walk with 4km speed per hour")
                            .hoursToSpend(0.20)
                            .difficulty(Difficulty.EASY)
                            .userEntity(userService.getById(1L))
                            .days(Arrays.stream(DaysOfTheWeek.values()).toList())
                            .build()

            ));
        }
    }

    @Override
    public void seedAll() throws UserNotFoundException, RoutineNotFoundException, GoalNotFoundException, AchievementNotFoundException {
        seedUser();
        seedRoutine();
        seedGoals();
        seedAchievement();
    }
}
