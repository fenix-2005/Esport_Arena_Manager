package com.EsportManager.Sanction_Service.Models.Dtos;

import lombok.Data;
import java.sql.Date;

@Data
public class SancionDTO {
    private Long id;
    private Long usuarioId;
    private Long equipoId;
    private String motivo;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;
    private String severida;
}
