package com.Esport_manager.Team_service.Controllers;

import com.Esport_manager.Team_service.Models.Equipo;
import com.Esport_manager.Team_service.Models.MiembroEquipo;
import com.Esport_manager.Team_service.Models.Dtos.EquipoDTO;
import com.Esport_manager.Team_service.Models.Dtos.MiembroEquipoDTO;
import com.Esport_manager.Team_service.Services.EquipoService;
import com.Esport_manager.Team_service.Services.MiembroEquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private MiembroEquipoService miembroService;


    // 1. Crear equipo
    @PostMapping
    public ResponseEntity<Equipo> crearEquipo(@Valid @RequestBody EquipoDTO dto) {
        Equipo nuevoEquipo = equipoService.save(dto);
        return new ResponseEntity<>(nuevoEquipo, HttpStatus.CREATED);
    }

    // 2. Listar equipos (Con filtros por juego, capitán o estado)
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

    // 3. Buscar equipo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Equipo> buscarEquipoPorId(@PathVariable Long id) {
        Equipo equipo = equipoService.findById(id);
        if (equipo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(equipo, HttpStatus.OK);
    }

    // 4. Actualizar nombre, capitán o estado
    @PutMapping("/{id}")
    public ResponseEntity<Equipo> actualizarEquipo(
            @PathVariable Long id,
            @Valid @RequestBody EquipoDTO dto) {
        Equipo equipoActualizado = equipoService.updateById(dto, id);
        return new ResponseEntity<>(equipoActualizado, HttpStatus.OK);
    }

    // 5. Desactivar o Eliminar equipo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEquipo(@PathVariable Long id) {
        equipoService.deleteById(id); // O cambiar de estado a INACTIVO según implementes
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // 6. Agregar un miembro al equipo
    @PostMapping("/{equipoId}/miembros")
    public ResponseEntity<MiembroEquipo> agregarMiembro(
            @PathVariable Long equipoId,
            @Valid @RequestBody MiembroEquipoDTO dto) {

        dto.setEquipoId(equipoId);
        MiembroEquipo nuevoMiembro = miembroService.save(dto);
        return new ResponseEntity<>(nuevoMiembro, HttpStatus.CREATED);
    }

    // 7. Listar todos los miembros de un equipo
    @GetMapping("/{equipoId}/miembros")
    public ResponseEntity<List<MiembroEquipo>> listarMiembrosDeEquipo(@PathVariable Long equipoId) {
        List<MiembroEquipo> miembros = miembroService.findByEquipoId(equipoId);
        return new ResponseEntity<>(miembros, HttpStatus.OK);
    }

    // 8. Expulsar a un miembro del equipo
    @DeleteMapping("/{equipoId}/miembros/{usuarioId}")
    public ResponseEntity<Void> expulsarMiembro(
            @PathVariable Long equipoId,
            @PathVariable Long usuarioId) {

        miembroService.eliminarPorEquipoYUsuario(equipoId, usuarioId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
