package com.EsportManager.Registration_Service.Repositories;

import com.EsportManager.Registration_Service.Models.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    List<Inscripcion> findByTorneoId(Long torneoId);

    List<Inscripcion> findByEquipoId(Long equipoId);

    List<Inscripcion> findByJugadorId(Long jugadorId);

    Optional<Inscripcion> findByIdAndTorneoId(Long id, Long torneoId);

    Optional<Inscripcion> findByJugadorIdAndTorneoId(Long jugadorId, Long torneoId);

    Optional<Inscripcion> findByEquipoIdAndTorneoId(Long equipoId, Long torneoId);

    }
