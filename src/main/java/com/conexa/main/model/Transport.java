package com.conexa.main.model;

import java.util.List;

public abstract class Transport extends StarWarsResource {
    private String name;
    private String model;
    private String manufacturer;
    private String costInCredits;
    private String length;
    private String crew;
    private String passengers;
    private String maxAtmospheringSpeed;
    private String cargoCapacity;
    private String consumables;
    private List<String> pilots;
}
