package com.Esport_manager.User_service.Controllers;

import com.Esport_manager.User_service.Models.Usuario;
import com.Esport_manager.User_service.Models.Dtos.UsuarioDTO;
import com.Esport_manager.User_service.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // 1. Crear un nuevo usuario (Jugador, Organizador o Admin)
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody UsuarioDTO dto) {

        Usuario nuevoUsuario = usuarioService.save(dto);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    // 2. Listar usuarios (Con filtros opcionales requeridos por el caso)
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(
            @RequestParam(required = false) String rol,
            @RequestParam(required = false) String estado) {

        List<Usuario> usuarios;

        if (rol != null) {
            usuarios = usuarioService.findByRol(rol);
        } else if (estado != null) {
            usuarios = usuarioService.findByEstado(estado);
        } else {
            usuarios = usuarioService.findAll();
        }

        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // 3. Buscar usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    // 4. Buscar usuario por Nickname (Muy útil para búsquedas en el Frontend)
    @GetMapping("/nickname/{nickname}")
    public ResponseEntity<Usuario> buscarPorNickname(@PathVariable String nickname) {
        Optional<Usuario> usuario = usuarioService.findByNickname(nickname);
        if (usuario.isPresent()) {
            return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // 5. Actualizar datos o estado de un usuario
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioDTO dto) {
        Usuario usuarioActualizado = usuarioService.updateById(dto, id);
        if (usuarioActualizado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
    }

    // 6. Desactivar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivarUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
