package com.fast.br.dto;

import lombok.Data;

@Data
public class TecnicoDTO {
    private Long idTecnico;
    private String nomeTecnico;
    private String telefone;
    private String email;
    private String nomeAjudante;
    private String telefoneAjudante;
    
    // Configurações de custos
    private Double valorHoraTrabalhada;
    private Double valorDeslocamento;
    private Double valorPorKm;
    private Double valorHoraExtra;
}

