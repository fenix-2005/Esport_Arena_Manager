package com.EsportManager.Match_Service.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.logging.log4j.message.Message;

import java.time.LocalDateTime;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Entity
@Table (name = "Partida")
@Getter
@Setter
@NamedEntityGraph
@ToString

public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;

    @NotNull (message = "Campo torneo_id no puede estar vacio")
    @Column (name = "torneo_id")
    private Long torneoId;

    @NotNull(message = "Campo Participante_A_Id no puede estar vacio")
    @Column (name = "Participante_A_id")
    private Long participanteAId;

    @NotNull(message = "Campo Participante_B_Id no puede estar vacio")
    @Column (name = "Participante_B_id")
    private Long participanteBId;

    @NotNull(message = "Campo Ronda no puede estar vacio")
    @Column (name = "Ronda")
    private Integer ronda;

    @NotNull(message = "Campo Fecha_Hora no puede estar vacio")
    @Column (name = "Fecha_hora")
    private LocalDateTime fechaHora;

    @NotNull(message = "Campo Estado no puede estar vacio")
    @Column (name = "Estado")
    private String estado;

    @Embedded
    Audit audit = new Audit();
}
