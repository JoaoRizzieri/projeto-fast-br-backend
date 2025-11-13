package com.fast.br.dto.request;

import lombok.Data;

@Data
public class TecnicoRequestDTO {
    private String nomeTecnico;
    private String telefone;
    private String email;
    private String nomeAjudante;
    private String telefoneAjudante;
    private String senha;
}