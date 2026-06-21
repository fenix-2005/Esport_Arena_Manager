package com.Esport_manager.Tournament_service.Controllers;

import com.Esport_manager.Tournament_service.Models.Torneo;
import com.Esport_manager.Tournament_service.Models.Dtos.TorneoDTO;
import com.Esport_manager.Tournament_service.Services.TorneoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/torneos")
@Tag(name = "Cartelera de Torneos", description = "Endpoints de configuración de ligas, copas abiertas, cronogramas y modalidades de copas")
public class TorneoController {

    @Autowired
    private TorneoService torneoService;

    @Operation(summary = "Publicar un nuevo torneo", description = "Crea las bases de una competencia asignando el juego base, las fechas de inicio/fin y los cupos permitidos.")
    @PostMapping
    public ResponseEntity<Torneo> crearTorneo(@Valid @RequestBody TorneoDTO dto) {
        Torneo nuevoTorneo = torneoService.save(dto);
        return new ResponseEntity<>(nuevoTorneo, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar torneos de la plataforma", description = "Muestra todos los campeonatos creados, permitiendo filtrar dinámicamente por juego, estado operativo o fecha de inicio.")
    @GetMapping
    public ResponseEntity<List<Torneo>> listarTorneos(
            @RequestParam(required = false) Long juegoId,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio) {
        List<Torneo> torneos;
        if (juegoId != null) {
            torneos = torneoService.findByJuegoId(juegoId);
        } else if (estado != null) {
            torneos = torneoService.findByEstado(estado);
        } else if (fechaInicio != null) {
            torneos = torneoService.findByFechaInicio(fechaInicio);
        } else {
            torneos = torneoService.findAll();
        }
        return new ResponseEntity<>(torneos, HttpStatus.OK);
    }

    @Operation(summary = "Buscar torneo por ID", description = "Obtiene los metadatos completos y configuraciones de cupo de un torneo específico.")
    @GetMapping("/{id}")
    public ResponseEntity<Torneo> buscarPorId(@PathVariable Long id) {
        Torneo torneo = torneoService.findById(id);
        return new ResponseEntity<>(torneo, HttpStatus.OK);
    }

    @Operation(summary = "Modificar bases del torneo", description = "Permite a los organizadores aplazar fechas, ampliar cupos máximos de participación o cambiar el estado del torneo.")
    @PutMapping("/{id}")
    public ResponseEntity<Torneo> actualizarTorneo(@PathVariable Long id, @Valid @RequestBody TorneoDTO dto) {
        Torneo torneoActualizado = torneoService.updateById(dto, id);
        return new ResponseEntity<>(torneoActualizado, HttpStatus.OK);
    }

    @Operation(summary = "Cerrar o cancelar torneo", description = "Da de baja una copa del sistema eliminándola de los registros activos de la cartelera.")
    @ApiResponse(responseCode = "204", description = "Campeonato removido correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarTorneo(@PathVariable Long id) {
        torneoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}