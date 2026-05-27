package com.EsportManager.Result_Service.Models.Dtos;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PartidaDTO {
    private Long id;
    private Long torneoId;
    private Long participanteAId;
    private Long participanteBId;
    private int ronda;
    private LocalDateTime fechaHora;
    private String estado;
}
