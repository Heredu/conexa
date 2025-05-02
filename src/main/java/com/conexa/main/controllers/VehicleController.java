package com.conexa.main.controllers;

import com.conexa.main.Services.impl.StarWarsServiceImpl;
import com.conexa.main.model.Vehicle;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicle")
public class VehicleController extends GenericController<Vehicle> {
    public VehicleController(StarWarsServiceImpl<Vehicle> starWarsService) {
        super(starWarsService, "vehicles", Vehicle.class);
    }
}
