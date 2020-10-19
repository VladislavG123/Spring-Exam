package com.step.filmio.controllers;

import com.step.filmio.models.Film;
import com.step.filmio.models.Role;
import com.step.filmio.models.User;
import com.step.filmio.services.AuthService;
import com.step.filmio.services.FilmService;
import com.step.filmio.services.TokenGenerationService;
import com.step.filmio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private FilmService filmService;

    @GetMapping("/")
    public String index(Model model, @CookieValue(value = "token", defaultValue = "") String token) {
        if (!token.equals("")) {
            if (AuthService.isAdmin(token, userService)){
                model.addAttribute("admin", true);
            }

            model.addAttribute("loggedIn", true);
        }

        model.addAttribute("films", filmService.getFilmRepository().findAll());

        return "index";
    }





}
