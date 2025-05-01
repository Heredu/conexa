package com.conexa.main.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class StarWarsResource {
    private String url;
    private Date created;
    private Date edited;
}
