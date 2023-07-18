package com.example.achievementboard.constants;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class BaseController {
    public ModelAndView setView(String viewName , ModelAndView modelAndView){
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    public ModelAndView setView(String viewName){
        return  setView(viewName , new ModelAndView());
    }

    public ModelAndView redirect(String redirect , ModelAndView modelAndView){
        modelAndView.setViewName("redirect:" + redirect);
        return modelAndView;
    }


}
