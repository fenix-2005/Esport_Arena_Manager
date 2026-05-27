package com.EsportManager.Ranking_Service.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// No date imports needed if no Date fields are used.

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
    private Long id;

    @NotNull(message = "Campo TorneoId no puede estar vacio")
    @Column (name = "Torneo_id")
    private Long torneoId;

    @NotNull (message = "Campo ParticipanteId no puede estar vacio")
    @Column (name = "Participante_id")
    private Long participanteId;

    @NotNull(message = "Campo Puntos no puede estar vacio")
    @Column (name = "Puntos")
    private String puntos;

    @NotNull (message = "Campo  Victorias no puede estar vacio")
    @Column (name = "Victorias")
    private String  victorias;

    @NotNull (message = "Campo Derrotas no puede estar vacio")
    @Column (name = "Derrotas")
    private String derrotas;

    @NotNull (message = "Campo Diferencia no puede estar vacio")
    @Column (name = "Diferencia")
    private String diferencia;

    @NotNull (message = "Campo Posicion no puede estar vacio")
    @Column (name = "Posicion")
    private int posicion;


    @Embedded
    Audit audit = new Audit();
}
