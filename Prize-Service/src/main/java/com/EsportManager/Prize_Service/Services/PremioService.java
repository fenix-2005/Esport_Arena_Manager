package com.EsportManager.Prize_Service.Services;

import com.EsportManager.Prize_Service.Exceptions.PremioDuplicadoException;
import com.EsportManager.Prize_Service.Exceptions.PremioNoEncontradoException;
import com.EsportManager.Prize_Service.Exceptions.PremioYaAsignadoException;
import com.EsportManager.Prize_Service.Models.Premio;
import com.EsportManager.Prize_Service.Repositories.PremioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PremioService {

    // Crea premios únicos por posición y protege de eliminación a los ya entregados.

    @Autowired
    private PremioRepository repository;

    public Premio crearPremio(Premio premio) {
        if (repository.findByTorneoIdAndPosicion(premio.getTorneoId(), premio.getPosicion()).isPresent()) {
            throw new PremioDuplicadoException("La posición " + premio.getPosicion() + " ya tiene un premio.");
        }
        premio.setEstado("NO_ASIGNADO");
        return repository.save(premio);
    }

    public void eliminarPremio(Long id) {
        Premio premio = repository.findById(id)
                .orElseThrow(() -> new PremioNoEncontradoException("El premio no existe."));

        if ("ASIGNADO".equals(premio.getEstado())) {
            throw new PremioYaAsignadoException("No puedes eliminar un premio ya entregado.");
        }
        repository.delete(premio);
    }
}
