package com.EsportManager.Result_Service.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

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
    private Long id;

    @NotNull(message = "Campo PartidaId no puede estar vacio")
    @Column (name = "Partida_id")
    private Long partidaId;

    @NotNull(message = "Campo TorneoId no puede estar vacio")
    @Column (name = "Torneo_id")
    private Long torneoId;

    @NotNull(message = "Campo GanadorId no puede estar vacio")
    @Column (name = "Ganador_id")
    private Long ganadorId;

    @NotNull(message = "Campo FechaInicio no puede estar vacio")
    @Column (name = "Fecha_inicio")
    private int puntajeA;

    @NotNull(message = "Campo FechaFin no puede estar vacio")
    @Column (name = "Fecha_fin")
    private int puntajeB;

    @NotNull(message = "Campo CupoMaximo no puede estar vacio")
    @Column (name = "Cupo_maximo")
    private int cupoMaximo;

    @NotNull(message = "Campo EstadoValidacion no puede estar vacio")
    @Column (name = "Estado_validacion")
    private String estadoValidacion;

    @NotNull(message = "Campo FechaRegistro no puede estar vacio")
    @Column (name = "Fecha_registro")
    private LocalDate fechaRegistro;

    @Embedded
    Audit audit = new Audit();
}
