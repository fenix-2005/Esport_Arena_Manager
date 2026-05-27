package com.EsportManager.Registration_Service.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "sanction-service", path = "/api/v1/sanciones")
public interface SancionClient {
    // Valida si el jugador o equipo tiene una sanción activa que lo bloquee
    @GetMapping("/verificar")
    Boolean tieneSancionActiva(@RequestParam(required = false) Long jugadorId, @RequestParam(required = false) Long equipoId);
}
