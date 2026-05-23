package com.EsportManager.Prize_Service.Repositories;

import com.EsportManager.Prize_Service.Models.PremioAsignado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PremioAsignadoRepository extends JpaRepository<PremioAsignado, Long> {

    List<PremioAsignado> findByParticipanteId(Long participanteId);


    List<PremioAsignado> findByPremioId(Long premioId);
}
