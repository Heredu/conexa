package com.conexa.main.controllers;

import com.conexa.main.Services.impl.StarWarsServiceImpl;
import com.conexa.main.config.StarWarsSwagger;
import com.conexa.main.model.CustomPage;
import com.conexa.main.model.SWApiUnitResponse;
import com.conexa.main.model.SWResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class GenericController<T> implements StarWarsSwagger<T> {
    private final StarWarsServiceImpl<T> starWarsService;
    private final String resourceName;

    public GenericController(StarWarsServiceImpl<T> starWarsService, String resourceName) {
        this.starWarsService = starWarsService;
        this.resourceName = resourceName;
    }

    @GetMapping
    public ResponseEntity<CustomPage<T>> getAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        CustomPage<T> result = starWarsService.getAll(pageable, resourceName);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SWApiUnitResponse<T>> getById(@PathVariable int id) {
        SWApiUnitResponse<T> result = starWarsService.getById(id, resourceName);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<SWResult<T>>> search(@RequestParam(required = false,defaultValue = " ") String name, @RequestParam(required = false,defaultValue = " ") String title, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<SWResult<T>> response = starWarsService.search(name, title, page, size, resourceName);
        return ResponseEntity.ok().body(response);
    }
}
