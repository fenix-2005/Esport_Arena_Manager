package Models.Dtos;

import lombok.Data;
import java.sql.Date;

@Data
public class NotificacionDTO {
    private Long id;
    private Long userId;
    private Long equipoId;
    private String tipo;
    private String mensaje;
    private Boolean leida;
    private Date fecha;
}
