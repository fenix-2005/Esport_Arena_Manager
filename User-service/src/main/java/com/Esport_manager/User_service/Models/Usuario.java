package com.Esport_manager.User_service.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Entity
@Table(name = "Usuario")
@Getter
@Setter
@NamedEntityGraph
@ToString

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;

    @NotNull (message = "Campo Nombre no puede estar vacio")
    @Column (name = "Nombre")
    private String nombre;

    @NotNull (message = "Campo Nickname no puede estar vacio")
    @Column (name = "Nickname")
    private String nickname;

    @NotNull(message = "Campo Email no puede estar vacio")
    @Column (name = "Email")
    private String email;


    @NotNull (message = "Campo Rol no puede estar vacio")
    @Column (name = "Rol")
    private String rol;

    @NotNull (message = "Campo Estado no puede estar vacio")
    @Column (name = "Estado")
    private String estado;

    @NotNull (message = "Campo fechaRegistro no puede estar vacio")
    @Column (name = "fecha_registro")
    private Date fechaRegistro;


    @Embedded
    Audit audit = new Audit();
}

