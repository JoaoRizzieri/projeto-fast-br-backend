package com.fast.br.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "nome_ajudante", length = 100)
    private String nomeAjudante;

    @Column(name = "telefone_ajudante", length = 20)
    private String telefoneAjudante;

    @Column(name = "senha", length = 100)
    private String senha;

    @Column(name = "valor_hora_trabalhada")
    private Double valorHoraTrabalhada = 50.0;

    @Column(name = "valor_deslocamento")
    private Double valorDeslocamento = 30.0;

    @Column(name = "valor_por_km")
    private Double valorPorKm = 0.75;

    @Column(name = "valor_hora_extra")
    private Double valorHoraExtra = 75.0;
}
