package com.EsportManager.Prize_Service.Models.Dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PremioAsignadoDTO {
    private Long id;

    @NotNull(message = "El ID del premio no puede ser nulo")
    private Long premioId;

    @NotNull(message = "El ID del participante no puede ser nulo")
    private Long participanteId;

    @NotNull(message = "La fecha de asignación no puede ser nula")
    private LocalDate fechaAsignacion;
}
