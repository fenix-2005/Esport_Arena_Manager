package com.EsportManager.Ranking_Service.Models.Dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RankingDTO {
    private Long id;

    @NotNull(message = "El ID del torneo no puede ser nulo")
    private Long torneoId;

    @NotNull(message = "El ID del participante no puede ser nulo")
    private Long participanteId;

    @NotNull(message = "Los puntos no pueden ser nulos")
    @Min(value = 0, message = "Los puntos no pueden ser negativos")
    private Integer puntos;

    @NotNull(message = "Las victorias no pueden ser nulas")
    @Min(value = 0, message = "Las victorias no pueden ser negativas")
    private Integer victorias;

    @NotNull(message = "Las derrotas no pueden ser nulas")
    @Min(value = 0, message = "Las derrotas no pueden ser negativas")
    private Integer derrotas;

    @NotNull(message = "La diferencia no puede ser nula")
    private Integer diferencia;

    @NotNull(message = "La posición no puede ser nula")
    @Min(value = 1, message = "La posición debe ser al menos 1")
    private Integer posicion;
}
