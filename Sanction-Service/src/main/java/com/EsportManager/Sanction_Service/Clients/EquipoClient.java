package com.EsportManager.Sanction_Service.Clients;

import com.EsportManager.Sanction_Service.Models.Dtos.EquipoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "team-service", path = "/api/v1/equipos")
public interface EquipoClient {
    // Verifica que el equipo existe antes de registrar su sanción
    @GetMapping("/{id}")
    EquipoDTO obtenerEquipoPorId(@PathVariable("id") Long id);
}
