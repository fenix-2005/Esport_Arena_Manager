package com.EsportManager.Prize_Service.Repositories;

import com.EsportManager.Prize_Service.Models.Premio;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PremioRepository extends JpaRepository<Premio, Long> {

    // Lista premios por torneo"
    List<Premio> findByTorneoId(Long torneoId);

    // Lista premios por posición
    List<Premio> findByPosicion(Integer posicion);

    // Busca premio por la id del torneo y su pocision
    Optional<Premio> findByTorneoIdAndPosicion(Long torneoId, Integer posicion);


}
