package com.example.achievementboard.service.goal;

import com.example.achievementboard.entity.Goal;
import com.example.achievementboard.repos.GoalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        repository.saveAll(build);
    }

    @Override
    public Goal getById(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void save(Goal entity) {
        repository.save(entity);
    }
}
