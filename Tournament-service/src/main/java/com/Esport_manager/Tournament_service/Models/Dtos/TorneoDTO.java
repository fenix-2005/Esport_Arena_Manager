package com.Esport_manager.Tournament_service.Models.Dtos;

import lombok.Data;
import java.util.Date;

@Data
public class TorneoDTO {
    private Long id;
    private String nombre;
    private Long juegoId;
    private Date fechaInicio;
    private Date fechaFin;
    private int cupoMaximo;
    private String estado;
    private String modalidad;
}
