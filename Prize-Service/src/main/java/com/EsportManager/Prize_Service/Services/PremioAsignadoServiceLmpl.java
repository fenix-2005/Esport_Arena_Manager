package com.EsportManager.Prize_Service.Services;

import com.EsportManager.Prize_Service.Models.Dtos.PremioAsignadoDTO;
import com.EsportManager.Prize_Service.Models.PremioAsignado;
import com.EsportManager.Prize_Service.Repositories.PremioAsignadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PremioAsignadoServiceLmpl implements PremioAsignadoService {

    @Autowired
    private PremioAsignadoRepository repository;

    @Override
    public List<PremioAsignado> findAll() {
        return repository.findAll();
    }

    @Override
    public PremioAsignado findById(Long id) {
        Optional<PremioAsignado> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public PremioAsignado save(PremioAsignadoDTO dto) {
        PremioAsignado p = new PremioAsignado();
        p.setPremioId(dto.getPremioId());
        p.setParticipanteId(dto.getParticipanteId());
        p.setFechaAsignacion(dto.getFechaAsignacion());
        return repository.save(p);
    }

    @Override
    public PremioAsignado updateById(PremioAsignadoDTO dto, Long id) {
        Optional<PremioAsignado> optional = repository.findById(id);
        if (optional.isPresent()) {
            PremioAsignado p = optional.get();
            p.setPremioId(dto.getPremioId());
            p.setParticipanteId(dto.getParticipanteId());
            p.setFechaAsignacion(dto.getFechaAsignacion());
            return repository.save(p);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<PremioAsignado> findByParticipanteId(Long participanteId) {
        return repository.findByParticipanteId(participanteId);
    }
}
