package com.Esport_manager.Auth_service.Models.Dtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CuentaAccesoDTO {
    private Long id;
    private String email;
    private String password;
    private String rol;
    private String estado;
    private LocalDate fechaCreacion;
}
