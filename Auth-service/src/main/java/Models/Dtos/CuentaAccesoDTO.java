package Models.Dtos;

import lombok.Data;
import java.sql.Date;

@Data
public class CuentaAccesoDTO {
    private Long id;
    private String email;
    private String password;
    private String rol;
    private String estado;
    private Date fechaCreacion;
}
