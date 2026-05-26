package com.Esport_manager.Team_service.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "Equipo")
@Getter
@Setter
@NamedEntityGraph
@ToString

public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;

    @NotNull(message = "Campo Nombre no puede estar vacio")
    @Column (name = "Nombre")
    private String nombre;

    @NotNull(message = "Campo CapitanId no puede estar vacio")
    @Column (name = "Capitan_id")
    private Long capitanId;

    @NotNull(message = "Campo JuegoPrincipalId no puede estar vacio")
    @Column (name = "Juego_principal_id")
    private Long juegoPrincipalId;


    @NotNull(message = "Campo Estado no puede estar vacio")
    @Column (name = "Estado")
    private String estado;

    @Embedded
    Audit audit = new Audit();
}

