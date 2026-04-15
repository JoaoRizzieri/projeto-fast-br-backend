package com.fast.br.dto;

public class DefeitoDTO {
    private Long idDefeito;
    private Long idOs;
    private String categoria;
    private String descricao;

    public Long getIdDefeito() { return idDefeito; }
    public void setIdDefeito(Long idDefeito) { this.idDefeito = idDefeito; }

    public Long getIdOs() { return idOs; }
    public void setIdOs(Long idOs) { this.idOs = idOs; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}