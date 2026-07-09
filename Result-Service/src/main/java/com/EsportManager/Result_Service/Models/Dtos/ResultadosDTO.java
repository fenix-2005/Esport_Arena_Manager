package com.EsportManager.Result_Service.Models.Dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ResultadosDTO {
    private Long id;

    @NotNull(message = "El ID de la partida no puede ser nulo")
    private Long partidaId;

    @NotNull(message = "El ID del torneo no puede ser nulo")
    private Long torneoId;

    @NotNull(message = "El ID del ganador no puede ser nulo")
    private Long ganadorId;

    @Min(value = 0, message = "El puntaje A no puede ser negativo")
    private int puntajeA;

    @Min(value = 0, message = "El puntaje B no puede ser negativo")
    private int puntajeB;

    @Min(value = 1, message = "El cupo máximo debe ser al menos 1")
    private int cupoMaximo;

    @NotBlank(message = "El estado de validación no puede estar vacío")
    private String estadoValidacion;

    @NotNull(message = "La fecha de registro no puede ser nula")
    private LocalDate fechaRegistro;
}
