package com.example.achievementboard.web.controller;

import com.example.achievementboard.domain.constants.exception.GoalNotFoundException;
import com.example.achievementboard.domain.constants.exception.RoutineNotFoundException;
import com.example.achievementboard.domain.constants.exception.UserNotFoundException;
import com.example.achievementboard.domain.dtos.user.UserView;
import com.example.achievementboard.domain.entity.UserEntity;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class BaseController {
    protected ModelAndView setView(String viewName , ModelAndView modelAndView){
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    protected ModelAndView setView(String viewName){
        return  setView(viewName , new ModelAndView());
    }

    protected ModelAndView redirect(String redirect , ModelAndView modelAndView){
        modelAndView.setViewName("redirect:" + redirect);
        return modelAndView;
    }
    protected UserView getUser(HttpSession session){
        return (UserView) session.getAttribute("user");
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView catchUserNotFound(HttpServletResponse response ,
                                          UsernameNotFoundException exception){

        ModelAndView modelAndView = new ModelAndView();
        response.setStatus(500);
        modelAndView.setViewName("UserNotFound");
        return modelAndView;

    }

    @ExceptionHandler(GoalNotFoundException.class)
    public ModelAndView catchUserNotFound(HttpServletResponse response ,
                                          GoalNotFoundException exception){

        ModelAndView modelAndView = new ModelAndView();
        response.setStatus(500);
        modelAndView.setViewName("goalNotFound");
        return modelAndView;

    }

    @ExceptionHandler(RoutineNotFoundException.class)
    public ModelAndView catchUserNotFound(HttpServletResponse response ,
                                          RoutineNotFoundException exception){

        ModelAndView modelAndView = new ModelAndView();
        response.setStatus(500);
        modelAndView.setViewName("RoutineNotFound");
        return modelAndView;

    }

}
