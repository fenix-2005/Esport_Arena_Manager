package com.Esport_manager.User_service.Controllers;

import com.Esport_manager.User_service.Models.Usuario;
import com.Esport_manager.User_service.Models.Dtos.UsuarioDTO;
import com.Esport_manager.User_service.Services.UsuarioService;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Perfiles de Usuarios", description = "Endpoints principales para el manejo de cuentas, perfiles e información de competidores")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Registrar nuevo perfil de usuario", description = "Crea la cuenta troncal para un usuario en la plataforma asignándole un rol inicial.")
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody UsuarioDTO dto) {
        Usuario nuevoUsuario = usuarioService.save(dto);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar perfiles registrados", description = "Recupera los datos de los usuarios registrados del sistema permitiendo aplicar filtros de rol o estado.")
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

    @Operation(summary = "Buscar usuario por ID", description = "Retorna el perfil completo y datos de contacto de un usuario utilizando su ID único.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Perfil de usuario localizado"),
            @ApiResponse(responseCode = "404", description = "El ID consultado no corresponde a ningún usuario")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        return (usuario == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @Operation(summary = "Buscar perfil por Nickname", description = "Busca la disponibilidad o información de un competidor a través de su seudónimo exacto.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Nickname localizado en los registros"),
            @ApiResponse(responseCode = "404", description = "El nickname consultado no se encuentra registrado")
    })
    @GetMapping("/nickname/{nickname}")
    public ResponseEntity<Usuario> buscarPorNickname(@PathVariable String nickname) {
        Optional<Usuario> usuario = usuarioService.findByNickname(nickname);
        return usuario.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Modificar datos de perfil", description = "Permite actualizar la información personal, correo electrónico o estado operativo de un usuario.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Perfil de usuario actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "No se pudo actualizar debido a que el usuario no existe")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioDTO dto) {
        Usuario usuarioActualizado = usuarioService.updateById(dto, id);
        return (usuarioActualizado == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
    }

    @Operation(summary = "Inhabilitar perfil de usuario", description = "Remueve de forma física el registro del usuario del padrón activo.")
    @ApiResponse(responseCode = "204", description = "Perfil eliminado correctamente de la base de datos")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivarUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}