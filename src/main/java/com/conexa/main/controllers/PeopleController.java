package com.conexa.main.controllers;

import com.conexa.main.Services.impl.StarWarsServiceImpl;
import com.conexa.main.model.People;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
public class PeopleController extends GenericController<People, Integer> {
    public PeopleController(StarWarsServiceImpl<People, Integer> starWarsService) {
        super(starWarsService, "people", People.class);
    }
}
