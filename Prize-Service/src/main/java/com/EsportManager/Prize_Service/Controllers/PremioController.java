package com.EsportManager.Prize_Service.Controllers;

import com.EsportManager.Prize_Service.Models.Premio;
import com.EsportManager.Prize_Service.Models.PremioAsignado;
import com.EsportManager.Prize_Service.Models.Dtos.PremioDTO;
import com.EsportManager.Prize_Service.Models.Dtos.PremioAsignadoDTO;
import com.EsportManager.Prize_Service.Services.PremioService;
import com.EsportManager.Prize_Service.Services.PremioAsignadoService;
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
@RequestMapping("/api/v1/premios")
@Tag(name = "Gestión de Premios", description = "Endpoints para el control de incentivos, pozos y adjudicaciones")
public class PremioController {

    @Autowired
    private PremioService premioService;

    @Autowired
    private PremioAsignadoService premioAsignadoService;

    @Operation(summary = "Crear nuevo premio", description = "Configura una recompensa específica (efectivo, hardware, medallas) asociada a un torneo.")
    @PostMapping
    public ResponseEntity<Premio> crearPremio(@Valid @RequestBody PremioDTO dto) {
        Premio nuevoPremio = premioService.save(dto);
        return new ResponseEntity<>(nuevoPremio, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar premios configurados", description = "Recupera la lista general de premios con filtros por torneo o posición de podio.")
    @GetMapping
    public ResponseEntity<List<Premio>> listarPremios(
            @RequestParam(required = false) Long torneoId,
            @RequestParam(required = false) Integer posicion) {
        List<Premio> premios;
        if (torneoId != null) {
            premios = premioService.findByTorneoId(torneoId);
        } else if (posicion != null) {
            premios = premioService.findByPosicion(posicion);
        } else {
            premios = premioService.findAll();
        }
        return new ResponseEntity<>(premios, HttpStatus.OK);
    }

    @Operation(summary = "Buscar premio por ID", description = "Obtiene los detalles del premio seleccionado.")
    @GetMapping("/{id}")
    public ResponseEntity<Premio> buscarPremioPorId(@PathVariable Long id) {
        Premio premio = premioService.findById(id);
        return new ResponseEntity<>(premio, HttpStatus.OK);
    }

    @Operation(summary = "Modificar datos de un premio", description = "Permite corregir descripciones o reajustar los valores monetarios antes de congelar el torneo.")
    @PutMapping("/{id}")
    public ResponseEntity<Premio> actualizarPremio(@PathVariable Long id, @Valid @RequestBody PremioDTO dto) {
        Premio premioActualizado = premioService.updateById(dto, id);
        return new ResponseEntity<>(premioActualizado, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar premio del catálogo", description = "Remueve un incentivo configurado del registro permanente.")
    @ApiResponse(responseCode = "204", description = "Premio borrado con éxito")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPremio(@PathVariable Long id) {
        premioService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Adjudicar premio a un ganador", description = "Vincula un premio específico al identificador del participante triunfador del torneo.")
    @PostMapping("/asignar")
    public ResponseEntity<PremioAsignado> asignarPremio(@Valid @RequestBody PremioAsignadoDTO dto) {
        PremioAsignado nuevaAsignacion = premioAsignadoService.save(dto);
        return new ResponseEntity<>(nuevaAsignacion, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar historial de premios entregados", description = "Obtiene todos los premios que ya fueron cobrados, con opción de filtrar por participante.")
    @GetMapping("/asignados")
    public ResponseEntity<List<PremioAsignado>> listarPremiosAsignados(@RequestParam(required = false) Long participanteId) {
        List<PremioAsignado> asignados = (participanteId != null) ?
                premioAsignadoService.findByParticipanteId(participanteId) : premioAsignadoService.findAll();
        return new ResponseEntity<>(asignados, HttpStatus.OK);
    }
}