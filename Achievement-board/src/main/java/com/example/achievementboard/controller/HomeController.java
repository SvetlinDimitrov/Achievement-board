package com.example.achievementboard.controller;

import com.example.achievementboard.constants.BaseController;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping()
@Controller
public class HomeController extends BaseController {

    @GetMapping
    public ModelAndView getHome(HttpSession session){
        if(session.getAttribute("user") == null){
            return redirect("/login" , new ModelAndView());
        }
        return setView("home");
    }
}
