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
    private long Id;

    @NotNull (message = "Campo Nombre no puede estar vacio")
    @Column (name = "Nombre")
    private String Nombre;

    @NotNull (message = "Campo Nickname no puede estar vacio")
    @Column (name = "Nickname")
    private String Nickname;

    @NotNull(message = "Campo Email no puede estar vacio")
    @Column (name = "Email")
    private String Email;


    @NotNull (message = "Campo Rol no puede estar vacio")
    @Column (name = "Rol")
    private String Rol;

    @NotNull (message = "Campo Estado no puede estar vacio")
    @Column (name = "Estado")
    private String Estado;

    @NotNull (message = "Campo fechaRegistro no puede estar vacio")
    @Column (name = "fecha_registro")
    private Date fechaRegistro;


    @Embedded
    Audit audit = new Audit();
}

