package com.example.achievementboard.web.controller;

import com.example.achievementboard.domain.dtos.user.UserView;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/")
@Controller
public class HomeController extends BaseController {

    @GetMapping({"/home",""})
    public ModelAndView getHome(@AuthenticationPrincipal UserView userView,
                                HttpSession session){
        session.setAttribute("user" , userView);
        return setView("home");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session){
        session.removeAttribute("user");
        return redirect("/login" , new ModelAndView());
    }
}
