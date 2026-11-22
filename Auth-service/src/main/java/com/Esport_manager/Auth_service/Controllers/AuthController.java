package com.Esport_manager.Auth_service.Controllers;


import com.Esport_manager.Auth_service.Repositories.CuentaAccesoRepository;
import com.Esport_manager.Auth_service.Security.JwtServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Autenticación", description = "Login y emisión de tokens JWT")
public class AuthController {

    private final CuentaAccesoRepository cuentas;
    private final PasswordEncoder encoder;
    private final JwtServicio jwt;

    public AuthController(CuentaAccesoRepository cuentas, PasswordEncoder encoder, JwtServicio jwt) {
        this.cuentas = cuentas;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    public record LoginSolicitud(@NotBlank String email, @NotBlank String password) {}
    public record UsuarioRespuesta(Long id, String email, String rol) {}
    public record LoginRespuesta(String token, UsuarioRespuesta usuario) {}

    @Operation(summary = "Iniciar sesión", description = "Valida credenciales y retorna un JWT.")
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginSolicitud datos) {
        return cuentas.findByEmail(datos.email())
                .filter(c -> "ACTIVO".equalsIgnoreCase(c.getEstado()))
                .filter(c -> encoder.matches(datos.password(), c.getPassword()))
                .map(c -> {
                    String token = jwt.generar(c.getEmail(), c.getRol());
                    var usuario = new UsuarioRespuesta(c.getId(), c.getEmail(), c.getRol());
                    return ResponseEntity.ok((Object) new LoginRespuesta(token, usuario));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("mensaje", "Email o contraseña incorrectos")));
    }
}
