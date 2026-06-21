package com.EsportManager.Sanction_Service.Controllers;

import com.EsportManager.Sanction_Service.Models.Sancion;
import com.EsportManager.Sanction_Service.Models.Dtos.SancionDTO;
import com.EsportManager.Sanction_Service.Services.SancionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sanciones")
@Tag(name = "Tribunal Disciplinario", description = "Gestión de amonestaciones, suspensiones activas y bloqueos de competidores")
public class SancionController {

    @Autowired
    private SancionService sancionService;

    @Operation(summary = "Emitir sanción disciplinaria", description = "Registra un castigo fundamentado inhabilitando temporalmente al infractor.")
    @PostMapping
    public ResponseEntity<Sancion> emitirSancion(@Valid @RequestBody SancionDTO dto) {
        Sancion nuevaSancion = sancionService.save(dto);
        return new ResponseEntity<>(nuevaSancion, HttpStatus.CREATED);
    }

    @Operation(summary = "Consultar historial de bloqueos", description = "Devuelve los castigos registrados cruzando filtros dinámicos por ID de usuarios, de escuadras o estados activos.")
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

    @Operation(summary = "Buscar sanción por ID", description = "Obtiene los motivos y rango de fechas de un castigo.")
    @GetMapping("/{id}")
    public ResponseEntity<Sancion> buscarPorId(@PathVariable Long id) {
        Sancion sancion = sancionService.findById(id);
        return new ResponseEntity<>(sancion, HttpStatus.OK);
    }

    @Operation(summary = "Verificación de bloqueo activa (Servicio Interno)", description = "Endpoint de seguridad consumido por el microservicio de Inscripciones para verificar suspensiones en tiempo real.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consulta completada (Retorna booleano)"),
            @ApiResponse(responseCode = "400", description = "Petición incorrecta por falta de parámetros")
    })
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

    @Operation(summary = "Modificar dictamen de sanción", description = "Permite extender las fechas de suspensión o reajustar la gravedad de la falta.")
    @PutMapping("/{id}")
    public ResponseEntity<Sancion> actualizarSancion(@PathVariable Long id, @Valid @RequestBody SancionDTO dto) {
        Sancion sancionActualizada = sancionService.updateById(dto, id);
        return new ResponseEntity<>(sancionActualizada, HttpStatus.OK);
    }

    @Operation(summary = "Cerrar amonestación de forma anticipada", description = "Levanta de forma anticipada o manual las suspensiones aplicadas, restaurando la elegibilidad.")
    @PutMapping("/{id}/cerrar")
    public ResponseEntity<Sancion> cerrarSancion(@PathVariable Long id) {
        Sancion sancionCerrada = sancionService.cerrarSancion(id);
        return new ResponseEntity<>(sancionCerrada, HttpStatus.OK);
    }

    @Operation(summary = "Borrar registro disciplinario", description = "Remueve de forma permanente una sanción del historial de la organización.")
    @ApiResponse(responseCode = "204", description = "Castigo eliminado correctamente de las planillas")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSancion(@PathVariable Long id) {
        sancionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}