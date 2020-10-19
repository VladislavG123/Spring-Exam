package com.step.filmio.controllers;

import com.step.filmio.models.User;
import com.step.filmio.services.AuthService;
import com.step.filmio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public String login(HttpServletResponse response, Model model, String username, String password){
        var token = userService.loginUser(username, password);
        if(token == null) {
            boolean toSignUp = userService.getUserRepository().findByUsername(username) == null;
            if (toSignUp) {
                model.addAttribute("username", username);
                model.addAttribute("password", password);
            }

            model.addAttribute("logup", toSignUp);

            return "index";
        }

        response.addCookie(new Cookie("token", token));

        model.addAttribute("loggedIn", true);

        return "redirect:/";
    }

    @PostMapping("signup")
    public String signUp(HttpServletResponse response, Model model, String username, String password) {
        userService.getUserRepository().save(new User(username, password));
        var token = userService.loginUser(username, password);


        response.addCookie(new Cookie("token", token));

        model.addAttribute("loggedIn", true);

        return "redirect:/";
    }

    @GetMapping("logoff")
    public String logOff(HttpServletResponse response,
                         Model model, @CookieValue(value = "token", defaultValue = "") String token) {
        if (AuthService.getUser(token, userService) != null){
            var cookie = new Cookie("token", "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        return "redirect:/";
    }

}
