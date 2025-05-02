package com.conexa.main.controllers;

import com.conexa.main.Services.impl.StarWarsServiceImpl;
import com.conexa.main.model.CustomPage;
import com.conexa.main.model.StarWarsResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class GenericController<T extends StarWarsResource> {
    private final StarWarsServiceImpl starWarsService;
    private final String resourceName;
    private final Class<T> resourceType;

    public GenericController(StarWarsServiceImpl starWarsService, String resourceName, Class<T> resourceType) {
        this.starWarsService = starWarsService;
        this.resourceName = resourceName;
        this.resourceType = resourceType;
    }

    @GetMapping
    public ResponseEntity<CustomPage<T>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        CustomPage<T> result = starWarsService.getAll(pageable, resourceName, resourceType);
        return ResponseEntity.ok(result);
    }
}
