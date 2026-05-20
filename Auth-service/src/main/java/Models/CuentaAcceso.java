package Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Entity
@Table(name = "Cuenta_Acceso")
@Getter
@Setter
@NamedEntityGraph
@ToString

public class CuentaAcceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotNull(message = "Campo Email no puede estar vacio")
    @Column (name = "Email")
    private String email;

    @NotNull (message = "Campo Password no puede estar vacio")
    @Column (name = "Password")
    private String password;

    @NotNull (message = "Campo Rol no puede estar vacio")
    @Column (name = "Rol")
    private String rol;

    @NotNull (message = "Campo Estado no puede estar vacio")
    @Column (name = "Estado")
    private String estado;

    @NotNull (message = "Campo fechaCreacion no puede estar vacio")
    @Column (name = "fecha_creacion")
    private Date fechaCreacion;


    @Embedded
    Audit audit = new Audit();
}
