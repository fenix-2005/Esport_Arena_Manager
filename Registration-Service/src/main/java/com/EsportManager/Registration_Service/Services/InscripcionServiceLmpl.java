package com.EsportManager.Registration_Service.Services;

import com.EsportManager.Registration_Service.Clients.EquipoClient;
import com.EsportManager.Registration_Service.Clients.TorneoClient;
import com.EsportManager.Registration_Service.Clients.UsuarioClient;
import com.EsportManager.Registration_Service.Models.Dtos.InscripcionDTO;
import com.EsportManager.Registration_Service.Models.Inscripcion;
import com.EsportManager.Registration_Service.Repositories.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscripcionServiceLmpl implements InscripcionService {

    @Autowired
    private InscripcionRepository repository;

    @Autowired
    private TorneoClient torneoClient;

    @Autowired
    private EquipoClient equipoClient;

    @Autowired
    private UsuarioClient usuarioClient;

    @Override
    public List<Inscripcion> findAll() {
        return repository.findAll();
    }

    @Override
    public Inscripcion findById(Long id) {
        Optional<Inscripcion> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Inscripcion save(InscripcionDTO dto) {
        // Validaciones externas via Feign
        try {
            if (dto.getTorneoId() != null) {
                torneoClient.obtenerTorneoPorId(dto.getTorneoId());
            }
            if (dto.getEquipoId() != null) {
                equipoClient.obtenerEquipoPorId(dto.getEquipoId());
            }
            if (dto.getJugadorId() != null) {
                usuarioClient.obtenerUsuarioPorId(dto.getJugadorId());
            }
        } catch (Exception e) {
            // En un caso real, manejaríamos excepciones personalizadas o dejaríamos que Feign las propague
            throw new RuntimeException("Error de validación externa: " + e.getMessage());
        }

        Inscripcion i = new Inscripcion();
        i.setTorneoId(dto.getTorneoId());
        i.setEquipoId(dto.getEquipoId());
        i.setJugadorId(dto.getJugadorId());
        i.setTipoParticipante(dto.getTipoParticipante());
        i.setEstado(dto.getEstado());
        i.setFechaInscripcion(dto.getFechaInscripcion());
        return repository.save(i);
    }

    @Override
    public Inscripcion updateById(InscripcionDTO dto, Long id) {
        Optional<Inscripcion> optional = repository.findById(id);
        if (optional.isPresent()) {
            Inscripcion i = optional.get();
            i.setTorneoId(dto.getTorneoId());
            i.setEquipoId(dto.getEquipoId());
            i.setJugadorId(dto.getJugadorId());
            i.setTipoParticipante(dto.getTipoParticipante());
            i.setEstado(dto.getEstado());
            i.setFechaInscripcion(dto.getFechaInscripcion());
            return repository.save(i);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Inscripcion> findByTorneoId(Long torneoId) {
        return repository.findByTorneoId(torneoId);
    }

    @Override
    public List<Inscripcion> findByEquipoId(Long equipoId) {
        return repository.findByEquipoId(equipoId);
    }

    @Override
    public List<Inscripcion> findByJugadorId(Long jugadorId) {
        return repository.findByJugadorId(jugadorId);
    }
}
