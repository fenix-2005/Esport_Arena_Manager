package com.EsportManager.Registration_Service.Clients;

import com.EsportManager.Registration_Service.Models.Dtos.EquipoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "team-service", path = "/api/v1/equipos")
public interface EquipoClient {
    // Si la inscripción es por equipo, valida que el equipo exista y cumpla los requisitos
    @GetMapping("/{id}")
    EquipoDTO obtenerEquipoPorId(@PathVariable("id") Long id);
}
