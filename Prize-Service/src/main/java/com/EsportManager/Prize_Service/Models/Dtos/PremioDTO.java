package com.EsportManager.Prize_Service.Models.Dtos;

import lombok.Data;

@Data
public class PremioDTO {
    private Long id;
    private Long torneoId;
    private int posicion;
    private String descripcion;
    private String valor;
    private String estado;
}
