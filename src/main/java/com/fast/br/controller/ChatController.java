package com.fast.br.controller;

import com.fast.br.dto.ChatMessageDTO;
import com.fast.br.dto.request.ChatMessageRequestDTO;
import com.fast.br.model.ChatMessage;
import com.fast.br.model.OrdemServico;
import com.fast.br.repository.ChatMessageRepository;
import com.fast.br.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @GetMapping("/{idOs}/mensagens")
    public ResponseEntity<List<ChatMessageDTO>> getMensagens(@PathVariable Long idOs) {
        List<ChatMessage> mensagens = chatMessageRepository.findByOrdemServicoIdOsOrderByDataEnvioAsc(idOs);
        
        List<ChatMessageDTO> dtos = mensagens.stream().map(msg -> {
            ChatMessageDTO dto = new ChatMessageDTO();
            dto.setIdMensagem(msg.getIdMensagem());
            dto.setIdOs(msg.getOrdemServico().getIdOs());
            dto.setRemetente(msg.getRemetente());
            dto.setMensagem(msg.getMensagem());
            dto.setDataEnvio(msg.getDataEnvio());
            dto.setLida(msg.getLida());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/{idOs}/mensagens")
    public ResponseEntity<ChatMessageDTO> enviarMensagem(
            @PathVariable Long idOs, 
            @RequestBody ChatMessageRequestDTO request) {
        
        OrdemServico os = ordemServicoRepository.findById(idOs)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada"));

        ChatMessage mensagem = new ChatMessage();
        mensagem.setOrdemServico(os);
        mensagem.setRemetente(request.getRemetente());
        mensagem.setMensagem(request.getMensagem());
        mensagem.setDataEnvio(LocalDateTime.now());
        mensagem.setLida(false);

        ChatMessage salva = chatMessageRepository.save(mensagem);

        ChatMessageDTO dto = new ChatMessageDTO();
        dto.setIdMensagem(salva.getIdMensagem());
        dto.setIdOs(salva.getOrdemServico().getIdOs());
        dto.setRemetente(salva.getRemetente());
        dto.setMensagem(salva.getMensagem());
        dto.setDataEnvio(salva.getDataEnvio());
        dto.setLida(salva.getLida());

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/mensagens/{id}")
    public ResponseEntity<Void> excluirMensagem(@PathVariable Long id) {
        chatMessageRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/mensagens/{id}/lida")
    public ResponseEntity<ChatMessageDTO> marcarComoLida(@PathVariable Long id) {
        ChatMessage mensagem = chatMessageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mensagem não encontrada"));

        mensagem.setLida(true);
        ChatMessage salva = chatMessageRepository.save(mensagem);

        ChatMessageDTO dto = new ChatMessageDTO();
        dto.setIdMensagem(salva.getIdMensagem());
        dto.setIdOs(salva.getOrdemServico().getIdOs());
        dto.setRemetente(salva.getRemetente());
        dto.setMensagem(salva.getMensagem());
        dto.setDataEnvio(salva.getDataEnvio());
        dto.setLida(salva.getLida());

        return ResponseEntity.ok(dto);
    }
}
