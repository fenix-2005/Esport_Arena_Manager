package com.EsportManager.Registration_Service.Models.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class InscripcionDTO {
    private Long id;

    @NotNull(message = "El ID del torneo no puede ser nulo")
    private Long torneoId;

    private Long equipoId;

    private Long jugadorId;

    @NotBlank(message = "El tipo de participante no puede estar vacío")
    private String tipoParticipante;

    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;

    @NotNull(message = "La fecha de inscripción no puede ser nula")
    private LocalDate fechaInscripcion;
}
