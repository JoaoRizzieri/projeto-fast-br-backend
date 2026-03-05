package com.fast.br.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageRequestDTO {
    private String mensagem;
    private String remetente;
}
