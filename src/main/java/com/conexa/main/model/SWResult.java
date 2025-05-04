package com.conexa.main.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SWResult <T>{
    private String _id;
    private String description;
    private String uid;
    private T properties;
}
