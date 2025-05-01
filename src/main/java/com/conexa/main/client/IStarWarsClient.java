package com.conexa.main.client;

import com.conexa.main.model.Film;
import com.conexa.main.model.People;
import com.conexa.main.model.Startship;
import com.conexa.main.model.Vehicle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "startWars", url = "${feign.client.starwars}")
public interface IStarWarsClient {
    @GetMapping("/people")
    Page<People> getPeoples();
    @GetMapping("/films")
    Page<Film> getFilms();
    @GetMapping("/starships")
    Page<Startship> getStarships();
    @GetMapping("/vehicles")
    Page<Vehicle> getVehicles();
}
