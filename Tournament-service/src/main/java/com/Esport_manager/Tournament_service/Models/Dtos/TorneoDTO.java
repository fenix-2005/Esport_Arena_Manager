package com.Esport_manager.Tournament_service.Models.Dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class TorneoDTO {
    private Long id;

    @NotBlank(message = "El nombre del torneo no puede estar vacío")
    private String nombre;

    @NotNull(message = "El ID del juego no puede ser nulo")
    private Long juegoId;

    @NotNull(message = "La fecha de inicio no puede ser nula")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin no puede ser nula")
    private LocalDate fechaFin;

    @NotNull(message = "El cupo máximo no puede ser nulo")
    @Min(value = 2, message = "El cupo máximo debe ser al menos 2")
    private Integer cupoMaximo;

    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;

    @NotBlank(message = "La modalidad no puede estar vacía")
    private String modalidad;
}
