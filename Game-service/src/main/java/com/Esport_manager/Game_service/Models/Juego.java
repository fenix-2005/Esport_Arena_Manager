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
    private long Id;

    @NotNull(message = "Campo Nombre no puede estar vacio")
    @Column (name = "Nombre")
    private String Nombre;

    @NotNull (message = "Campo Genero no puede estar vacio")
    @Column (name = "Genero")
    private String Genero;

    @NotNull (message = "Campo Modalidad no puede estar vacio")
    @Column (name = "Modalidad")
    private String Modalidad;

    @NotNull (message = "Campo JugadoresPorEquipo no puede estar vacio")
    @Column (name = "Jugadores_por_equipo")
    private String JugadoresPorEquipo;

    @NotNull(message = "Campo Email no puede estar vacio")
    @Column (name = "Email")
    private String Email;

    @NotNull (message = "Campo Estado no puede estar vacio")
    @Column (name = "Estado")
    private String Estado;



    @Embedded
    Audit audit = new Audit();
}
