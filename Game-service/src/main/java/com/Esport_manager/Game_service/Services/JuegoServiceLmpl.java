package com.Esport_manager.Game_service.Services;

import com.Esport_manager.Game_service.Models.Dtos.JuegoDTO;
import com.Esport_manager.Game_service.Models.Juego;
import com.Esport_manager.Game_service.Repositories.JuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JuegoServiceLmpl implements JuegoService {

    @Autowired
    private JuegoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Juego> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Juego findById(Long id) {
        Optional<Juego> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null; // Or throw RuntimeException if no specific Exception available
    }

    @Override
    @Transactional
    public Juego save(JuegoDTO juegoDTO) {
        Juego j = new Juego();
        j.setNombre(juegoDTO.getNombre());
        j.setGenero(juegoDTO.getGenero());
        j.setModalidad(juegoDTO.getModalidad());
        j.setJugadoresPorEquipo(juegoDTO.getJugadoresPorEquipo());
        j.setEmail(juegoDTO.getEmail());
        j.setEstado(juegoDTO.getEstado());
        return repository.save(j);
    }

    @Override
    @Transactional
    public Juego updateById(JuegoDTO juegoDTO, Long id) {
        Optional<Juego> optional = repository.findById(id);
        if (optional.isPresent()) {
            Juego j = optional.get();
            j.setNombre(juegoDTO.getNombre());
            j.setGenero(juegoDTO.getGenero());
            j.setModalidad(juegoDTO.getModalidad());
            j.setJugadoresPorEquipo(juegoDTO.getJugadoresPorEquipo());
            j.setEmail(juegoDTO.getEmail());
            j.setEstado(juegoDTO.getEstado());
            return repository.save(j);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Juego> findByEstado(String estado) {
        return repository.findByEstado(estado);
    }

    @Override
    @Transactional(readOnly = true)
    public Juego findByNombre(String nombre) {
        Optional<Juego> optional = repository.findByNombre(nombre);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
}
