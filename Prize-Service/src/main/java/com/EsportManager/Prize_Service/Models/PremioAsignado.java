package com.EsportManager.Prize_Service.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

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
    private Long id;

    @NotNull(message = "Campo PremioId no puede estar vacio")
    @Column (name = "Premio_id")
    private Long premioId;

    @NotNull (message = "Campo ParticipanteId no puede estar vacio")
    @Column (name = "Participante_id")
    private Long participanteId;

    @NotNull(message = "Campo FechaAsignacion no puede estar vacio")
    @Column (name = "Fecha_asignacion")
    private LocalDate fechaAsignacion;



    @Embedded
    Audit audit = new Audit();
}

