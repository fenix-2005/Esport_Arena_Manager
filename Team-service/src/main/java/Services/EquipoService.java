package Services;

import Exceptions.EquipoSinCapitanException;
import Models.Equipo;
import Models.MiembroEquipo;
import Repositories.EquipoRepository;
import Repositories.MiembroEquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipoService {

    // Exige un capitán para crear equipos y asegura que los jugadores no ocupen dos cupos.

    @Autowired private EquipoRepository equipoRepository;
    @Autowired private MiembroEquipoRepository miembroRepository;

    public Equipo crearEquipo(Equipo equipo) {
        if (equipo.getCapitanId() == null) {
            throw new EquipoSinCapitanException("Para registrar un equipo, debes asignar un capitán.");
        }
        equipo.setEstado("ACTIVO");
        return equipoRepository.save(equipo);
    }

}
