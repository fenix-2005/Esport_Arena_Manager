package Services;

import Exceptions.PartidaCanceladaException;
import Exceptions.PartidaNoEncontradaException;
import Models.Partida;
import Repositories.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidaService {

    // Controla el inicio de los enfrentamientos y bloquea las partidas que fueron canceladas.

    @Autowired
    private PartidaRepository repository;

    public Partida iniciarPartida(Long id) {
        Partida partida = repository.findById(id)
                .orElseThrow(() -> new PartidaNoEncontradaException("La partida no existe."));

        if ("CANCELADA".equals(partida.getEstado())) {
            throw new PartidaCanceladaException("No se puede iniciar una partida cancelada.");
        }
        partida.setEstado("EN CURSO");
        return repository.save(partida);
    }
}
