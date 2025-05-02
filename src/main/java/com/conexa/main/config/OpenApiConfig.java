package com.conexa.main.config;

import com.conexa.main.model.Film;
import com.conexa.main.model.People;
import com.conexa.main.model.Starship;
import com.conexa.main.model.Vehicle;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Star Wars API")
                        .version("1.0")
                        .description("API para consultar información del universo Star Wars"))
                .components(new Components()
                        .addSchemas("Person", new Schema<People>())
                        .addSchemas("Film", new Schema<Film>())
                        .addSchemas("Starship", new Schema<Starship>())
                        .addSchemas("vehicle", new Schema<Vehicle>()));
    }
}
