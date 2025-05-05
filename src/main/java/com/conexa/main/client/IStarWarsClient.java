package com.conexa.main.client;

import com.conexa.main.model.StarWarsApiResponseGetAll;
import com.conexa.main.model.StarWarsApiResponseGetSearch;
import com.conexa.main.model.StarWarsApiResponseGetById;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "startWars", url = "${feign.client.starwars}")
public interface IStarWarsClient {
    @GetMapping("/{resource}")
    StarWarsApiResponseGetAll<?> getAll(
            @PathVariable String resource,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer limit);

    @GetMapping("/{resource}/{id}")
    StarWarsApiResponseGetById<?> getResourceById(
            @PathVariable String resource,
            @PathVariable int id);

    @GetMapping("/{resource}")
    StarWarsApiResponseGetSearch<?> getSearch(
            @PathVariable String resource,
            @RequestParam String name,
            @RequestParam String title);
}
