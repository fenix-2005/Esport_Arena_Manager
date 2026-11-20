package com.Esport_manager.Auth_service.Security;

import com.Esport_manager.Auth_service.Models.CuentaAcceso;
import com.Esport_manager.Auth_service.Repositories.CuentaAccesoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
public class DatosIniciales {

    @Bean
    CommandLineRunner usuariosDePrueba(CuentaAccesoRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.count() > 0) return;

            CuentaAcceso admin = new CuentaAcceso();
            admin.setEmail("admin@esports.cl");
            admin.setPassword(encoder.encode("clave123"));
            admin.setRol("ADMIN");
            admin.setEstado("ACTIVO");
            admin.setFechaCreacion(LocalDate.now());
            repo.save(admin);

            CuentaAcceso jugador = new CuentaAcceso();
            jugador.setEmail("jugador@esports.cl");
            jugador.setPassword(encoder.encode("clave123"));
            jugador.setRol("JUGADOR");
            jugador.setEstado("ACTIVO");
            jugador.setFechaCreacion(LocalDate.now());
            repo.save(jugador);
        };
    }
}
