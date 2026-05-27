package com.Esport_manager.Team_service.Models.Dtos;

import lombok.Data;

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
