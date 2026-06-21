package com.Esport_manager.Auth_service.Models.Dtos;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String email;
    private String password;
    private String rol;
    private String estado;
    private String token;
    private String nombre;
}
