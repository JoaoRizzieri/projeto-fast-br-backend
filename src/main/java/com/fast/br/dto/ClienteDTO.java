package com.fast.br.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private Long idCliente;
    private String nomeCliente;
    private String telefone;
    private String cidade;
    private String uf;
}
