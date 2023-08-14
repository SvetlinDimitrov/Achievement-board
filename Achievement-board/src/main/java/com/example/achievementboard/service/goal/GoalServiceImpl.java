package com.example.achievementboard.service.goal;

import com.example.achievementboard.domain.constants.enums.Difficulty;
import com.example.achievementboard.domain.constants.exception.RoutineNotFoundException;
import com.example.achievementboard.domain.constants.exception.UserNotFoundException;
import com.example.achievementboard.domain.constants.exception.GoalNotFoundException;
import com.example.achievementboard.domain.dtos.goal.GoalChange;
import com.example.achievementboard.domain.dtos.goal.GoalCreate;
import com.example.achievementboard.domain.dtos.goal.GoalView;
import com.example.achievementboard.domain.entity.AchievementEntity;
import com.example.achievementboard.domain.entity.GoalEntity;
import com.example.achievementboard.repos.GoalRepository;
import com.example.achievementboard.repos.RoutineRepository;
import com.example.achievementboard.repos.UserRepository;
import com.example.achievementboard.service.achievement.AchievementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class GoalServiceImpl implements GoalService {
    private final GoalRepository goalRepository;
    private final RoutineRepository routineRepository;
    private final AchievementService achievementService;
    private final UserRepository userRepository;

    @Override
    public boolean isEmpty() {
        return goalRepository.count() == 0;
    }

    @Override
    public void saveAll(List<GoalEntity> build) {
        build.forEach(GoalServiceImpl::setPicture);
        goalRepository.saveAll(build);
    }

    @Override
    @Transactional
    public GoalEntity getById(Long id) throws GoalNotFoundException {
        return goalRepository.findById(id).orElseThrow(() -> new GoalNotFoundException(id));
    }

    @Override
    public void save(GoalEntity entity) {
        setPicture(entity);
        goalRepository.save(entity);
    }

    @Override
    @Transactional
    public List<GoalView> getAllGoalsSortedByDate(Long userId) {
        return goalRepository.findAllByUserEntity_IdOrderByEndDate(userId).stream().map(GoalView::new).toList();
    }

    @Override
    @Transactional
    public List<GoalView> getAllGoalsSortByImportance(Long userId) {
        return goalRepository.findAllByUserEntity_Id(userId)
                .stream()
                .sorted((g1,g2) -> g2.getImportance().compareTo(g1.getImportance()))
                .map(GoalView::new)
                .toList();
    }

    @Override
    @Transactional
    public List<GoalView> getAllGoalsSortByDifficulty(Long userId) {
        return goalRepository.findAllByUserEntity_Id(userId)
                .stream()
                .sorted((g1,g2) -> g2.getDifficulty().compareTo(g1.getDifficulty()))
                .map(GoalView::new)
                .toList();
    }

    @Override
    public void save(GoalCreate goalCreate, Long userId) throws UserNotFoundException {
        GoalEntity build = GoalEntity.builder()
                .name(goalCreate.getName())
                .importance(goalCreate.getImportance())
                .descriptionHowToDoIt(goalCreate.getDescriptionHowItWillBeDone())
                .endDate(goalCreate.getEndDate())
                .beginDate(goalCreate.getStartDate())
                .category(goalCreate.getCategory())
                .descriptionWhyYouWantToAchieveIt(goalCreate.getBonusDescription())
                .difficulty(goalCreate.getDifficulty())
                .userEntity(userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId)))
                .routineEntity(goalCreate.getRoutineId() == 0 ? null : routineRepository.findById(goalCreate.getRoutineId()).orElseThrow())
                .build();
        save(build);
    }

    @Override
    @Transactional
    public void edit(GoalChange goalChange) throws GoalNotFoundException, RoutineNotFoundException {
        GoalEntity edit = getById(goalChange.getId());
        edit.setName(goalChange.getName());
        edit.setImportance(goalChange.getImportance());
        edit.setDescriptionHowToDoIt(goalChange.getDescriptionHowToDoIt());
        edit.setEndDate(goalChange.getEndDate());
        edit.setBeginDate(goalChange.getBeginDate());
        edit.setCategory(goalChange.getCategory());
        edit.setDescriptionWhyYouWantToAchieveIt(goalChange.getDescriptionWhyYouWantToAchieveIt());
        edit.setDifficulty(goalChange.getDifficulty());
        edit.setRoutineEntity(goalChange.getRoutineId() == 0 ? null : routineRepository.findById(goalChange.getRoutineId()).orElseThrow(() -> new RoutineNotFoundException(goalChange.getRoutineId())));
        save(edit);
    }

    @Override
    public void deleteGoal(Long id) {
        goalRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void finishGoal(Long id) throws GoalNotFoundException {
        GoalEntity goalEntity = getById(id);
        AchievementEntity newAchievementEntity = AchievementEntity.builder()
                .name(goalEntity.getName())
                .descriptionHowItWasSucceeded(goalEntity.getDescriptionHowToDoIt())
                .startDate(goalEntity.getBeginDate())
                .endDate(goalEntity.getEndDate())
                .difficulty(goalEntity.getDifficulty())
                .userEntity(goalEntity.getUserEntity())
                .routineEntity(goalEntity.getRoutineEntity())
                .build();
        achievementService.save(newAchievementEntity);
        deleteGoal(id);
    }

    @Override
    @Transactional
    public List<GoalView> getAllGoalViews(Long id) {
        return goalRepository.findAllByUserEntity_Id(id).stream().map(GoalView::new).toList();
    }

    private static void setPicture(GoalEntity entity) {
        if(entity.getDifficulty().equals(Difficulty.EASY)){
            entity.setPictureRes("/pic/easy.jpg");
        }else if(entity.getDifficulty().equals(Difficulty.MEDIUM)){
            entity.setPictureRes("/pic/medium.jpg");
        }else{
            entity.setPictureRes("/pic/hard.jpg");
        }
    }
}
