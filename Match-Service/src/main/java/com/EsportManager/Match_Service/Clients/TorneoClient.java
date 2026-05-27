package com.EsportManager.Match_Service.Clients;

import com.EsportManager.Match_Service.Models.Dtos.TorneoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "tournament-service", path = "/api/v1/torneos")
public interface TorneoClient {

    // Sirve para validar que el torneo existe y no está cancelado antes de programar una partida
    @GetMapping("/{id}")
    TorneoDTO obtenerTorneoPorId(@PathVariable("id") Long id);
}
