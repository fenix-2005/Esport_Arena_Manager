package com.EsportManager.Match_Service.Controllers;

import com.EsportManager.Match_Service.Models.Partida;
import com.EsportManager.Match_Service.Models.Dtos.PartidaDTO;
import com.EsportManager.Match_Service.Services.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/partidas")
public class PartidaController {

    @Autowired
    private PartidaService partidaService;

    // 1. Crear partida
    @PostMapping
    public ResponseEntity<Partida> crearPartida(@Valid @RequestBody PartidaDTO dto) {
        Partida nuevaPartida = partidaService.save(dto);
        return new ResponseEntity<>(nuevaPartida, HttpStatus.CREATED);
    }

    // 2. Listar partidas
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

    // 3. Buscar partida por ID
    @GetMapping("/{id}")
    public ResponseEntity<Partida> buscarPorId(@PathVariable Long id) {
        Partida partida = partidaService.findById(id);
        return new ResponseEntity<>(partida, HttpStatus.OK);
    }

    // 4. Actualizar horario, participantes o estado
    @PutMapping("/{id}")
    public ResponseEntity<Partida> actualizarPartida(
            @PathVariable Long id,
            @Valid @RequestBody PartidaDTO dto) {
        Partida partidaActualizada = partidaService.updateById(dto, id);
        return new ResponseEntity<>(partidaActualizada, HttpStatus.OK);
    }

    // 5. Eliminar partida
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarPartida(@PathVariable Long id) {
        partidaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
