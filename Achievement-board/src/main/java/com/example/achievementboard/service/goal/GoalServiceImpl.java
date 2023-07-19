package com.example.achievementboard.service.goal;

import com.example.achievementboard.constants.enums.Difficulty;
import com.example.achievementboard.entity.Goal;
import com.example.achievementboard.entity.User;
import com.example.achievementboard.repos.GoalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class GoalServiceImpl implements GoalService {
    private final GoalRepository repository;

    @Override
    public boolean isEmpty() {
        return repository.count() == 0;
    }

    @Override
    public void saveAll(List<Goal> build) {
        build.forEach(GoalServiceImpl::setPicture);
        repository.saveAll(build);
    }

    @Override
    public Goal getById(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void save(Goal entity) {
        setPicture(entity);
        repository.save(entity);
    }

    @Override
    public List<Goal> getAllGoals(User user) {
        return repository.findAllByUser(user);
    }

    @Override
    public List<Goal> getAllGoalsSortedByDate(User user) {
        return repository.findAllByUserOrderByEndDate(user);
    }

    @Override
    public List<Goal> getAllGoalsSortByImportance(User user) {
        List<Goal> allByUser = repository.findAllByUser(user);
        allByUser.sort((g1,g2) -> g2.getImportance().compareTo(g1.getImportance()));
        return allByUser;
    }

    @Override
    public List<Goal> getAllGoalsSortByDifficulty(User user) {
        List<Goal> allByUser = repository.findAllByUser(user);
        allByUser.sort((g1,g2) -> g2.getDifficulty().compareTo(g1.getDifficulty()));
        return allByUser;
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
