package com.EsportManager.Result_Service.Controllers;

import com.EsportManager.Result_Service.Models.Resultados;
import com.EsportManager.Result_Service.Models.Dtos.ResultadosDTO;
import com.EsportManager.Result_Service.Services.ResultadosService;
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
@RequestMapping("/api/v1/resultados")
@Tag(name = "Planillas de Resultados", description = "Registro oficial de scores, marcas y ganadores de las llaves abiertas")
public class ResultadosController {

    @Autowired
    private ResultadosService resultadosService;

    @Operation(summary = "Registrar acta de resultado", description = "Cierra una partida cargando los scores oficiales. Valida de forma remota que el ID del enfrentamiento sea legítimo.")
    @PostMapping
    public ResponseEntity<Resultados> registrarResultado(@Valid @RequestBody ResultadosDTO dto) {
        Resultados nuevoResultado = resultadosService.save(dto);
        return new ResponseEntity<>(nuevoResultado, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar actas de resultados", description = "Obtiene todas las planillas cerradas de la plataforma, filtrando de forma opcional por torneo o partida.")
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

    @Operation(summary = "Buscar resultado por ID", description = "Muestra de forma exacta los detalles y marcadores puntuales de un acta específica.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Planilla localizada e importada"),
            @ApiResponse(responseCode = "404", description = "El acta consultada no existe")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Resultados> buscarPorId(@PathVariable Long id) {
        Resultados resultado = resultadosService.findById(id);
        return (resultado == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Operation(summary = "Modificar marcadores de planilla", description = "Permite editar las variables de puntaje ingresadas antes de la validación final del comisario.")
    @PutMapping("/{id}")
    public ResponseEntity<Resultados> actualizarResultado(
            @PathVariable Long id,
            @Valid @RequestBody ResultadosDTO dto,
            @RequestHeader(value = "Rol-Usuario", required = false) String rolUsuario) {
        Resultados resultadoActualizado = resultadosService.updateById(dto, id);
        return new ResponseEntity<>(resultadoActualizado, HttpStatus.OK);
    }

    @Operation(summary = "Anular acta de juego", description = "Invalida o elimina un marcador por disputas técnicas o infracciones de reglamento detectadas.")
    @ApiResponse(responseCode = "204", description = "Planilla de juego removida de los registros oficiales")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> anularResultado(@PathVariable Long id) {
        resultadosService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}