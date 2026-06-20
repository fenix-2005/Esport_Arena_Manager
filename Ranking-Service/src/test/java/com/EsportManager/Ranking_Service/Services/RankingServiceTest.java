package com.EsportManager.Ranking_Service.Services;

import com.EsportManager.Ranking_Service.Exceptions.RankingNoEncontradoException;
import com.EsportManager.Ranking_Service.Models.Dtos.RankingDTO;
import com.EsportManager.Ranking_Service.Models.Ranking;
import com.EsportManager.Ranking_Service.Repositories.RankingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RankingServiceTest {

    @Mock
    private RankingRepository repository;

    @InjectMocks
    private RankingServiceLmpl service;

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Ranking()));
        List<Ranking> resultado = service.findAll();
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByIdExitoso() {
        Ranking ranking = new Ranking();
        when(repository.findById(1L)).thenReturn(Optional.of(ranking));

        Ranking resultado = service.findById(1L);
        assertNotNull(resultado);
    }

    @Test
    void findByIdLanzaExcepcion() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RankingNoEncontradoException.class, () -> {
            service.findById(99L);
        });
    }

    @Test
    void save() {
        RankingDTO dto = new RankingDTO();
        Ranking ranking = new Ranking();

        when(repository.save(any(Ranking.class))).thenReturn(ranking);

        Ranking resultado = service.save(dto);
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(Ranking.class));
    }

    @Test
    void updateByIdExitoso() {
        RankingDTO dto = new RankingDTO();
        Ranking ranking = new Ranking();

        when(repository.findById(1L)).thenReturn(Optional.of(ranking));
        when(repository.save(any(Ranking.class))).thenReturn(ranking);

        Ranking resultado = service.updateById(dto, 1L);
        assertNotNull(resultado);
    }

    @Test
    void updateByIdLanzaExcepcion() {
        RankingDTO dto = new RankingDTO();
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RankingNoEncontradoException.class, () -> {
            service.updateById(dto, 99L);
        });
    }

    @Test
    void deleteById() {
        doNothing().when(repository).deleteById(1L);
        service.deleteById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void findByTorneoId() {
        when(repository.findByTorneoId(1L)).thenReturn(Arrays.asList(new Ranking()));
        List<Ranking> resultado = service.findByTorneoId(1L);
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByTorneoIdAndParticipanteId() {
        Ranking ranking = new Ranking();
        when(repository.findByTorneoIdAndParticipanteId(1L, 2L)).thenReturn(Optional.of(ranking));

        Optional<Ranking> resultado = service.findByTorneoIdAndParticipanteId(1L, 2L);
        assertTrue(resultado.isPresent());
    }
}
