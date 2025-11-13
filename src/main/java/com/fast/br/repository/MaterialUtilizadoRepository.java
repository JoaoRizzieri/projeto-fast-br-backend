package com.fast.br.repository;

import com.fast.br.model.MaterialUtilizado;
import com.fast.br.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialUtilizadoRepository extends JpaRepository<MaterialUtilizado, Long> {
    List<MaterialUtilizado> findByOrdemServicoIdOs(Long idOs);
}
