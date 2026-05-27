package com.EsportManager.Result_Service.Models.Dtos;

import lombok.Data;
import java.time.LocalDate;

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
    private LocalDate fechaRegistro;
}
