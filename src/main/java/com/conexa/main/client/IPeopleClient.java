package com.conexa.main.client;

import com.conexa.main.model.People;
import com.conexa.main.model.SWApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface IPeopleClient {
    @FeignClient(name = "starWarsPeopleClient", url = "${feign.client.starwars}")
    interface PeopleClient extends IGenericClient<People> {
        @Override
        @GetMapping("/people")
        SWApiResponse<People> getAll(
                @RequestParam(required = false) int page,
                @RequestParam(required = false) int limit);
    }
}
