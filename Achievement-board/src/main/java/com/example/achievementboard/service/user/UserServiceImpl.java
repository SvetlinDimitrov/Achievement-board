package com.example.achievementboard.service.user;

import com.example.achievementboard.constants.dtos.LoginUser;
import com.example.achievementboard.constants.dtos.RegisterUser;
import com.example.achievementboard.entity.Achievement;
import com.example.achievementboard.entity.User;
import com.example.achievementboard.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public User getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void save(User entity) {
        repository.save(entity);
    }

    @Override
    public boolean notUsedEmail(String value) {
        return repository.findByEmail(value).isEmpty();
    }

    @Override
    public void saveUser(RegisterUser user) {
        repository.save(user.toUser());
    }

    @Override
    public boolean login(LoginUser user) {
        Optional<User> loggedUser = repository.findByEmail(user.getEmail());
        return loggedUser.isPresent() && loggedUser.get().getPassword().equals(user.getPassword());
    }

    @Override
    public User getByEmail(String email) {
        return repository.findByEmail(email).orElseThrow();
    }
}
