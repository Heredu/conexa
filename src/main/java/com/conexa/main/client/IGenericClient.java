package com.conexa.main.client;

import com.conexa.main.model.SWApiResponse;
import com.conexa.main.model.StarWarsResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "startWars", url = "${feign.client.starwars}")
public interface IGenericClient<T extends StarWarsResource> {
    @GetMapping("/{resource}")
    SWApiResponse<T> getAll(
            @RequestParam(required = false) int page,
            @RequestParam(required = false) int limit);
}
