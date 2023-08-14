package com.example.achievementboard.service.user;


import com.example.achievementboard.domain.dtos.user.LoginUser;
import com.example.achievementboard.domain.dtos.user.RegisterUser;
import com.example.achievementboard.domain.dtos.user.UserView;
import com.example.achievementboard.domain.entity.UserEntity;
import com.example.achievementboard.service.BaseService;

public interface UserService extends BaseService<UserEntity> {

    boolean notUsedEmail(String value);

    void saveUser(RegisterUser user);


    UserView getByEmail(String email);
}
