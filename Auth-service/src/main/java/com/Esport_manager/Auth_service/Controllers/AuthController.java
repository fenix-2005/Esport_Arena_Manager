package com.Esport_manager.Auth_service.Controllers;

import com.Esport_manager.Auth_service.Models.CuentaAcceso;
import com.Esport_manager.Auth_service.Repositories.CuentaAccesoRepository;
import com.Esport_manager.Auth_service.Security.JwtServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Autenticación", description = "Login, registro y emisión de tokens JWT")
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
    public record RegistroSolicitud(
            @NotBlank(message = "El correo es obligatorio")
            @Email(message = "Formato de correo inválido")
            String email,
            @NotBlank(message = "La contraseña es obligatoria")
            @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
            String password
    ) {}
    public record UsuarioRespuesta(Long id, String email, String rol) {}
    public record LoginRespuesta(String token, UsuarioRespuesta usuario) {}

    @Operation(summary = "Iniciar sesión", description = "Valida credenciales y retorna un JWT.")
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginSolicitud datos) {
        return cuentas.findByEmail(datos.email().trim())
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

    @Operation(summary = "Registrar jugador", description = "Crea una cuenta nueva con rol JUGADOR y retorna su JWT.")
    @PostMapping("/registro")
    public ResponseEntity<?> registro(@Valid @RequestBody RegistroSolicitud datos) {
        String correo = datos.email().trim().toLowerCase();

        if (cuentas.findByEmail(correo).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("mensaje", "Ya existe una cuenta registrada con ese correo"));
        }

        CuentaAcceso nueva = new CuentaAcceso();
        nueva.setEmail(correo);
        nueva.setPassword(encoder.encode(datos.password()));
        nueva.setRol("JUGADOR");
        nueva.setEstado("ACTIVO");
        nueva.setFechaCreacion(LocalDate.now());

        CuentaAcceso guardada = cuentas.save(nueva);
        String token = jwt.generar(guardada.getEmail(), guardada.getRol());
        var usuario = new UsuarioRespuesta(guardada.getId(), guardada.getEmail(), guardada.getRol());

        return ResponseEntity.status(HttpStatus.CREATED).body(new LoginRespuesta(token, usuario));
    }
}