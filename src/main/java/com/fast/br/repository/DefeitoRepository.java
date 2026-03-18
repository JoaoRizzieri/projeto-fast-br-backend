package com.fast.br.repository;

import com.fast.br.model.Defeito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DefeitoRepository extends JpaRepository<Defeito, Long> {
    List<Defeito> findByOrdemServicoIdOs(Long idOs);
}

