package com.EsportManager.Result_Service.Controllers;

import com.EsportManager.Result_Service.Models.Resultados;
import com.EsportManager.Result_Service.Models.Dtos.ResultadosDTO;
import com.EsportManager.Result_Service.Services.ResultadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/resultados")
public class ResultadosController {

    @Autowired
    private ResultadosService resultadosService;

    // 1. Crear resultado (El Service verificará con Feign que la partida exista)
    @PostMapping
    public ResponseEntity<Resultados> registrarResultado(@Valid @RequestBody ResultadosDTO dto) {
        Resultados nuevoResultado = resultadosService.save(dto); // Requiere adaptar a DTO
        return new ResponseEntity<>(nuevoResultado, HttpStatus.CREATED);
    }

    // 2. Listar resultados (Filtrados por torneo o partida según el caso)
    @GetMapping
    public ResponseEntity<List<Resultados>> listarResultados(
            @RequestParam(required = false) Long torneoId,
            @RequestParam(required = false) Long partidaId) {

        List<Resultados> resultados;

        if (torneoId != null) {
            resultados = resultadosService.findByTorneoId(torneoId);
        } else if (partidaId != null) {
            resultados = resultadosService.findByPartidaId(partidaId);
        } else {
            resultados = resultadosService.findAll();
        }

        return new ResponseEntity<>(resultados, HttpStatus.OK);
    }

    // 3. Buscar resultado por ID
    @GetMapping("/{id}")
    public ResponseEntity<Resultados> buscarPorId(@PathVariable Long id) {
        Resultados resultado = resultadosService.findById(id);
        if (resultado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    // 4. Actualizar resultado antes de validación
    @PutMapping("/{id}")
    public ResponseEntity<Resultados> actualizarResultado(
            @PathVariable Long id,
            @Valid @RequestBody ResultadosDTO dto,
            @RequestHeader(value = "Rol-Usuario", required = false) String rolUsuario) {

        Resultados resultadoActualizado = resultadosService.updateById(dto, id);
        return new ResponseEntity<>(resultadoActualizado, HttpStatus.OK);
    }

    // 5. Anular resultado con justificación
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> anularResultado(@PathVariable Long id) {
        resultadosService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
