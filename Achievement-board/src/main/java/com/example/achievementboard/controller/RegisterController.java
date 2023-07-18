package com.example.achievementboard.controller;

import com.example.achievementboard.constants.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/register")
@Controller
public class RegisterController extends BaseController {
    @GetMapping
    public ModelAndView getRegisterPage() {
        return setView("register");
    }
}
