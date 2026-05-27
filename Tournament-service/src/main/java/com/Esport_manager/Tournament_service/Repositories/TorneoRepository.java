package com.Esport_manager.Tournament_service.Repositories;

import com.Esport_manager.Tournament_service.Models.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;
@Repository

public interface TorneoRepository extends JpaRepository<Torneo, Long> {

    List<Torneo> findByJuegoId(Long juegoId);

    List<Torneo> findByEstado(String estado);

    List<Torneo> findByFechaInicio(LocalDate fechaInicio);
}
