package com.example.achievementboard.service.goal;

import com.example.achievementboard.constants.dtos.goal.GoalCreate;
import com.example.achievementboard.constants.dtos.goal.GoalView;
import com.example.achievementboard.constants.enums.Difficulty;
import com.example.achievementboard.entity.Achievement;
import com.example.achievementboard.entity.Goal;
import com.example.achievementboard.entity.User;
import com.example.achievementboard.repos.GoalRepository;
import com.example.achievementboard.service.achievement.AchievementService;
import com.example.achievementboard.service.routine.RoutineService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GoalServiceImpl implements GoalService {
    private final GoalRepository goalRepository;
    private final RoutineService routineService;
    private final AchievementService achievementService;

    @Override
    public boolean isEmpty() {
        return goalRepository.count() == 0;
    }

    @Override
    public void saveAll(List<Goal> build) {
        build.forEach(GoalServiceImpl::setPicture);
        goalRepository.saveAll(build);
    }

    @Override
    public Goal getById(Long id) {
        return goalRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Goal entity) {
        setPicture(entity);
        goalRepository.save(entity);
    }

    @Override
    public List<Goal> getAllGoals(User user) {
        return goalRepository.findAllByUser(user);
    }

    @Override
    public List<Goal> getAllGoalsSortedByDate(User user) {
        return goalRepository.findAllByUserOrderByEndDate(user);
    }

    @Override
    public List<Goal> getAllGoalsSortByImportance(User user) {
        List<Goal> allByUser = goalRepository.findAllByUser(user);
        allByUser.sort((g1,g2) -> g2.getImportance().compareTo(g1.getImportance()));
        return allByUser;
    }

    @Override
    public List<Goal> getAllGoalsSortByDifficulty(User user) {
        List<Goal> allByUser = goalRepository.findAllByUser(user);
        allByUser.sort((g1,g2) -> g2.getDifficulty().compareTo(g1.getDifficulty()));
        return allByUser;
    }

    @Override
    public void save(GoalCreate goalCreate, User user) {
        Goal build = Goal.builder()
                .name(goalCreate.getName())
                .importance(goalCreate.getImportance())
                .descriptionHowToDoIt(goalCreate.getDescriptionHowItWillBeDone())
                .endDate(goalCreate.getEndDate())
                .beginDate(goalCreate.getStartDate())
                .category(goalCreate.getCategory())
                .descriptionWhyYouWantToAchieveIt(goalCreate.getBonusDescription())
                .difficulty(goalCreate.getDifficulty())
                .user(user)
                .routine(goalCreate.getRoutineId() == 0 ? null : routineService.getById(goalCreate.getRoutineId()))
                .build();
        save(build);
    }

    @Override
    public void edit(GoalView goalView) {
        Goal edit = getById(goalView.getId());
        edit.setName(goalView.getName());
        edit.setImportance(goalView.getImportance());
        edit.setDescriptionHowToDoIt(goalView.getDescriptionHowToDoIt());
        edit.setEndDate(goalView.getEndDate());
        edit.setBeginDate(goalView.getBeginDate());
        edit.setCategory(goalView.getCategory());
        edit.setDescriptionWhyYouWantToAchieveIt(goalView.getDescriptionWhyYouWantToAchieveIt());
        edit.setDifficulty(goalView.getDifficulty());
        edit.setRoutine(goalView.getRoutineId() == 0 ? null : routineService.getById(goalView.getRoutineId()));
        save(edit);
    }

    @Override
    public void deleteGoal(Long id) {
        goalRepository.deleteById(id);
    }

    @Override
    public void finishGoal(Long id) {
        Goal goal = getById(id);
        Achievement newAchievement = Achievement.builder()
                .name(goal.getName())
                .descriptionHowItWasSucceeded(goal.getDescriptionHowToDoIt())
                .startDate(goal.getBeginDate())
                .endDate(goal.getEndDate())
                .difficulty(goal.getDifficulty())
                .user(goal.getUser())
                .routine(goal.getRoutine())
                .build();
        achievementService.save(newAchievement);
        deleteGoal(id);
    }

    private static void setPicture(Goal entity) {
        if(entity.getDifficulty().equals(Difficulty.EASY)){
            entity.setPictureRes("/pic/easy.jpg");
        }else if(entity.getDifficulty().equals(Difficulty.MEDIUM)){
            entity.setPictureRes("/pic/medium.jpg");
        }else{
            entity.setPictureRes("/pic/hard.jpg");
        }
    }
}
