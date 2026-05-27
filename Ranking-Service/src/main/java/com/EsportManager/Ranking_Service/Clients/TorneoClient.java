package com.EsportManager.Ranking_Service.Clients;


import com.EsportManager.Ranking_Service.Models.Dtos.TorneoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "tournament-service", path = "/api/v1/torneos")
public interface TorneoClient {
    // Valida que el torneo exista antes de crear una tabla de posiciones
    @GetMapping("/{id}")
    TorneoDTO obtenerTorneoPorId(@PathVariable("id") Long id);
}
