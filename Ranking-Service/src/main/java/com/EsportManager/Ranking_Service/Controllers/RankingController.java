package com.EsportManager.Ranking_Service.Controllers;

import com.EsportManager.Ranking_Service.Models.Ranking;
import com.EsportManager.Ranking_Service.Models.Dtos.RankingDTO;
import com.EsportManager.Ranking_Service.Services.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/rankings")
public class RankingController {

    @Autowired
    private RankingService rankingService;

    // 1. Crear registro de ranking inicial para un participante
    @PostMapping
    public ResponseEntity<Ranking> crearRegistroRanking(@Valid @RequestBody RankingDTO dto) {
        Ranking nuevoRanking = rankingService.save(dto);
        return new ResponseEntity<>(nuevoRanking, HttpStatus.CREATED);
    }

    // 2. Listar ranking
    @GetMapping
    public ResponseEntity<List<Ranking>> listarRanking(
            @RequestParam(required = false) Long torneoId) {

        List<Ranking> rankings;
        if (torneoId != null) {
            rankings = rankingService.findByTorneoId(torneoId);
        } else {
            rankings = rankingService.findAll();
        }
        return new ResponseEntity<>(rankings, HttpStatus.OK);
    }

    // 3. Buscar posición específica de un participante en un torneo
    @GetMapping("/participante")
    public ResponseEntity<Ranking> buscarPosicionParticipante(
            @RequestParam Long torneoId,
            @RequestParam Long participanteId) {

        Optional<Ranking> ranking = rankingService.findByTorneoIdAndParticipanteId(torneoId, participanteId);
        if (ranking.isPresent()) {
            return new ResponseEntity<>(ranking.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // 4. Buscar ranking por ID del registro
    @GetMapping("/{id}")
    public ResponseEntity<Ranking> buscarPorId(@PathVariable Long id) {
        Ranking ranking = rankingService.findById(id);
        return new ResponseEntity<>(ranking, HttpStatus.OK);
    }

    // 5. Actualizar puntos y estadísticas (manual)
    @PutMapping("/{id}")
    public ResponseEntity<Ranking> actualizarRanking(
            @PathVariable Long id,
            @Valid @RequestBody RankingDTO dto) {
        Ranking rankingActualizado = rankingService.updateById(dto, id);
        return new ResponseEntity<>(rankingActualizado, HttpStatus.OK);
    }

    // 6. Eliminar o reiniciar registro de ranking
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRegistro(@PathVariable Long id) {
        rankingService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
