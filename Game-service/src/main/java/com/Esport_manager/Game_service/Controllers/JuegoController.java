package com.Esport_manager.Game_service.Controllers;

import com.Esport_manager.Game_service.Models.Juego;
import com.Esport_manager.Game_service.Models.Dtos.JuegoDTO;
import com.Esport_manager.Game_service.Services.JuegoService;
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
@RequestMapping("/api/v1/juegos")
@Tag(name = "Catálogo de Juegos", description = "Administración de títulos, reglas de emparejamiento y categorías")
public class JuegoController {

    @Autowired
    private JuegoService juegoService;

    @Operation(summary = "Registrar nuevo videojuego", description = "Inserta un juego soportado para las competencias de la organización.")
    @PostMapping
    public ResponseEntity<Juego> crearJuego(@Valid @RequestBody JuegoDTO dto) {
        Juego nuevoJuego = juegoService.save(dto);
        return new ResponseEntity<>(nuevoJuego, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar catálogo de videojuegos", description = "Obtiene los títulos globales con opción de filtrar únicamente por estado operativo.")
    @ApiResponse(responseCode = "200", description = "Catálogo recuperado exitosamente")
    @GetMapping
    public ResponseEntity<List<Juego>> listarJuegos(@RequestParam(required = false) String estado) {
        List<Juego> juegos = (estado != null) ? juegoService.findByEstado(estado) : juegoService.findAll();
        return new ResponseEntity<>(juegos, HttpStatus.OK);
    }

    @Operation(summary = "Buscar videojuego por ID", description = "Obtiene las configuraciones técnicas de un juego utilizando su clave única.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Videojuego localizado correctamente"),
            @ApiResponse(responseCode = "404", description = "El ID ingresado no corresponde a ningún juego")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Juego> buscarPorId(@PathVariable Long id) {
        Juego juego = juegoService.findById(id);
        return (juego == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(juego, HttpStatus.OK);
    }

    @Operation(summary = "Buscar videojuego por Nombre", description = "Busca un juego mediante coincidencia exacta de su nombre registrado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Videojuego localizado"),
            @ApiResponse(responseCode = "404", description = "No se encontraron coincidencias para el nombre")
    })
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Juego> buscarPorNombre(@PathVariable String nombre) {
        Juego juego = juegoService.findByNombre(nombre);
        return (juego == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(juego, HttpStatus.OK);
    }

    @Operation(summary = "Modificar parámetros de juego", description = "Actualiza las modalidades de emparejamiento, género o reglas del título.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Videojuego actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "No se pudo actualizar porque el juego no existe")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Juego> actualizarJuego(@PathVariable Long id, @Valid @RequestBody JuegoDTO dto) {
        Juego juegoActualizado = juegoService.updateById(dto, id);
        return (juegoActualizado == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(juegoActualizado, HttpStatus.OK);
    }

    @Operation(summary = "Remover juego del sistema", description = "Elimina de forma física el registro del videojuego del catálogo.")
    @ApiResponse(responseCode = "204", description = "Videojuego eliminado correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarJuego(@PathVariable Long id) {
        juegoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}