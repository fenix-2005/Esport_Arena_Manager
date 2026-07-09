package com.EsportManager.Sanction_Service.Models.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class SancionDTO {
    private Long id;

    private Long usuarioId;

    private Long equipoId;

    @NotBlank(message = "El motivo no puede estar vacío")
    private String motivo;

    @NotNull(message = "La fecha de inicio no puede ser nula")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin no puede ser nula")
    private LocalDate fechaFin;

    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;

    @NotBlank(message = "La severidad no puede estar vacía")
    private String severida;
}
