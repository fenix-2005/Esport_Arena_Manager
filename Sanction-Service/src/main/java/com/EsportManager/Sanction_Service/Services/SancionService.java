package com.EsportManager.Sanction_Service.Services;

import com.EsportManager.Sanction_Service.Exceptions.FechasSancionInvalidasException;
import com.EsportManager.Sanction_Service.Exceptions.SancionNoEncontradaException;
import com.EsportManager.Sanction_Service.Models.Sancion;
import com.EsportManager.Sanction_Service.Repositories.SancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SancionService {

    // Aplica castigos verificando las fechas y permite cerrar sanciones cumplidas.

    @Autowired
    private SancionRepository repository;

    public Sancion crearSancion(Sancion sancion) {
        if (sancion.getFechaFin().before(sancion.getFechaInicio())) {
            throw new FechasSancionInvalidasException("La fecha de término no puede ser anterior al inicio.");
        }
        sancion.setEstado("ACTIVA");
        return repository.save(sancion);
    }

    public Sancion cerrarSancion(Long id) {
        Sancion sancion = repository.findById(id)
                .orElseThrow(() -> new SancionNoEncontradaException("Sanción no encontrada."));

        sancion.setEstado("CUMPLIDA");
        return repository.save(sancion);
    }
}
