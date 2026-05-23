package Repositories;

import Models.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    List<Equipo> findByJuegoPrincipalId(Long juegoPrincipalId);

    List<Equipo> findByCapitanId(Long capitanId);

    List<Equipo> findByEstado(String estado);

}

