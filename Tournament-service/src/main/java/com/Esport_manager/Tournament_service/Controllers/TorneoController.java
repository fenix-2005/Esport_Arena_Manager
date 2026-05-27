package com.Esport_manager.Tournament_service.Controllers;

import com.Esport_manager.Tournament_service.Models.Torneo;
import com.Esport_manager.Tournament_service.Models.Dtos.TorneoDTO;
import com.Esport_manager.Tournament_service.Services.TorneoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/torneos")
public class TorneoController {

    @Autowired
    private TorneoService torneoService;

    // 1. Crear torneo
    @PostMapping
    public ResponseEntity<Torneo> crearTorneo(@Valid @RequestBody TorneoDTO dto) {
        Torneo nuevoTorneo = torneoService.save(dto); // Requiere adaptar a DTO
        return new ResponseEntity<>(nuevoTorneo, HttpStatus.CREATED);
    }

    // 2. Listar torneos
    @GetMapping
    public ResponseEntity<List<Torneo>> listarTorneos(
            @RequestParam(required = false) Long juegoId,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio) {

        List<Torneo> torneos;


        if (juegoId != null) {
            torneos = torneoService.findByJuegoId(juegoId);
        } else if (estado != null) {
            torneos = torneoService.findByEstado(estado);
        } else if (fechaInicio != null) {
            torneos = torneoService.findByFechaInicio(fechaInicio);
        } else {
            torneos = torneoService.findAll();
        }

        return new ResponseEntity<>(torneos, HttpStatus.OK);
    }

    // 3. Buscar torneo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Torneo> buscarPorId(@PathVariable Long id) {
        Torneo torneo = torneoService.findById(id);
        return new ResponseEntity<>(torneo, HttpStatus.OK);
    }

    // 4. Actualizar torneo (fechas, cupos, estado)
    @PutMapping("/{id}")
    public ResponseEntity<Torneo> actualizarTorneo(
            @PathVariable Long id,
            @Valid @RequestBody TorneoDTO dto) {

        Torneo torneoActualizado = torneoService.updateById(dto, id);
        return new ResponseEntity<>(torneoActualizado, HttpStatus.OK);
    }

    // 5. Cancelar o cerrar torneo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarTorneo(@PathVariable Long id) {
        torneoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
