package com.conexa.main.client;

import com.conexa.main.model.SWApiResponse;
import com.conexa.main.model.SWApiUnitListResponse;
import com.conexa.main.model.SWApiUnitResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "startWars", url = "${feign.client.starwars}")
public interface IStarWarsClient {
    @GetMapping("/{resource}")
    SWApiResponse<?> getAll(
            @PathVariable String resource,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer limit);

    @GetMapping("/{resource}/{id}")
    SWApiUnitResponse<?> getResourceById(
            @PathVariable String resource,
            @PathVariable int id);

    @GetMapping("/{resource}")
    SWApiUnitListResponse<?> getSearch(
            @PathVariable String resource,
            @RequestParam String name);
}
