package com.EsportManager.Sanction_Service.Services;

import com.EsportManager.Sanction_Service.Clients.EquipoClient;
import com.EsportManager.Sanction_Service.Clients.UsuarioClient;
import com.EsportManager.Sanction_Service.Exceptions.SancionNoEncontradaException;
import com.EsportManager.Sanction_Service.Models.Sancion;
import com.EsportManager.Sanction_Service.Models.Dtos.SancionDTO;
import com.EsportManager.Sanction_Service.Repositories.SancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SancionServiceLmpl implements SancionService {

    @Autowired
    private SancionRepository repository;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private EquipoClient equipoClient;

    @Override
    public List<Sancion> findAll() {
        return repository.findAll();
    }

    @Override
    public Sancion findById(Long id) {
        Optional<Sancion> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new SancionNoEncontradaException("La sancion con id " + id + " no existe");
    }

    @Override
    public Sancion save(SancionDTO dto) {
        // Validación lógica: Al menos usuarioId o equipoId deben estar presentes
        if (dto.getUsuarioId() == null && dto.getEquipoId() == null) {
            throw new RuntimeException("Debe proporcionar al menos un ID de usuario o un ID de equipo para la sanción");
        }

        // Validar usuario y equipo via Feign
        try {
            if (dto.getUsuarioId() != null) {
                usuarioClient.obtenerUsuarioPorId(dto.getUsuarioId());
            }
            if (dto.getEquipoId() != null) {
                equipoClient.obtenerEquipoPorId(dto.getEquipoId());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error de validación externa en Sanction-service: " + e.getMessage());
        }

        Sancion s = new Sancion();
        s.setUsuarioId(dto.getUsuarioId());
        s.setEquipoId(dto.getEquipoId());
        s.setMotivo(dto.getMotivo());
        s.setFechaInicio(dto.getFechaInicio());
        s.setFechaFin(dto.getFechaFin());
        s.setEstado(dto.getEstado());
        s.setSeverida(dto.getSeverida());
        return repository.save(s);
    }

    @Override
    public Sancion updateById(SancionDTO dto, Long id) {
        Optional<Sancion> optional = repository.findById(id);
        if (optional.isPresent()) {
            Sancion s = optional.get();
            s.setUsuarioId(dto.getUsuarioId());
            s.setEquipoId(dto.getEquipoId());
            s.setMotivo(dto.getMotivo());
            s.setFechaInicio(dto.getFechaInicio());
            s.setFechaFin(dto.getFechaFin());
            s.setEstado(dto.getEstado());
            s.setSeverida(dto.getSeverida());
            return repository.save(s);
        }
        throw new SancionNoEncontradaException("La sancion con id " + id + " no existe");
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Sancion> findByUsuarioIdAndEstado(Long usuarioId, String estado) {
        return repository.findByUsuarioIdAndEstado(usuarioId, estado);
    }

    @Override
    public List<Sancion> findByEquipoIdAndEstado(Long equipoId, String estado) {
        return repository.findByEquipoIdAndEstado(equipoId, estado);
    }

    @Override
    public List<Sancion> findByUsuarioId(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Sancion> findByEquipoId(Long equipoId) {
        return repository.findByEquipoId(equipoId);
    }

    @Override
    public List<Sancion> findByEstado(String estado) {
        return repository.findByEstado(estado);
    }

    @Override
    public boolean tieneSancionActiva(Long usuarioId, Long equipoId) {
        return repository.existsByUsuarioIdAndEquipoIdAndEstado(usuarioId, equipoId, "ACTIVA");
    }

    @Override
    public Sancion cerrarSancion(Long id) {
        Optional<Sancion> optional = repository.findById(id);
        if (optional.isPresent()) {
            Sancion sancion = optional.get();
            sancion.setEstado("CERRADA");
            return repository.save(sancion);
        }
        throw new SancionNoEncontradaException("La sancion con id " + id + " no existe");
    }
}
