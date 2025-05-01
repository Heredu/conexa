package com.conexa.main.model;

import java.util.Date;
import java.util.List;

public class Film extends StarWarsResource {
    private String title;
    private int episodeId;
    private String openingCrawl;
    private String director;
    private String producer; // Puede contener múltiples nombres separados por comas
    private Date releaseDate;
    private List<String> species; // URLs de especies
    private List<String> starships; // URLs de naves espaciales
    private List<String> vehicles; // URLs de vehículos
    private List<String> characters; // URLs de personajes
    private List<String> planets; // URLs de planetas
}
