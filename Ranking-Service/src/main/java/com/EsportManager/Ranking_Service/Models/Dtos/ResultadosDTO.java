package com.EsportManager.Ranking_Service.Models.Dtos;

import lombok.Data;
import java.util.Date;

@Data
public class ResultadosDTO {
    private Long id;
    private Long partidaId;
    private Long torneoId;
    private Long ganadorId;
    private int puntajeA;
    private int puntajeB;
    private int cupoMaximo;
    private String estadoValidacion;
    private Date fechaRegistro;
}
