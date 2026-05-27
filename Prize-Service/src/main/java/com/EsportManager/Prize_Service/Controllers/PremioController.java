package com.EsportManager.Prize_Service.Controllers;

import com.EsportManager.Prize_Service.Models.Premio;
import com.EsportManager.Prize_Service.Models.PremioAsignado;
import com.EsportManager.Prize_Service.Models.Dtos.PremioDTO;
import com.EsportManager.Prize_Service.Models.Dtos.PremioAsignadoDTO;
import com.EsportManager.Prize_Service.Services.PremioService;
import com.EsportManager.Prize_Service.Services.PremioAsignadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/premios")
public class PremioController {

    @Autowired
    private PremioService premioService;

    @Autowired
    private PremioAsignadoService premioAsignadoService;


    // 1. Crear un premio nuevo
    @PostMapping
    public ResponseEntity<Premio> crearPremio(@Valid @RequestBody PremioDTO dto) {
        Premio nuevoPremio = premioService.save(dto);
        return new ResponseEntity<>(nuevoPremio, HttpStatus.CREATED);
    }

    // 2. Listar premios
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

    // 3. Buscar premio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Premio> buscarPremioPorId(@PathVariable Long id) {
        Premio premio = premioService.findById(id);
        return new ResponseEntity<>(premio, HttpStatus.OK);
    }

    // 4. Actualizar un premio (antes de ser asignado)
    @PutMapping("/{id}")
    public ResponseEntity<Premio> actualizarPremio(
            @PathVariable Long id,
            @Valid @RequestBody PremioDTO dto) {
        Premio premioActualizado = premioService.updateById(dto, id);
        return new ResponseEntity<>(premioActualizado, HttpStatus.OK);
    }

    // 5. Eliminar un premio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPremio(@PathVariable Long id) {
        premioService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 6. Asignar un premio a un participante
    @PostMapping("/asignar")
    public ResponseEntity<PremioAsignado> asignarPremio(@Valid @RequestBody PremioAsignadoDTO dto) {
        PremioAsignado nuevaAsignacion = premioAsignadoService.save(dto);
        return new ResponseEntity<>(nuevaAsignacion, HttpStatus.CREATED);
    }

    // 7. Listar premios asignados
    @GetMapping("/asignados")
    public ResponseEntity<List<PremioAsignado>> listarPremiosAsignados(
            @RequestParam(required = false) Long participanteId) {

        List<PremioAsignado> asignados;
        if (participanteId != null) {
            asignados = premioAsignadoService.findByParticipanteId(participanteId); // Requiere agregar en el Service
        } else {
            asignados = premioAsignadoService.findAll();
        }
        return new ResponseEntity<>(asignados, HttpStatus.OK);
    }
}
