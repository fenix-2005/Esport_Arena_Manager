package com.EsportManager.Prize_Service.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Entity
@Table(name = "Premio")
@Getter
@Setter
@NamedEntityGraph
@ToString

public class Premio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private long Id;

    @NotNull(message = "Campo TorneoId no puede estar vacio")
    @Column (name = "Torneo_id")
    private String TorneoId;

    @NotNull (message = "Campo Posicion no puede estar vacio")
    @Column (name = "Posicion")
    private String Posicion;

    @NotNull(message = "Campo Descripcion no puede estar vacio")
    @Column (name = "Descripcion")
    private String Descripcion;


    @NotNull (message = "Campo Valor no puede estar vacio")
    @Column (name = "Valor")
    private String Valor;

    @NotNull (message = "Campo Estado no puede estar vacio")
    @Column (name = "Estado")
    private String Estado;


    @Embedded
    Audit audit = new Audit();
}
