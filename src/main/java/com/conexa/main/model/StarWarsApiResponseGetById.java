package com.conexa.main.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StarWarsApiResponseGetById<T> {
    private SWResult<T> result;
}
