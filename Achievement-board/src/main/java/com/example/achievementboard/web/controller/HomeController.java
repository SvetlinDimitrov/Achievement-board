package com.example.achievementboard.web.controller;

import com.example.achievementboard.domain.dtos.user.RegisterUser;
import com.example.achievementboard.domain.dtos.user.UserView;
import com.example.achievementboard.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping
@Controller
@AllArgsConstructor
public class HomeController extends BaseController {

    private UserService userService;

    @GetMapping({"/home","/"})
    public ModelAndView getHome(@AuthenticationPrincipal UserView userView,
                                HttpSession session){
        session.setAttribute("user" , userView);
        return setView("home");
    }
    @GetMapping("/login")
    public ModelAndView getLoginPage(ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session){
        session.removeAttribute("user");
        return redirect("/login" , new ModelAndView());
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage(@ModelAttribute(name = "registerUser") RegisterUser user) {
        return setView("register");
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute(name = "registerUser")RegisterUser user,
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
