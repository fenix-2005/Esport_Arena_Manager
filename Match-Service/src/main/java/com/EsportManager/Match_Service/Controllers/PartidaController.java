package com.EsportManager.Match_Service.Controllers;

import com.EsportManager.Match_Service.Models.Partida;
import com.EsportManager.Match_Service.Models.Dtos.PartidaDTO;
import com.EsportManager.Match_Service.Services.PartidaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/partidas")
@Tag(name = "Programación de Partidas", description = "Endpoints para la gestión de fixtures, llaves y estados de enfrentamientos")
public class PartidaController {

    @Autowired
    private PartidaService partidaService;

    @Operation(summary = "Programar nueva partida", description = "Genera un emparejamiento oficial entre dos participantes asignando ronda y horario.")
    @PostMapping
    public ResponseEntity<Partida> crearPartida(@Valid @RequestBody PartidaDTO dto) {
        Partida nuevaPartida = partidaService.save(dto);
        return new ResponseEntity<>(nuevaPartida, HttpStatus.CREATED);
    }

    @Operation(summary = "Buscar y listar partidas", description = "Recupera los emparejamientos competitivos filtrando opcionalmente por torneo, estado o número de ronda.")
    @ApiResponse(responseCode = "200", description = "Listado de enfrentamientos generado")
    @GetMapping
    public ResponseEntity<List<Partida>> listarPartidas(
            @RequestParam(required = false) Long torneoId,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) Integer ronda) {
        List<Partida> partidas;
        if (torneoId != null) {
            partidas = partidaService.findByTorneoId(torneoId);
        } else if (estado != null) {
            partidas = partidaService.findByEstado(estado);
        } else if (ronda != null) {
            partidas = partidaService.findByRonda(ronda);
        } else {
            partidas = partidaService.findAll();
        }
        return new ResponseEntity<>(partidas, HttpStatus.OK);
    }

    @Operation(summary = "Obtener partida por ID", description = "Recupera la información completa de una partida específica.")
    @GetMapping("/{id}")
    public ResponseEntity<Partida> buscarPorId(@PathVariable Long id) {
        Partida partida = partidaService.findById(id);
        return new ResponseEntity<>(partida, HttpStatus.OK);
    }

    @Operation(summary = "Modificar programación de partida", description = "Permite reprogramar horarios, cambiar participantes o modificar el estado de la partida.")
    @PutMapping("/{id}")
    public ResponseEntity<Partida> actualizarPartida(@PathVariable Long id, @Valid @RequestBody PartidaDTO dto) {
        Partida partidaActualizada = partidaService.updateById(dto, id);
        return new ResponseEntity<>(partidaActualizada, HttpStatus.OK);
    }

    @Operation(summary = "Cancelar partida programada", description = "Elimina un enfrentamiento del calendario competitivo oficial.")
    @ApiResponse(responseCode = "204", description = "Partida cancelada y eliminada del fixture")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarPartida(@PathVariable Long id) {
        partidaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}