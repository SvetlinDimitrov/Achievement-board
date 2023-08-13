package com.example.achievementboard.web.controller;

import com.example.achievementboard.domain.dtos.user.LoginUser;
import com.example.achievementboard.domain.dtos.user.UserView;
import com.example.achievementboard.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/login")
public class LoginController extends BaseController {

    private UserService userService;

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

        if(!userService.login(user)){
            FieldError error = new FieldError("loginUser" ,"email","Wrong password or email");
            result.addError(error);
            return setView("login");
        }

        UserView userView = userService.getByEmail(user.getEmail());
        session.setAttribute("userEntity" , userView);

        return redirect("/" , new ModelAndView());
    }
}
