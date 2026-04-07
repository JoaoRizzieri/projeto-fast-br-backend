package com.fast.br.repository;

import com.fast.br.model.OrdemServico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
    List<OrdemServico> findByTecnicoIdTecnico(Long idTecnico);

    Page<OrdemServico> findByTecnicoIdTecnico(Long idTecnico, Pageable pageable);

    List<OrdemServico> findByTecnicoIdTecnicoAndServicoFinalizado(Long idTecnico, Boolean servicoFinalizado);

    Page<OrdemServico> findByTecnicoIdTecnicoAndServicoFinalizado(Long idTecnico, Boolean servicoFinalizado, Pageable pageable);

    List<OrdemServico> findByTecnicoIdTecnicoAndServicoFinalizadoAndDataPrimeiraVisitaIsNull(Long idTecnico, Boolean servicoFinalizado);

    Page<OrdemServico> findByTecnicoIdTecnicoAndServicoFinalizadoAndDataPrimeiraVisitaIsNull(Long idTecnico, Boolean servicoFinalizado, Pageable pageable);

    List<OrdemServico> findByTecnicoIdTecnicoAndServicoFinalizadoAndDataPrimeiraVisitaIsNotNull(Long idTecnico, Boolean servicoFinalizado);

    Page<OrdemServico> findByTecnicoIdTecnicoAndServicoFinalizadoAndDataPrimeiraVisitaIsNotNull(Long idTecnico, Boolean servicoFinalizado, Pageable pageable);

}

