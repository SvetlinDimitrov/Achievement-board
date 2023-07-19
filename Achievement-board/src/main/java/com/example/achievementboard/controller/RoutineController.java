package com.example.achievementboard.controller;

import com.example.achievementboard.entity.User;
import com.example.achievementboard.service.routine.RoutineService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/routine")
@AllArgsConstructor
public class RoutineController extends BaseController{
    private final RoutineService service;

    @GetMapping
    public ModelAndView getRoutinePage(HttpSession session , ModelAndView modelAndView){
        if(session.getAttribute("user") == null){
            return redirect("/login" , new ModelAndView());
        }
        User user = getUser(session);

        modelAndView.addObject("allRoutines" , service.getAllRoutines(user));
        return setView("allRoutines",modelAndView);
    }

    @GetMapping("/sortByDifficulty")
    public ModelAndView getRoutinePageSortedByDifficulty(HttpSession session , ModelAndView modelAndView){
        if(session.getAttribute("user") == null){
            return redirect("/login" , new ModelAndView());
        }
        User user = getUser(session);

        modelAndView.addObject("allRoutines" , service.getAllRoutinesSortedByDifficulty(user));
        return setView("allRoutines",modelAndView);
    }

    @GetMapping("/sortByHourSpend")
    public ModelAndView getRoutinePageSortedByHourSpend(HttpSession session , ModelAndView modelAndView){
        if(session.getAttribute("user") == null){
            return redirect("/login" , new ModelAndView());
        }
        User user = getUser(session);

        modelAndView.addObject("allRoutines" , service.getAllRoutinesSortByHourSpend(user));
        return setView("allRoutines",modelAndView);
    }
}
