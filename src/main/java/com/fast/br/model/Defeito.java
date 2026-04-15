package com.fast.br.model;

import jakarta.persistence.*;

@Entity
@Table(name = "defeitos")
public class Defeito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_defeito")
    private Long idDefeito;

    @ManyToOne
    @JoinColumn(name = "id_os", nullable = false)
    private OrdemServico ordemServico;

    @Column(name = "categoria", length = 50)
    private String categoria;

    @Column(name = "descricao", length = 100)
    private String descricao;

    public Defeito() {}

    public Long getIdDefeito() { return idDefeito; }
    public void setIdDefeito(Long idDefeito) { this.idDefeito = idDefeito; }

    public OrdemServico getOrdemServico() { return ordemServico; }
    public void setOrdemServico(OrdemServico ordemServico) { this.ordemServico = ordemServico; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
