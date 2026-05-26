package com.Esport_manager.Game_service.Services;

import com.Esport_manager.Game_service.Models.Juego;
import com.Esport_manager.Game_service.Repositories.JuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JuegoServiceLmpl implements JuegoService {

    @Autowired
    private JuegoRepository repository;

    @Override
    public List<Juego> findAll() {
        return repository.findAll();
    }

    @Override
    public Juego findById(Long id) {
        Optional<Juego> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null; // Or throw RuntimeException if no specific Exception available
    }

    @Override
    public Juego save(Juego juego) {
        return repository.save(juego);
    }

    @Override
    public Juego updateById(Juego juego, Long id) {
        Optional<Juego> optional = repository.findById(id);
        if (optional.isPresent()) {
            Juego j = optional.get();
            j.setNombre(juego.getNombre());
            j.setGenero(juego.getGenero());
            j.setModalidad(juego.getModalidad());
            j.setJugadoresPorEquipo(juego.getJugadoresPorEquipo());
            j.setEmail(juego.getEmail());
            j.setEstado(juego.getEstado());
            return repository.save(j);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
