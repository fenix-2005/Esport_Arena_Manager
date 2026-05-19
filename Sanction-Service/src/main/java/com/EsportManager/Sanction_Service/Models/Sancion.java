package com.EsportManager.Sanction_Service.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

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
    private long Id;

    @NotNull(message = "Campo UsuarioId no puede estar vacio")
    @Column (name = "Usuario_id")
    private long UsuarioId;

    @NotNull (message = "Campo EquipoId no puede estar vacio")
    @Column (name = "Equipo_id")
    private long EquipoId;

    @NotNull(message = "Campo Motivo no puede estar vacio")
    @Column (name = "Motivo")
    private String Motivo;


    @NotNull (message = "Campo FechaInicio no puede estar vacio")
    @Column (name = "Fecha_inicio")
    private Date FechaInicio;

    @NotNull (message = "Campo FechaFin no puede estar vacio")
    @Column (name = "Fecha_fin")
    private Date FechaFin;

    @NotNull (message = "Campo Estado no puede estar vacio")
    @Column (name = "Estado")
    private String Estado;

    @NotNull (message = "Campo Severida no puede estar vacio")
    @Column (name = "Severida")
    private String Severida;


    @Embedded
    Audit audit = new Audit();
}

