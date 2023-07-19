package com.example.achievementboard.controller;

import com.example.achievementboard.constants.dtos.RegisterUser;
import com.example.achievementboard.service.user.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/register")
@Controller
@AllArgsConstructor
public class RegisterController extends BaseController {
    private UserService userService;
    @GetMapping
    public ModelAndView getRegisterPage(@ModelAttribute(name = "registerUser")RegisterUser user) {
        return setView("register");
    }

    @PostMapping
    public ModelAndView register(@Valid  @ModelAttribute(name = "registerUser")RegisterUser user,
                                 BindingResult result,
                                 ModelAndView modelAndView
    ) {
        if(result.hasErrors()){
            modelAndView.setViewName("register");
            return modelAndView;
        }

        userService.saveUser(user);

        return redirect("/login" , modelAndView);
    }
}
