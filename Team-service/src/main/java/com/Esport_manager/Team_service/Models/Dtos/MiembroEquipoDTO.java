package com.Esport_manager.Team_service.Models.Dtos;

import lombok.Data;

@Data
public class MiembroEquipoDTO {
    private Long id;
    private Long equipoId;
    private Long usuarioId;
    private Long rolEquipo;
}
