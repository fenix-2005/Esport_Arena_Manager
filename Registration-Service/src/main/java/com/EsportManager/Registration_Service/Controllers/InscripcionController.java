package com.EsportManager.Registration_Service.Controllers;

import com.EsportManager.Registration_Service.Models.Inscripcion;
import com.EsportManager.Registration_Service.Models.Dtos.InscripcionDTO;
import com.EsportManager.Registration_Service.Services.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    // 1. Crear inscripción (Aplica todas las validaciones Feign en el Service)
    @PostMapping
    public ResponseEntity<Inscripcion> crearInscripcion(@Valid @RequestBody InscripcionDTO dto) {
        Inscripcion nuevaInscripcion = inscripcionService.save(dto); // Requiere adaptar a DTO
        return new ResponseEntity<>(nuevaInscripcion, HttpStatus.CREATED);
    }

    // 2. Listar inscripciones (Con filtros por torneo, equipo o jugador)
    @GetMapping
    public ResponseEntity<List<Inscripcion>> listarInscripciones(
            @RequestParam(required = false) Long torneoId,
            @RequestParam(required = false) Long equipoId,
            @RequestParam(required = false) Long jugadorId) {

        List<Inscripcion> inscripciones;

        if (torneoId != null) {
            inscripciones = inscripcionService.findByTorneoId(torneoId);
        } else if (equipoId != null) {
            inscripciones = inscripcionService.findByEquipoId(equipoId);
        } else if (jugadorId != null) {
            inscripciones = inscripcionService.findByJugadorId(jugadorId);
        } else {
            inscripciones = inscripcionService.findAll();
        }

        return new ResponseEntity<>(inscripciones, HttpStatus.OK);
    }

    // 3. Buscar inscripción por ID
    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> buscarPorId(@PathVariable Long id) {
        Inscripcion inscripcion = inscripcionService.findById(id);
        if (inscripcion == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(inscripcion, HttpStatus.OK);
    }

    // 4. Actualizar estado de inscripción (Ej. De 'PENDIENTE' a 'ACEPTADA' o 'RECHAZADA')
    @PutMapping("/{id}")
    public ResponseEntity<Inscripcion> actualizarEstadoInscripcion(
            @PathVariable Long id,
            @Valid @RequestBody InscripcionDTO dto) {

        Inscripcion inscripcionActualizada = inscripcionService.updateById(dto, id);
        return new ResponseEntity<>(inscripcionActualizada, HttpStatus.OK);
    }

    // 5. Cancelar inscripción
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarInscripcion(@PathVariable Long id) {
        inscripcionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
