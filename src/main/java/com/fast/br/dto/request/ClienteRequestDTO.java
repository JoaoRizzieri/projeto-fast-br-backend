package com.fast.br.dto.request;

import lombok.Data;

@Data
public class ClienteRequestDTO {
    private String nomeCliente;
    private String contato;
    private String telefone;
    private String endereco;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String codigoCliente;
}
