package com.Esport_manager.Team_service.Controllers;

import com.Esport_manager.Team_service.Models.Equipo;
import com.Esport_manager.Team_service.Models.MiembroEquipo;
import com.Esport_manager.Team_service.Models.Dtos.EquipoDTO;
import com.Esport_manager.Team_service.Models.Dtos.MiembroEquipoDTO;
import com.Esport_manager.Team_service.Services.EquipoService;
import com.Esport_manager.Team_service.Services.MiembroEquipoService;
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
@RequestMapping("/api/v1/equipos")
@Tag(name = "Clubes e Integrantes", description = "Endpoints para fundar escuadras, controlar altas, bajas y reasignaciones tácticas")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private MiembroEquipoService miembroService;

    @Operation(summary = "Fundar una nueva escuadra", description = "Crea un equipo asignándole nombre, juego base e indexando al fundador como su capitán oficial.")
    @PostMapping
    public ResponseEntity<Equipo> crearEquipo(@Valid @RequestBody EquipoDTO dto) {
        Equipo nuevoEquipo = equipoService.save(dto);
        return new ResponseEntity<>(nuevoEquipo, HttpStatus.CREATED);
    }

    @Operation(summary = "Buscar y listar escuadras", description = "Obtiene los clubes creados con filtros jerárquicos por juego principal, capitán o estado.")
    @GetMapping
    public ResponseEntity<List<Equipo>> listarEquipos(
            @RequestParam(required = false) Long juegoPrincipalId,
            @RequestParam(required = false) Long capitanId,
            @RequestParam(required = false) String estado) {
        List<Equipo> equipos;
        if (juegoPrincipalId != null) {
            equipos = equipoService.findByJuegoPrincipalId(juegoPrincipalId);
        } else if (capitanId != null) {
            equipos = equipoService.findByCapitanId(capitanId);
        } else if (estado != null) {
            equipos = equipoService.findByEstado(estado);
        } else {
            equipos = equipoService.findAll();
        }
        return new ResponseEntity<>(equipos, HttpStatus.OK);
    }

    @Operation(summary = "Obtener club por ID", description = "Muestra la ficha técnica e institucional de una escuadra.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Escuadra localizada de forma exitosa"),
            @ApiResponse(responseCode = "404", description = "El ID ingresado no corresponde a ningún club")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Equipo> buscarEquipoPorId(@PathVariable Long id) {
        Equipo equipo = equipoService.findById(id);
        return (equipo == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(equipo, HttpStatus.OK);
    }

    @Operation(summary = "Actualizar directiva del club", description = "Modifica los metadatos institucionales del equipo, reasigna capitanías o cambia el estado corporativo.")
    @PutMapping("/{id}")
    public ResponseEntity<Equipo> actualizarEquipo(@PathVariable Long id, @Valid @RequestBody EquipoDTO dto) {
        Equipo equipoActualizado = equipoService.updateById(dto, id);
        return new ResponseEntity<>(equipoActualizado, HttpStatus.OK);
    }

    @Operation(summary = "Disolver escuadra competitiva", description = "Da de baja de forma total la escuadra eliminándola de los registros activos.")
    @ApiResponse(responseCode = "204", description = "Club competitivo disuelto con éxito")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEquipo(@PathVariable Long id) {
        equipoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Inscribir jugador al roster", description = "Ficha a un usuario asignándole un rol táctico determinado dentro de la alineación de la escuadra.")
    @PostMapping("/{equipoId}/miembros")
    public ResponseEntity<MiembroEquipo> agregarMiembro(@PathVariable Long equipoId, @Valid @RequestBody MiembroEquipoDTO dto) {
        dto.setEquipoId(equipoId);
        MiembroEquipo nuevoMiembro = miembroService.save(dto);
        return new ResponseEntity<>(nuevoMiembro, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar alineación activa del club", description = "Obtiene los perfiles y roles de los jugadores inscritos en la escuadra.")
    @GetMapping("/{equipoId}/miembros")
    public ResponseEntity<List<MiembroEquipo>> listarMiembrosDeEquipo(@PathVariable Long equipoId) {
        List<MiembroEquipo> miembros = miembroService.findByEquipoId(equipoId);
        return new ResponseEntity<>(miembros, HttpStatus.OK);
    }

    @Operation(summary = "Remover jugador de la alineación", description = "Saca a un competidor del roster oficial liberando su plaza en el club.")
    @ApiResponse(responseCode = "204", description = "Jugador removido del roster operativo")
    @DeleteMapping("/{equipoId}/miembros/{usuarioId}")
    public ResponseEntity<Void> expulsarMiembro(@PathVariable Long equipoId, @PathVariable Long usuarioId) {
        miembroService.eliminarPorEquipoYUsuario(equipoId, usuarioId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}