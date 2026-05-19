package com.EsportManager.Registration_Service.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Entity
@Table(name = "Inscripcion")
@Getter
@Setter
@NamedEntityGraph
@ToString

public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private long Id;

    @NotNull(message = "Campo TorneoId no puede estar vacio")
    @Column (name = "Torneo_id")
    private long TorneoId;

    @NotNull (message = "Campo EquipoId no puede estar vacio")
    @Column (name = "Equipo_id")
    private long EquipoId;

    @NotNull(message = "Campo JugadorId no puede estar vacio")
    @Column (name = "Jugador_id")
    private long JugadorId;


    @NotNull (message = "Campo  TipoParticipante no puede estar vacio")
    @Column (name = "Tipo_participante")
    private String  TipoParticipante;

    @NotNull (message = "Campo Estado no puede estar vacio")
    @Column (name = "Estado")
    private String Estado;

    @NotNull (message = "Campo  FechaInscripcion no puede estar vacio")
    @Column (name = " Fecha_inscripcion")
    private Date  FechaInscripcion;


    @Embedded
    Audit audit = new Audit();
}
