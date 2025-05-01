package com.conexa.main.controllers;

import com.conexa.main.Services.impl.GenericServiceImpl;
import com.conexa.main.model.People;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
public class PeopleController extends GenericController<People, Integer> {
    protected PeopleController(GenericServiceImpl<People, Integer> service) {
        super(service);
    }
}
