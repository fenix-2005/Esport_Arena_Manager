package com.Esport_manager.Tournament_service.Models.Dtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TorneoDTO {
    private Long id;
    private String nombre;
    private Long juegoId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int cupoMaximo;
    private String estado;
    private String modalidad;
}
