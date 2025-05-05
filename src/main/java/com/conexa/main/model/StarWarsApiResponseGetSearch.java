package com.conexa.main.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class StarWarsApiResponseGetSearch<T> {
    @JsonAlias({"result", "results"})
    private List<SWResult<T>> result;

}
