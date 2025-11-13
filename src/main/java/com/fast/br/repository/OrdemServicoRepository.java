package com.fast.br.repository;

import com.fast.br.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
    List<OrdemServico> findByTecnicoIdTecnico(Long idTecnico);

}

