package com.EsportManager.Sanction_Service.Controllers;

import com.EsportManager.Sanction_Service.Models.Sancion;
import com.EsportManager.Sanction_Service.Models.Dtos.SancionDTO;
import com.EsportManager.Sanction_Service.Services.SancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/v1/sanciones")
public class SancionController {

    @Autowired
    private SancionService sancionService;

    // 1. Crear sanción
    @PostMapping
    public ResponseEntity<Sancion> emitirSancion(@Valid @RequestBody SancionDTO dto) {
        Sancion nuevaSancion = sancionService.save(dto); // Requiere adaptar a DTO
        return new ResponseEntity<>(nuevaSancion, HttpStatus.CREATED);
    }

    // 2. Listar sanciones
    @GetMapping
    public ResponseEntity<List<Sancion>> listarSanciones(
            @RequestParam(required = false) Long usuarioId,
            @RequestParam(required = false) Long equipoId,
            @RequestParam(required = false) String estado) {

        List<Sancion> sanciones;


        if (usuarioId != null && estado != null) {
            sanciones = sancionService.findByUsuarioIdAndEstado(usuarioId, estado);
        } else if (equipoId != null && estado != null) {
            sanciones = sancionService.findByEquipoIdAndEstado(equipoId, estado);
        } else if (usuarioId != null) {
            sanciones = sancionService.findByUsuarioId(usuarioId);
        } else if (equipoId != null) {
            sanciones = sancionService.findByEquipoId(equipoId);
        } else if (estado != null) {
            sanciones = sancionService.findByEstado(estado);
        } else {
            sanciones = sancionService.findAll();
        }

        return new ResponseEntity<>(sanciones, HttpStatus.OK);
    }

    // 3. Buscar sanción por ID
    @GetMapping("/{id}")
    public ResponseEntity<Sancion> buscarPorId(@PathVariable Long id) {
        Sancion sancion = sancionService.findById(id);
        return new ResponseEntity<>(sancion, HttpStatus.OK);
    }

    // 4. Endpoint interno consumido por el Registration-service
    @GetMapping("/verificar")
    public ResponseEntity<Boolean> verificarSancionActiva(
            @RequestParam(required = false) Long usuarioId,
            @RequestParam(required = false) Long equipoId) {

        if (usuarioId == null && equipoId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe proporcionar usuarioId o equipoId");
        }

        boolean tieneSancion = sancionService.tieneSancionActiva(usuarioId, equipoId);
        return new ResponseEntity<>(tieneSancion, HttpStatus.OK);
    }

    // 5. Actualizar duración, motivo o estado
    @PutMapping("/{id}")
    public ResponseEntity<Sancion> actualizarSancion(
            @PathVariable Long id,
            @Valid @RequestBody SancionDTO dto) {
        Sancion sancionActualizada = sancionService.updateById(dto, id);
        return new ResponseEntity<>(sancionActualizada, HttpStatus.OK);
    }

    // 6. Cerrar sanción cumplida
    @PutMapping("/{id}/cerrar")
    public ResponseEntity<Sancion> cerrarSancion(@PathVariable Long id) {
        Sancion sancionCerrada = sancionService.cerrarSancion(id);
        return new ResponseEntity<>(sancionCerrada, HttpStatus.OK);
    }

    // 7. Eliminar sanción
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSancion(@PathVariable Long id) {
        sancionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
