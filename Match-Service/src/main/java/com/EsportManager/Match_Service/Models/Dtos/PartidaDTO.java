package com.EsportManager.Match_Service.Models.Dtos;

import lombok.Data;
import java.util.Date;

@Data
public class PartidaDTO {
    private Long id;
    private Long torneoId;
    private Long participanteAId;
    private Long participanteBId;
    private int ronda;
    private Date fechaHora;
    private String estado;
}
