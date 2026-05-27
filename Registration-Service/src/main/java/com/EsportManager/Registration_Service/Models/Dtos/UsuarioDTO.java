package com.EsportManager.Registration_Service.Models.Dtos;

import lombok.Data;
import java.sql.Date;

@Data
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String nickname;
    private String email;
    private String rol;
    private String estado;
    private Date fechaRegistro;
}
