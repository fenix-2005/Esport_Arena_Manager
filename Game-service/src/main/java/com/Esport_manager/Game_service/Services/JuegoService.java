package com.Esport_manager.Game_service.Services;

import com.Esport_manager.Game_service.Exceptions.JuegoDuplicadoException;
import com.Esport_manager.Game_service.Exceptions.JugadoresPorEquipoInvalidosException;
import com.Esport_manager.Game_service.Models.Juego;
import com.Esport_manager.Game_service.Repositories.JuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JuegoService {

    // Registra juegos nuevos sin repetir nombres y valida que los jugadores sean números positivos.

    @Autowired
    private JuegoRepository repository;

    public Juego crearJuego(Juego juego) {
        if (repository.findByNombre(juego.getNombre()).isPresent()) {
            throw new JuegoDuplicadoException("El juego " + juego.getNombre() + " ya está registrado.");
        }
        if (juego.getJugadoresPorEquipo() <= 0) {
            throw new JugadoresPorEquipoInvalidosException("La cantidad de jugadores debe ser mayor a 0.");
        }
        juego.setEstado("ACTIVO");
        return repository.save(juego);
    }
}
