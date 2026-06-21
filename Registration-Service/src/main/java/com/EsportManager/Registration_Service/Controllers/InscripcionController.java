package com.EsportManager.Registration_Service.Controllers;

import com.EsportManager.Registration_Service.Models.Inscripcion;
import com.EsportManager.Registration_Service.Models.Dtos.InscripcionDTO;
import com.EsportManager.Registration_Service.Services.InscripcionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/inscripciones")
@Tag(name = "Control de Inscripciones", description = "Procesamiento de fichajes, validación distribuidas de cupos y postulaciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @Operation(summary = "Solicitar inscripción a torneo", description = "Crea una postulación para un competidor o club. Valida internamente mediante Feign que no existan bloqueos activos.")
    @PostMapping
    public ResponseEntity<Inscripcion> crearInscripcion(@Valid @RequestBody InscripcionDTO dto) {
        Inscripcion nuevaInscripcion = inscripcionService.save(dto);
        return new ResponseEntity<>(nuevaInscripcion, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar solicitudes de inscripción", description = "Recupera todas las inscripciones permitiendo filtrar ordenadamente por torneo, equipo o jugador.")
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

    @Operation(summary = "Buscar postulación por ID", description = "Obtiene los metadatos y fechas de una solicitud de inscripción por su clave primaria.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Inscripción localizada"),
            @ApiResponse(responseCode = "404", description = "El identificador no corresponde a ninguna inscripción")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> buscarPorId(@PathVariable Long id) {
        Inscripcion inscripcion = inscripcionService.findById(id);
        return (inscripcion == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(inscripcion, HttpStatus.OK);
    }

    @Operation(summary = "Modificar estado de postulación", description = "Endpoint administrativo para cambiar las solicitudes de PENDIENTE a ACEPTADA o RECHAZADA.")
    @PutMapping("/{id}")
    public ResponseEntity<Inscripcion> actualizarEstadoInscripcion(@PathVariable Long id, @Valid @RequestBody InscripcionDTO dto) {
        Inscripcion inscripcionActualizada = inscripcionService.updateById(dto, id);
        return new ResponseEntity<>(inscripcionActualizada, HttpStatus.OK);
    }

    @Operation(summary = "Dar de baja inscripción", description = "Cancela la participación de un competidor liberando de forma inmediata su plaza del torneo.")
    @ApiResponse(responseCode = "204", description = "Inscripción revocada de forma exitosa")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarInscripcion(@PathVariable Long id) {
        inscripcionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}