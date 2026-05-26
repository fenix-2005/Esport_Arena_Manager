package com.EsportManager.Ranking_Service.Models.Dtos;

import lombok.Data;

@Data
public class RankingDTO {
    private Long id;
    private Long torneoId;
    private Long participanteId;
    private String puntos;
    private String victorias;
    private String derrotas;
    private String diferencia;
    private int posicion;
}
