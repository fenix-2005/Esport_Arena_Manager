package Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "Miembro_equipo")
@Getter
@Setter
@NamedEntityGraph
@ToString

public class MiembroEquipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private long Id;

    @NotNull(message = "Campo EquipoId no puede estar vacio")
    @Column (name = "Equipo_id")
    private long EquipoId;

    @NotNull(message = "Campo UsuarioId no puede estar vacio")
    @Column (name = "Usuario_id")
    private long UsuarioId;

    @NotNull(message = "Campo RolEquipo no puede estar vacio")
    @Column (name = "Rol_equipo")
    private long RolEquipo;


    @Embedded
    Audit audit = new Audit();
}

