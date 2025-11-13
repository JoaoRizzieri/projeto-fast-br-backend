package com.fast.br.repository;

import com.fast.br.model.OrdemServico;
import com.fast.br.model.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TecnicoRepository extends JpaRepository<Tecnico,Long> {
    Optional<Tecnico> findByEmail(String email);

}
