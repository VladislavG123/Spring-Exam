package com.step.filmio.services;

import com.step.filmio.data.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    public FilmRepository getFilmRepository() {
        return filmRepository;
    }



}
