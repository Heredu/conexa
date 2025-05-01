package com.conexa.main.controllers;

import com.conexa.main.Services.impl.GenericServiceImpl;
import com.conexa.main.model.CustomPage;
import com.conexa.main.model.StarWarsResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class GenericController<T extends StarWarsResource, ID> {

    private final GenericServiceImpl<T, ID> service;

    protected GenericController(GenericServiceImpl<T, ID> service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<CustomPage<T>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        CustomPage<T> result = service.getAll(pageable);
        return ResponseEntity.ok(result);
    }
}
