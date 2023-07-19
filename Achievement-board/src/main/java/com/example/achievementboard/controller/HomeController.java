package com.example.achievementboard.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/")
@Controller
public class HomeController extends BaseController {

    @GetMapping({"/home",""})
    public ModelAndView getHome(HttpSession session){
        if(session.getAttribute("user") == null){
            return redirect("/login" , new ModelAndView());
        }
        return setView("home");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session){
        session.removeAttribute("user");
        return redirect("/login" , new ModelAndView());
    }
}
