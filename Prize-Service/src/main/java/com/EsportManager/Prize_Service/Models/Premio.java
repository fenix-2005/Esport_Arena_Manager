package com.EsportManager.Prize_Service.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// No date imports needed if no Date fields are used.

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
    private Long id;

    @NotNull(message = "Campo TorneoId no puede estar vacio")
    @Column (name = "Torneo_id")
    private Long torneoId;

    @NotNull (message = "Campo Posicion no puede estar vacio")
    @Column (name = "Posicion")
    private int posicion;

    @NotNull(message = "Campo Descripcion no puede estar vacio")
    @Column (name = "Descripcion")
    private String descripcion;


    @NotNull (message = "Campo Valor no puede estar vacio")
    @Column (name = "Valor")
    private String valor;

    @NotNull (message = "Campo Estado no puede estar vacio")
    @Column (name = "Estado")
    private String estado;


    @Embedded
    Audit audit = new Audit();
}
