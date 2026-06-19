package com.Esport_manager.Game_service.Models.Dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class JuegoDTO {
    private Long id;
    private String nombre;
    private String genero;
    private String modalidad;
    private int jugadoresPorEquipo;
    private String email;
    private String estado;
}
