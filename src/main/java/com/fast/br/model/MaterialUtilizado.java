package com.fast.br.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "materiais_utilizados")
public class MaterialUtilizado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_material")
    private Long idMaterial;

    @ManyToOne
    @JoinColumn(name = "id_os", nullable = false)
    private OrdemServico ordemServico;

    @Column(name = "nome_material", nullable = false, length = 100)
    private String nomeMaterial;

    @Column(name = "quantidade", precision = 10, scale = 2)
    private BigDecimal quantidade;

    @Column(name = "valor_unitario", precision = 10, scale = 2)
    private BigDecimal valorUnitario;

    @Column(name = "valor_total", precision = 10, scale = 2)
    private BigDecimal valorTotal;
}
