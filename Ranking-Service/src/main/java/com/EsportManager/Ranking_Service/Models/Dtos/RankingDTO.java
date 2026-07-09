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

    @NotBlank(message = "Los puntos no pueden estar vacíos")
    private String puntos;

    @NotBlank(message = "Las victorias no pueden estar vacías")
    private String victorias;

    @NotBlank(message = "Las derrotas no pueden estar vacías")
    private String derrotas;

    @NotBlank(message = "La diferencia no puede estar vacía")
    private String diferencia;

    @Min(value = 1, message = "La posición debe ser al menos 1")
    private int posicion;
}
