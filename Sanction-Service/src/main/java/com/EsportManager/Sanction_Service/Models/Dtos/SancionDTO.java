package com.EsportManager.Sanction_Service.Models.Dtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class SancionDTO {
    private Long id;
    private Long usuarioId;
    private Long equipoId;
    private String motivo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;
    private String severida;
}
