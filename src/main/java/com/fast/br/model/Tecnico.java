package com.fast.br.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tecnicos")
public class Tecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tecnico")
    private Long idTecnico;

    @Column(name = "nome_tecnico", nullable = false, length = 100)
    private String nomeTecnico;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "senha", length = 100)
    private String senha;

    @Column(name = "nome_ajudante", length = 100)
    private String nomeAjudante;

    @Column(name = "telefone_ajudante", length = 20)
    private String telefoneAjudante;

    @Column(name = "valor_hora_trabalhada")
    private Double valorHoraTrabalhada = 50.0;

    @Column(name = "valor_deslocamento")
    private Double valorDeslocamento = 30.0;

    @Column(name = "valor_por_km")
    private Double valorPorKm = 0.75;

    @Column(name = "valor_hora_extra")
    private Double valorHoraExtra = 75.0;

    // GETTERS E SETTERS MANUAIS
    public Long getIdTecnico() { return idTecnico; }
    public void setIdTecnico(Long idTecnico) { this.idTecnico = idTecnico; }

    public String getNomeTecnico() { return nomeTecnico; }
    public void setNomeTecnico(String nomeTecnico) { this.nomeTecnico = nomeTecnico; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

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
