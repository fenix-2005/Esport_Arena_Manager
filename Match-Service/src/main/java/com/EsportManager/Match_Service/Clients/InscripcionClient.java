package com.EsportManager.Match_Service.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "registration-service", path = "/api/v1/inscripciones")
public interface InscripcionClient {
    
    // Sirve para validar la regla: "No crear partida con participante no inscrito"
    @GetMapping("/verificar")
    Boolean verificarInscripcion(@RequestParam("torneoId") Long torneoId, @RequestParam("participanteId") Long participanteId);
}