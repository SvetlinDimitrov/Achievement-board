package com.example.achievementboard.service.user;


import com.example.achievementboard.constants.dtos.LoginUser;
import com.example.achievementboard.constants.dtos.RegisterUser;
import com.example.achievementboard.service.BaseService;
import com.example.achievementboard.entity.User;

public interface UserService extends BaseService<User> {

    boolean notUsedEmail(String value);

    void saveUser(RegisterUser user);

    boolean login(LoginUser user);
}
