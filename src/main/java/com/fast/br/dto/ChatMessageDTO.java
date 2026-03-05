package com.fast.br.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDTO {
    
    private Long idMensagem;
    private Long idOs;
    private String remetente;
    private String mensagem;
    private LocalDateTime dataEnvio;
    private Boolean lida;
}
