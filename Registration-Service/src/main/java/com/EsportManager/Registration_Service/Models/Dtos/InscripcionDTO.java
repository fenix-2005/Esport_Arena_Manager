package com.EsportManager.Registration_Service.Models.Dtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class InscripcionDTO {
    private Long id;
    private Long torneoId;
    private Long equipoId;
    private Long jugadorId;
    private String tipoParticipante;
    private String estado;
    private LocalDate fechaInscripcion;
}
