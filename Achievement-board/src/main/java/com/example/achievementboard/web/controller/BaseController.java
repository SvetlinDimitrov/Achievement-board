package com.example.achievementboard.web.controller;

import com.example.achievementboard.domain.dtos.user.UserView;
import com.example.achievementboard.domain.entity.UserEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
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

}
