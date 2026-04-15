package com.fast.br.dto.request;

public class TecnicoRequestDTO {
    private String nomeTecnico;
    private String telefone;
    private String email;
    private String nomeAjudante;
    private String telefoneAjudante;
    private String senha;

    public String getNomeTecnico() { return nomeTecnico; }
    public void setNomeTecnico(String nomeTecnico) { this.nomeTecnico = nomeTecnico; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNomeAjudante() { return nomeAjudante; }
    public void setNomeAjudante(String nomeAjudante) { this.nomeAjudante = nomeAjudante; }

    public String getTelefoneAjudante() { return telefoneAjudante; }
    public void setTelefoneAjudante(String telefoneAjudante) { this.telefoneAjudante = telefoneAjudante; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}