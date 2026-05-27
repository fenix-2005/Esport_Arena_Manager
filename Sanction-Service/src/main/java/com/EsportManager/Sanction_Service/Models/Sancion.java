package com.EsportManager.Sanction_Service.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "Sancion")
@Getter
@Setter
@NamedEntityGraph
@ToString

public class Sancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;

    @NotNull(message = "Campo UsuarioId no puede estar vacio")
    @Column (name = "Usuario_id")
    private Long usuarioId;

    @NotNull (message = "Campo EquipoId no puede estar vacio")
    @Column (name = "Equipo_id")
    private Long equipoId;

    @NotNull(message = "Campo Motivo no puede estar vacio")
    @Column (name = "Motivo")
    private String motivo;


    @NotNull (message = "Campo FechaInicio no puede estar vacio")
    @Column (name = "Fecha_inicio")
    private LocalDate fechaInicio;

    @NotNull (message = "Campo FechaFin no puede estar vacio")
    @Column (name = "Fecha_fin")
    private LocalDate fechaFin;

    @NotNull (message = "Campo Estado no puede estar vacio")
    @Column (name = "Estado")
    private String estado;

    @NotNull (message = "Campo Severida no puede estar vacio")
    @Column (name = "Severida")
    private String severida;


    @Embedded
    Audit audit = new Audit();
}

