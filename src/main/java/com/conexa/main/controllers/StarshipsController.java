package com.conexa.main.controllers;

import com.conexa.main.Services.impl.StarWarsServiceImpl;
import com.conexa.main.model.Starship;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/starship")
public class StarshipsController extends GenericController<Starship> {
    public StarshipsController(StarWarsServiceImpl<Starship> starWarsService) {
        super(starWarsService, "starships");
    }
}
