package com.EsportManager.Registration_Service.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

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
    private Long id;

    @NotNull(message = "Campo TorneoId no puede estar vacio")
    @Column (name = "Torneo_id")
    private Long torneoId;

    @NotNull (message = "Campo EquipoId no puede estar vacio")
    @Column (name = "Equipo_id")
    private Long equipoId;

    @NotNull(message = "Campo JugadorId no puede estar vacio")
    @Column (name = "Jugador_id")
    private Long jugadorId;


    @NotNull (message = "Campo  TipoParticipante no puede estar vacio")
    @Column (name = "Tipo_participante")
    private String  tipoParticipante;

    @NotNull (message = "Campo Estado no puede estar vacio")
    @Column (name = "Estado")
    private String estado;

    @NotNull (message = "Campo  FechaInscripcion no puede estar vacio")
    @Column (name = " Fecha_inscripcion")
    private LocalDate  fechaInscripcion;


    @Embedded
    Audit audit = new Audit();
}
