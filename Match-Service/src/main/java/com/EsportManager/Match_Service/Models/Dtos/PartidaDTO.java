package com.EsportManager.Match_Service.Models.Dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PartidaDTO {
    private Long id;

    @NotNull(message = "El ID del torneo no puede ser nulo")
    private Long torneoId;

    @NotNull(message = "El ID del participante A no puede ser nulo")
    private Long participanteAId;

    @NotNull(message = "El ID del participante B no puede ser nulo")
    private Long participanteBId;

    @NotNull(message = "La ronda no puede ser nula")
    @Min(value = 1, message = "La ronda debe ser al menos 1")
    private Integer ronda;

    @NotNull(message = "La fecha y hora no pueden ser nulas")
    private LocalDateTime fechaHora;

    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;
}
