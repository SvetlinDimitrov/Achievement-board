package com.example.achievementboard.web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/")
@Controller
public class HomeController extends BaseController {

    @GetMapping({"/home",""})
    public ModelAndView getHome(){
        return setView("home");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session){
        session.removeAttribute("userEntity");
        return redirect("/login" , new ModelAndView());
    }
}
