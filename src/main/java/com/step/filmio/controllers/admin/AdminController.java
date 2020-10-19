package com.step.filmio.controllers.admin;

import com.step.filmio.models.Film;
import com.step.filmio.services.AuthService;
import com.step.filmio.services.FilmService;
import com.step.filmio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private FilmService filmService;

    @GetMapping("admin")
    public String index(Model model, @CookieValue(value = "token", defaultValue = "") String token) {

        if (token.equals("") || !AuthService.isAdmin(token, userService)) {
            return "error";
        }

        var films = filmService.getFilmRepository().findAll();

        model.addAttribute("films", films);

        return "admin/index";
    }

    @PostMapping("admin/add_film")
    public String addFilm(Model model, @CookieValue(value = "token", defaultValue = "") String token,
                          String name, String details, String image, String video){
        if (token.equals("") || !AuthService.isAdmin(token, userService)) {
            return "error";
        }

        if (!video.contains("http")) {
            video = "https://www.youtube.com/embed/" + video + "?feature=oembed";
        }

        filmService.getFilmRepository().save(new Film(name, details, image, video));

        return "redirect:" + (index(model, token).contains("admin") ? "" : "/error");
    }

    @GetMapping("admin/delete_film")
    public String deleteFilm(Model model, @CookieValue(value = "token", defaultValue = "") String token,
                            String id){
        if (token.equals("") || !AuthService.isAdmin(token, userService)) {
            return "error";
        }

        filmService.getFilmRepository().deleteById(Long.parseLong(id));

        return "redirect:" + (index(model, token).contains("admin") ? "" : "/error");
    }



}
