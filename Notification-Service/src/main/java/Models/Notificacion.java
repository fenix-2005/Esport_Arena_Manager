package Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Entity
@Table(name = "Notificacion")
@Getter
@Setter
@NamedEntityGraph
@ToString

public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private long Id;

    @NotNull(message = "Campo UserID no puede estar vacio")
    @Column (name = "UserId")
    private Long UserId;

    @NotNull (message = "Campo EquipoId no puede estar vacio")
    @Column (name = "EquipoId")
    private  Long EquipoId;

    @NotNull (message = "Campo Tipo no puede estar vacio")
    @Column (name = "Tipo")
    private String Tipo;


    @Column (name = "Mensaje")
    private String Mensaje;


    @Column (name = "Leida")
    private Boolean Leida;

    @NotNull (message = "Campo Fecha no puede estar vacio")
    @Column (name = "Fecha")
    private Date Fecha;


    @Embedded
    Audit audit = new Audit();
}
