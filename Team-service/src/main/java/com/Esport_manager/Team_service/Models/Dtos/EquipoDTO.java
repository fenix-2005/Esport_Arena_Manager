package com.Esport_manager.Team_service.Models.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EquipoDTO {
    private Long id;

    @NotBlank(message = "El nombre del equipo no puede estar vacío")
    private String nombre;

    @NotNull(message = "El ID del capitán no puede ser nulo")
    private Long capitanId;

    @NotNull(message = "El ID del juego principal no puede ser nulo")
    private Long juegoPrincipalId;

    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;
}
