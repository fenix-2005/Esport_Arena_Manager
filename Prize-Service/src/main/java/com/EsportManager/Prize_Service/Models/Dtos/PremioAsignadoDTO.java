package com.EsportManager.Prize_Service.Models.Dtos;

import lombok.Data;
import java.sql.Date;

@Data
public class PremioAsignadoDTO {
    private Long id;
    private Long premioId;
    private Long participanteId;
    private Date fechaAsignacion;
}
