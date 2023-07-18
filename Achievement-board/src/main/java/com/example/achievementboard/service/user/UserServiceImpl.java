package com.example.achievementboard.service.user;

import com.example.achievementboard.entity.Achievement;
import com.example.achievementboard.entity.User;
import com.example.achievementboard.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public boolean isEmpty() {
        return repository.count() == 0;
    }

    @Override
    public void saveAll(List<User> build) {
        repository.saveAll(build);
    }

    @Override
    @Transactional
    public User getById(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void save(User entity) {
        repository.save(entity);
    }
}
