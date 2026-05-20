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
    private Long id;

    @NotNull(message = "Campo Nombre no puede estar vacio")
    @Column (name = "Nombre")
    private String nombre;

    @NotNull(message = "Campo JuegoId no puede estar vacio")
    @Column (name = "Juego_id")
    private Long juegoId;

    @NotNull(message = "Campo FechaInicio no puede estar vacio")
    @Column (name = "Fecha_inicio")
    private Date fechaInicio;

    @NotNull(message = "Campo FechaFin no puede estar vacio")
    @Column (name = "Fecha_fin")
    private Date fechaFin;

    @NotNull(message = "Campo CupoMaximo no puede estar vacio")
    @Column (name = "Cupo_maximo")
    private int cupoMaximo;

    @NotNull(message = "Campo Estado no puede estar vacio")
    @Column (name = "Estado")
    private boolean estado;

    @NotNull(message = "Campo Modalidad no puede estar vacio")
    @Column (name = "Modalidad")
    private String modalidad;

    @Embedded
    Audit audit = new Audit();
}
