package com.example.achievementboard.service.achievement;

import com.example.achievementboard.entity.Achievement;
import com.example.achievementboard.entity.User;
import com.example.achievementboard.repos.AchievementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AchievementServiceImpl implements AchievementService {
    private final AchievementRepository repository;

    @Override
    public boolean isEmpty() {
        return repository.count() == 0;
    }

    @Override
    public void saveAll(List<Achievement> build) {
        repository.saveAll(build);
    }

    @Override
    public Achievement getById(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void save(Achievement entity) {
        repository.save(entity);
    }
}
