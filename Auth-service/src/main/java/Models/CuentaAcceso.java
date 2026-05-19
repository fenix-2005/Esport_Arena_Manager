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
    @Column(name="Id")
    private long Id;

    @NotNull(message = "Campo Email no puede estar vacio")
    @Column (name = "Email")
    private String Email;

    @NotNull (message = "Campo Password no puede estar vacio")
    @Column (name = "Password")
    private  String Password;

    @NotNull (message = "Campo Rol no puede estar vacio")
    @Column (name = "Rol")
    private String Rol;

    @NotNull (message = "Campo Estado no puede estar vacio")
    @Column (name = "Estado")
    private String Estado;

    @NotNull (message = "Campo fechaCreacion no puede estar vacio")
    @Column (name = "fechaCreacion")
    private Date fechaCreacion;


    @Embedded
    Audit audit = new Audit();
}
