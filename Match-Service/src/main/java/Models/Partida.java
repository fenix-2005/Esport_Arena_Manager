package Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.logging.log4j.message.Message;

import java.util.Date;

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
    private long Id;

    @NotNull (message = "Campo torneo_id no puede estar vacio")
    @Column (name = "torneo_Id")
    private long torneoId;

    @NotNull(message = "Campo Participante_A_Id no puede estar vacio")
    @Column (name = "Participante_A_Id")
    private long participanteAId;

    @NotNull(message = "Campo Participante_B_Id no puede estar vacio")
    @Column (name = "Participante_B_Id")
    private long participanteBId;

    @NotNull(message = "Campo Ronda no puede estar vacio")
    @Column (name = "Ronda")
    private int ronda;

    @NotNull(message = "Campo Fecha_Hora no puede estar vacio")
    @Column (name = "Fecha_Hora")
    private Date fechaHora;

    @NotNull(message = "Campo Estado no puede estar vacio")
    @Column (name = "Estado")
    private boolean estado;

    @Embedded
    Audit audit = new Audit();
}
