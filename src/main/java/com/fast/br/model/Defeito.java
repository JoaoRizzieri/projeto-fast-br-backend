package com.fast.br.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
