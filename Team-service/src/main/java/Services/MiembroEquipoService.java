package Services;

import Exceptions.JugadorYaEnEquipoException;
import Exceptions.MiembroNoEncontradoException;
import Models.MiembroEquipo;
import Repositories.MiembroEquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiembroEquipoService {

    // Gestiona la plantilla de los equipos evitando que un mismo jugador se una dos veces al mismo equipo.

    @Autowired
    private MiembroEquipoRepository repository;

    public MiembroEquipo agregarMiembro(MiembroEquipo miembro) {
        if (repository.findByEquipoIdAndUsuarioId(miembro.getEquipoId(), miembro.getUsuarioId()).isPresent()) {
            throw new JugadorYaEnEquipoException("El jugador ya ocupa un cupo en este equipo.");
        }
        return repository.save(miembro);
    }

    public void eliminarMiembro(Long id) {
        MiembroEquipo miembro = repository.findById(id)
                .orElseThrow(() -> new MiembroNoEncontradoException("El registro del miembro no existe en este equipo."));

        repository.delete(miembro);
    }
}
