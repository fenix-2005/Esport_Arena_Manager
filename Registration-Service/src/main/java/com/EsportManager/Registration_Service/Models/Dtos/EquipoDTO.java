package com.EsportManager.Registration_Service.Models.Dtos;

import lombok.Data;

@Data
public class EquipoDTO {
    private Long id;
    private String nombre;
    private Long capitanId;
    private Long juegoPrincipalId;
    private String estado;
}
