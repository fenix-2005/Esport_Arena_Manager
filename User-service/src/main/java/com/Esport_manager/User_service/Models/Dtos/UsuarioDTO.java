package com.Esport_manager.User_service.Models.Dtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String nickname;
    private String email;
    private String rol;
    private String estado;
    private LocalDate fechaRegistro;
}
