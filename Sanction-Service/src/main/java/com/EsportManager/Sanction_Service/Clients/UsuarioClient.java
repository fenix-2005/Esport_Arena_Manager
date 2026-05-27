package com.EsportManager.Sanction_Service.Clients;

import com.EsportManager.Sanction_Service.Models.Dtos.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", path = "/api/v1/usuarios")
public interface UsuarioClient {
    // Verifica que el jugador existe antes de registrar su sanción
    @GetMapping("/{id}")
    UsuarioDTO obtenerUsuarioPorId(@PathVariable("id") Long id);
}
