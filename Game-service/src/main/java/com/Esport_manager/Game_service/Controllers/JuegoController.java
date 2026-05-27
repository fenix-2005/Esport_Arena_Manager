package com.Esport_manager.Game_service.Controllers;

import com.Esport_manager.Game_service.Models.Juego;
import com.Esport_manager.Game_service.Models.Dtos.JuegoDTO;
import com.Esport_manager.Game_service.Services.JuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/juegos")
public class JuegoController {

    @Autowired
    private JuegoService juegoService;

    // 1. Crear un nuevo juego
    @PostMapping
    public ResponseEntity<Juego> crearJuego(@Valid @RequestBody JuegoDTO dto) {
        Juego nuevoJuego = juegoService.save(dto);
        return new ResponseEntity<>(nuevoJuego, HttpStatus.CREATED);
    }

    // 2. Listar juegos
    @GetMapping
    public ResponseEntity<List<Juego>> listarJuegos(@RequestParam(required = false) String estado) {
        List<Juego> juegos;
        if (estado != null) {
            juegos = juegoService.findByEstado(estado);
        } else {
            juegos = juegoService.findAll();
        }
        return new ResponseEntity<>(juegos, HttpStatus.OK);
    }

    // 3. Buscar juego por ID
    @GetMapping("/{id}")
    public ResponseEntity<Juego> buscarPorId(@PathVariable Long id) {
        Juego juego = juegoService.findById(id);
        if (juego == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(juego, HttpStatus.OK);
    }

    // 4. Buscar juego por Nombre
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Juego> buscarPorNombre(@PathVariable String nombre) {
        Juego juego = juegoService.findByNombre(nombre);
        if (juego == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(juego, HttpStatus.OK);
    }

    // 5. Actualizar modalidad o reglas de un juego
    @PutMapping("/{id}")
    public ResponseEntity<Juego> actualizarJuego(@PathVariable Long id, @Valid @RequestBody JuegoDTO dto) {
        Juego juegoActualizado = juegoService.updateById(dto, id);
        if (juegoActualizado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(juegoActualizado, HttpStatus.OK);
    }

    // 6. Eliminar / Desactivar juego
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarJuego(@PathVariable Long id) {
        juegoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
