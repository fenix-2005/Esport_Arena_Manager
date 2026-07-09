package com.EsportManager.Prize_Service.Models.Dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PremioDTO {
    private Long id;

    @NotNull(message = "El ID del torneo no puede ser nulo")
    private Long torneoId;

    @NotNull(message = "La posición no puede ser nula")
    @Min(value = 1, message = "La posición debe ser al menos 1")
    private Integer posicion;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;

    @NotBlank(message = "El valor no puede estar vacío")
    private String valor;

    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;
}
