package com.conexa.main.config;

import com.conexa.main.model.CustomPage;
import com.conexa.main.model.SWApiUnitResponse;
import com.conexa.main.model.SWResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface StarWarsSwagger<T> {
    @Operation(summary = "Obtener lista paginada de recursos",
            description = "Retorna una lista paginada de recursos Star Wars")
    @ApiResponse(responseCode = "200",
            description = "Lista de recursos encontrada",
            content = @Content(schema = @Schema(implementation = CustomPage.class)))
    @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    ResponseEntity<CustomPage<T>> getAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size);

    @Operation(summary = "Obtener recurso por id",
            description = "Retorna recurso Star Wars")
    @ApiResponse(responseCode = "200",
            description = "recurso encontrado",
            content = @Content(schema = @Schema(implementation = SWApiUnitResponse.class)))
    @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    ResponseEntity<SWApiUnitResponse<T>> getById(@PathVariable int id);

    @Operation(summary = "Obtener lista paginada de recursos filtrados por nombre o titulo",
            description = "Retorna recursos filtrados Star Wars")
    @ApiResponse(responseCode = "200",
            description = "Lista de recursos encontrada",
            content = @Content(schema = @Schema(implementation = Page.class)))
    @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    ResponseEntity<Page<SWResult<T>>> search(@RequestParam(required = false) String name, @RequestParam(required = false) String title, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size);

}