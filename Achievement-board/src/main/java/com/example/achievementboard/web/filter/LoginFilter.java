package com.example.achievementboard.web.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain)
            throws IOException, ServletException {

        HttpSession session = request.getSession();
        String requestURL = request.getRequestURL().toString();

        if(session.getAttribute("user") == null && !requestURL.equals("http://localhost:8080/register")){
            request.getRequestDispatcher("/login").forward(request , response);
        }else{
            super.doFilter(request, response, chain);
        }
    }

}
