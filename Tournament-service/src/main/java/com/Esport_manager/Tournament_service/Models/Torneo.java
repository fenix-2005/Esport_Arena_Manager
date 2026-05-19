package com.Esport_manager.Tournament_service.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "Torneo")
@Getter
@Setter
@NamedEntityGraph
@ToString

public class Torneo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private long Id;

    @NotNull(message = "Campo Nombre no puede estar vacio")
    @Column (name = "Nombre")
    private String Nombre;

    @NotNull(message = "Campo JuegoId no puede estar vacio")
    @Column (name = "JuegoId")
    private long JuegoId;

    @NotNull(message = "Campo FechaInicio no puede estar vacio")
    @Column (name = "FechaInicio")
    private Date FechaInicio;

    @NotNull(message = "Campo FechaFin no puede estar vacio")
    @Column (name = "FechaFin")
    private Date FechaFin;

    @NotNull(message = "Campo CupoMaximo no puede estar vacio")
    @Column (name = "CupoMaximo")
    private int CupoMaximo;

    @NotNull(message = "Campo Estado no puede estar vacio")
    @Column (name = "Estado")
    private boolean estado;

    @NotNull(message = "Campo Modalidad no puede estar vacio")
    @Column (name = "Modalidad")
    private String Modalidad;

    @Embedded
    Audit audit = new Audit();
}
