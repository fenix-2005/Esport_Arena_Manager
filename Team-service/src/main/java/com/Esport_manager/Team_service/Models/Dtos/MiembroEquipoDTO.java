package com.Esport_manager.Team_service.Models.Dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MiembroEquipoDTO {
    private Long id;

    @NotNull(message = "El ID del equipo no puede ser nulo")
    private Long equipoId;

    @NotNull(message = "El ID del usuario no puede ser nulo")
    private Long usuarioId;

    @NotNull(message = "El rol en el equipo no puede ser nulo")
    private Long rolEquipo;
}
