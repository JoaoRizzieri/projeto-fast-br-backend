package com.fast.br.repository;

import com.fast.br.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByOrdemServicoIdOsOrderByDataEnvioAsc(Long idOs);
}
