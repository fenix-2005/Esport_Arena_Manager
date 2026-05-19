package com.EsportManager.Result_Service.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "Resultados")
@Getter
@Setter
@NamedEntityGraph
@ToString

public class Resultados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private long Id;

    @NotNull(message = "Campo PartidaId no puede estar vacio")
    @Column (name = "Partida_id")
    private Long PartidaId;

    @NotNull(message = "Campo GanadorId no puede estar vacio")
    @Column (name = "Ganador_id")
    private long GanadorId;

    @NotNull(message = "Campo FechaInicio no puede estar vacio")
    @Column (name = "Fecha_inicio")
    private int PuntajeA;

    @NotNull(message = "Campo FechaFin no puede estar vacio")
    @Column (name = "Fecha_fin")
    private int PuntajeB;

    @NotNull(message = "Campo CupoMaximo no puede estar vacio")
    @Column (name = "Cupo_maximo")
    private int CupoMaximo;

    @NotNull(message = "Campo EstadoValidacion no puede estar vacio")
    @Column (name = "Estado_validacion")
    private boolean estadoValidacion;

    @NotNull(message = "Campo FechaRegistro no puede estar vacio")
    @Column (name = "Fecha_registro")
    private Date FechaRegistro;

    @Embedded
    Audit audit = new Audit();
}
