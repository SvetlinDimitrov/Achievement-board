package com.example.achievementboard.controller;

import com.example.achievementboard.constants.BaseController;
import com.example.achievementboard.constants.dtos.LoginUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    @GetMapping
    public ModelAndView getLoginPage(@ModelAttribute ("loginUser") LoginUser user) {
        return setView("login");
    }

    @PostMapping
    public ModelAndView login(@Valid @ModelAttribute("loginUser") LoginUser user,
                              BindingResult result,
                              HttpSession session
                              ) {

        if(result.hasErrors()){
            return setView("login");
        }

        session.setAttribute("user" , user);
        return redirect("/" , new ModelAndView());
    }
}
