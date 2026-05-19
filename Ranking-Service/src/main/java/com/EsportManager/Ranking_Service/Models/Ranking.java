package com.EsportManager.Ranking_Service.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Entity
@Table(name = "Ranking")
@Getter
@Setter
@NamedEntityGraph
@ToString

public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private long Id;

    @NotNull(message = "Campo TorneoId no puede estar vacio")
    @Column (name = "Torneo_id")
    private long TorneoId;

    @NotNull (message = "Campo ParticipanteId no puede estar vacio")
    @Column (name = "Participante_d")
    private long ParticipanteId;

    @NotNull(message = "Campo Puntos no puede estar vacio")
    @Column (name = "Puntos")
    private long Puntos;


    @NotNull (message = "Campo  Victorias no puede estar vacio")
    @Column (name = "Victorias")
    private String  Victorias;

    @NotNull (message = "Campo Derrotas no puede estar vacio")
    @Column (name = "Ferrotas")
    private String Derrotas;

    @NotNull (message = "Campo  Diferencia no puede estar vacio")
    @Column (name = " Fecha_inscripcion")
    private Date FechaInscripcion;


    @Embedded
    Audit audit = new Audit();
}
