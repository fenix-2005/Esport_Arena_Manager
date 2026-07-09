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

    @NotNull(message = "El puntaje A no puede ser nulo")
    @Min(value = 0, message = "El puntaje A no puede ser negativo")
    private Integer puntajeA;

    @NotNull(message = "El puntaje B no puede ser nulo")
    @Min(value = 0, message = "El puntaje B no puede ser negativo")
    private Integer puntajeB;

    @NotNull(message = "El cupo máximo no puede ser nulo")
    @Min(value = 1, message = "El cupo máximo debe ser al menos 1")
    private Integer cupoMaximo;

    @NotBlank(message = "El estado de validación no puede estar vacío")
    private String estadoValidacion;

    @NotNull(message = "La fecha de registro no puede ser nula")
    private LocalDate fechaRegistro;
}
