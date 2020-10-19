package com.step.filmio.controllers;

import com.step.filmio.models.Comment;
import com.step.filmio.models.Film;
import com.step.filmio.models.Role;
import com.step.filmio.models.User;
import com.step.filmio.services.AuthService;
import com.step.filmio.services.CommentService;
import com.step.filmio.services.FilmService;
import com.step.filmio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class FilmController {
    @Autowired
    private UserService userService;

    @Autowired
    private FilmService filmService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/film")
    public String getFilm(Model model, @CookieValue(value = "token", defaultValue = "") String token,
                          String id) {
        if (!token.equals("")) {
            model.addAttribute("admin", AuthService.isAdmin(token, userService));

            model.addAttribute("loggedIn", true);
        }

        var film = filmService.getFilmRepository()
                .findById(Long.parseLong(id)).orElse(new Film());


        var comments = new ArrayList<CommentViewModel>();

        for (var comment:  commentService.getCommentRepository().findAllByFilmId(film.getId())) {
            comments.add(new CommentViewModel(comment.getId(),
                    comment.getContent(),
                    userService.getUserRepository().findById(comment.getUserId()).orElse(new User()).getUsername()));

        }

        
        model.addAttribute("film", film);
        model.addAttribute("comments", comments);

        return "film";
    }



    @PostMapping("/comment/add")
    public String addComment(Model model, @CookieValue(value = "token", defaultValue = "") String token,
                             String filmId, String content){

        if (!token.equals("")) {
            var user = AuthService.getUser(token, userService);
            commentService.getCommentRepository().save(new Comment(content, Long.parseLong(filmId), user.getId()));
        }

        return getFilm(model, token, filmId);
    }

    @PostMapping("/comment/delete")
    public String deleteComment(Model model, @CookieValue(value = "token", defaultValue = "") String token,
                                String id, String filmId){
        if (!token.equals("") && AuthService.isAdmin(token, userService)) {
            commentService.getCommentRepository().deleteById(Long.parseLong(id));
        }

        return getFilm(model, token, filmId);

    }
}
