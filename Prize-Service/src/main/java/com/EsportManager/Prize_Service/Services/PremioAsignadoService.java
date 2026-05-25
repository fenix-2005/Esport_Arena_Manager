package com.EsportManager.Prize_Service.Services;

import com.EsportManager.Prize_Service.Exceptions.PremioYaAsignadoException;
import com.EsportManager.Prize_Service.Models.PremioAsignado;
import com.EsportManager.Prize_Service.Repositories.PremioAsignadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PremioAsignadoService {

    // Asigna un trofeo o premio a un ganador y evita que un mismo premio se entregue dos veces.

    @Autowired
    private PremioAsignadoRepository repository;

    public PremioAsignado asignarPremio(PremioAsignado asignacion) {
        List<PremioAsignado> historial = repository.findByPremioId(asignacion.getPremioId());

        if (!historial.isEmpty()) {
            throw new PremioYaAsignadoException("Este premio ya fue asignado a otro participante y no puede duplicarse.");
        }

        return repository.save(asignacion);
    }
}
