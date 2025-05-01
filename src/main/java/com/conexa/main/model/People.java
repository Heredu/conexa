package com.conexa.main.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class People extends StarWarsResource {
    private String name;
    private String birthYear; // Formato ABY/BBY
    private String eyeColor;
    private String gender;
    private String hairColor;
    private String height; // En centímetros
    private String mass;   // En kilogramos
    private String skinColor;
    private String homeworld; // URL del planeta
    private List<String> films; // URLs de películas
    private List<String> species; // URLs de especies
    private List<String> starships; // URLs de naves espaciales
    private List<String> vehicles; // URLs de vehículos
}
