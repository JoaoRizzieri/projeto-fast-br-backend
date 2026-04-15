package com.fast.br.dto;

public class TecnicoDTO {
    private Long idTecnico;
    private String nomeTecnico;
    private String telefone;
    private String email;
    private String nomeAjudante;
    private String telefoneAjudante;
    private Double valorHoraTrabalhada;
    private Double valorDeslocamento;
    private Double valorPorKm;
    private Double valorHoraExtra;

    // GETTERS E SETTERS
    public Long getIdTecnico() { return idTecnico; }
    public void setIdTecnico(Long idTecnico) { this.idTecnico = idTecnico; }

    public String getNomeTecnico() { return nomeTecnico; }
    public void setNomeTecnico(String nomeTecnico) { this.nomeTecnico = nomeTecnico; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getNomeAjudante() { return nomeAjudante; }
    public void setNomeAjudante(String nomeAjudante) { this.nomeAjudante = nomeAjudante; }

    public String getTelefoneAjudante() { return telefoneAjudante; }
    public void setTelefoneAjudante(String telefoneAjudante) { this.telefoneAjudante = telefoneAjudante; }

    public Double getValorHoraTrabalhada() { return valorHoraTrabalhada; }
    public void setValorHoraTrabalhada(Double valorHoraTrabalhada) { this.valorHoraTrabalhada = valorHoraTrabalhada; }

    public Double getValorDeslocamento() { return valorDeslocamento; }
    public void setValorDeslocamento(Double valorDeslocamento) { this.valorDeslocamento = valorDeslocamento; }

    public Double getValorPorKm() { return valorPorKm; }
    public void setValorPorKm(Double valorPorKm) { this.valorPorKm = valorPorKm; }

    public Double getValorHoraExtra() { return valorHoraExtra; }
    public void setValorHoraExtra(Double valorHoraExtra) { this.valorHoraExtra = valorHoraExtra; }
}
