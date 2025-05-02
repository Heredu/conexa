package com.conexa.main.controllers;

import com.conexa.main.Services.impl.StarWarsServiceImpl;
import com.conexa.main.model.Film;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/film")
public class FilmController extends GenericController<Film> {
    public FilmController(StarWarsServiceImpl<Film> starWarsService) {
        super(starWarsService, "films", Film.class);
    }
}
