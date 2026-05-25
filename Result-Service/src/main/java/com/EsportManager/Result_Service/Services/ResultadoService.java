package com.EsportManager.Result_Service.Services;

import com.EsportManager.Result_Service.Exception.PuntajeInvalidoException;
import com.EsportManager.Result_Service.Exception.ResultadoNoEncontradoException;
import com.EsportManager.Result_Service.Exception.ResultadoYaValidadoException;
import com.EsportManager.Result_Service.Models.Resultados;
import com.EsportManager.Result_Service.Repositories.ResultadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultadoService {

    // Registra marcadores finales rechazando puntajes negativos y bloquea modificaciones no autorizadas.

    @Autowired
    private ResultadosRepository repository;

    public Resultados crearResultados(Resultados resultados) {
        if (resultados.getPuntajeA() < 0 || resultados.getPuntajeB() < 0) {
            throw new PuntajeInvalidoException("Los puntajes no pueden ser menores a 0.");
        }
        resultados.setEstadoValidacion("PENDIENTE");
        return repository.save(resultados);
    }

    public Resultados modificarResultados(Long id, int nuevoPuntajeA, int nuevoPuntajeB, String rolUsuario) {
        Resultados resultado = repository.findById(id)
                .orElseThrow(() -> new ResultadoNoEncontradoException("No se encontró el resultado."));

        if ("VALIDADO".equals(resultado.getEstadoValidacion()) && !"ORGANIZADOR".equals(rolUsuario)) {
            throw new ResultadoYaValidadoException("Solo los organizadores pueden modificar resultados validados.");
        }
        resultado.setPuntajeA(nuevoPuntajeA);
        resultado.setPuntajeB(nuevoPuntajeB);
        return repository.save(resultado);
    }
}
