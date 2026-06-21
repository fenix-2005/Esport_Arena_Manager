package com.EsportManager.Ranking_Service.Controllers;

import com.EsportManager.Ranking_Service.Models.Ranking;
import com.EsportManager.Ranking_Service.Models.Dtos.RankingDTO;
import com.EsportManager.Ranking_Service.Services.RankingService;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/rankings")
@Tag(name = "Tablas de Posiciones", description = "Endpoints para el cálculo estadístico, puntajes de liga y records globales")
public class RankingController {

    @Autowired
    private RankingService rankingService;

    @Operation(summary = "Inicializar récord de participante", description = "Crea una fila de estadísticas en cero para un participante al entrar a una nueva liga o torneo.")
    @PostMapping
    public ResponseEntity<Ranking> crearRegistroRanking(@Valid @RequestBody RankingDTO dto) {
        Ranking nuevoRanking = rankingService.save(dto);
        return new ResponseEntity<>(nuevoRanking, HttpStatus.CREATED);
    }

    @Operation(summary = "Ver tabla de clasificación", description = "Recupera las estadísticas de puntajes globales o filtradas de manera específica por el ID de un torneo.")
    @GetMapping
    public ResponseEntity<List<Ranking>> listarRanking(@RequestParam(required = false) Long torneoId) {
        List<Ranking> rankings = (torneoId != null) ? rankingService.findByTorneoId(torneoId) : rankingService.findAll();
        return new ResponseEntity<>(rankings, HttpStatus.OK);
    }

    @Operation(summary = "Buscar rendimiento de un competidor", description = "Obtiene de forma aislada la posición exacta, victorias y derrotas de un competidor en un torneo.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ficha de rendimiento localizada"),
            @ApiResponse(responseCode = "404", description = "El participante no se encuentra registrado en el torneo consultado")
    })
    @GetMapping("/participante")
    public ResponseEntity<Ranking> buscarPosicionParticipante(
            @RequestParam Long torneoId,
            @RequestParam Long participanteId) {
        Optional<Ranking> ranking = rankingService.findByTorneoIdAndParticipanteId(torneoId, participanteId);
        return ranking.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Buscar registro métrico por ID", description = "Recupera una entrada directa de las tablas utilizando su ID autogenerado.")
    @GetMapping("/{id}")
    public ResponseEntity<Ranking> buscarPorId(@PathVariable Long id) {
        Ranking ranking = rankingService.findById(id);
        return new ResponseEntity<>(ranking, HttpStatus.OK);
    }

    @Operation(summary = "Actualizar estadísticas manuales", description = "Permite a los administradores ajustar o corregir el puntaje, diferencia de mapas o posición de un participante.")
    @PutMapping("/{id}")
    public ResponseEntity<Ranking> actualizarRanking(@PathVariable Long id, @Valid @RequestBody RankingDTO dto) {
        Ranking rankingActualizado = rankingService.updateById(dto, id);
        return new ResponseEntity<>(rankingActualizado, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar registro métrico", description = "Borra definitivamente la fila de puntajes de un participante.")
    @ApiResponse(responseCode = "204", description = "Métrica eliminada correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRegistro(@PathVariable Long id) {
        rankingService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}