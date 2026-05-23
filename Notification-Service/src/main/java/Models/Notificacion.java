package Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Entity
@Table(name = "Notificacion")
@Getter
@Setter
@NamedEntityGraph
@ToString
@NoArgsConstructor

public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;

    @NotNull(message = "Campo UserID no puede estar vacio")
    @Column (name = "User_id")
    private Long userId;

    @NotNull (message = "Campo EquipoId no puede estar vacio")
    @Column (name = "Equipo_id")
    private  Long equipoId;

    @NotNull (message = "Campo Tipo no puede estar vacio")
    @Column (name = "Tipo")
    private String tipo;


    @Column (name = "Mensaje")
    private String mensaje;


    @Column (name = "Leida")
    private Boolean leida;

    @NotNull (message = "Campo Fecha no puede estar vacio")
    @Column (name = "Fecha")
    private Date fecha;


    @Embedded
    Audit audit = new Audit();
}
