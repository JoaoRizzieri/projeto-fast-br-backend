package com.fast.br.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "nome_cliente", nullable = false, length = 100)
    private String nomeCliente;

    @Column(name = "contato", length = 100)
    private String contato;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "endereco", length = 150)
    private String endereco;

    @Column(name = "numero", length = 10)
    private String numero;

    @Column(name = "bairro", length = 50)
    private String bairro;

    @Column(name = "cidade", length = 50)
    private String cidade;

    @Column(name = "uf", length = 2)
    private String uf;

    @Column(name = "codigo_cliente", length = 20)
    private String codigoCliente;
}
