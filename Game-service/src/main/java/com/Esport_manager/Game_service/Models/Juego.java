package com.Esport_manager.Game_service.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Entity
@Table(name = "Juego")
@Getter
@Setter
@NamedEntityGraph
@ToString

public class Juego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;

    @NotNull(message = "Campo Nombre no puede estar vacio")
    @Column (name = "Nombre")
    private String nombre;

    @NotNull (message = "Campo Genero no puede estar vacio")
    @Column (name = "Genero")
    private String genero;

    @NotNull (message = "Campo Modalidad no puede estar vacio")
    @Column (name = "Modalidad")
    private String modalidad;

    @NotNull (message = "Campo JugadoresPorEquipo no puede estar vacio")
    @Column (name = "Jugadores_por_equipo")
    private String jugadoresPorEquipo;

    @NotNull(message = "Campo Email no puede estar vacio")
    @Column (name = "Email")
    private String email;

    @NotNull (message = "Campo Estado no puede estar vacio")
    @Column (name = "Estado")
    private String estado;



    @Embedded
    Audit audit = new Audit();
}
