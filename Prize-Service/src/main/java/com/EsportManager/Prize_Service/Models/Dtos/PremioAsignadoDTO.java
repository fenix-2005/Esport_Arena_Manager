package com.EsportManager.Prize_Service.Models.Dtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PremioAsignadoDTO {
    private Long id;
    private Long premioId;
    private Long participanteId;
    private LocalDate fechaAsignacion;
}
