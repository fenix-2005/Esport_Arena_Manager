package com.EsportManager.Prize_Service.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Entity
@Table(name = "PremioAsignado")
@Getter
@Setter
@NamedEntityGraph
@ToString

public class PremioAsignado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private long Id;

    @NotNull(message = "Campo PremioId no puede estar vacio")
    @Column (name = "Premio_id")
    private long PremioId;

    @NotNull (message = "Campo ParticipanteId no puede estar vacio")
    @Column (name = "Participante_id")
    private long ParticipanteId;

    @NotNull(message = "Campo FechaAsignacion no puede estar vacio")
    @Column (name = "Fecha_asignacion")
    private Date FechaAsignacion;



    @Embedded
    Audit audit = new Audit();
}

