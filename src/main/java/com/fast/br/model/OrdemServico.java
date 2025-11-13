package com.fast.br.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ordens_servico")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_os")
    private Long idOs;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_tecnico", nullable = false)
    private Tecnico tecnico;

    @Column(name = "pedido", length = 50)
    private String pedido;

    @Column(name = "data_faturamento")
    private LocalDate dataFaturamento;

    @Column(name = "garantia")
    private Boolean garantia = false;

    @Column(name = "empresa", length = 100)
    private String empresa;

    @Column(name = "cidade_empresa", length = 50)
    private String cidadeEmpresa;

    @Column(name = "uf_empresa", length = 2)
    private String ufEmpresa;

    @Column(name = "data_abertura", nullable = false)
    private LocalDate dataAbertura;

    @Column(name = "descricao_chamado", columnDefinition = "TEXT")
    private String descricaoChamado;

    @Column(name = "observacoes_cliente", columnDefinition = "TEXT")
    private String observacoesCliente;

    @Column(name = "data_primeira_visita")
    private LocalDate dataPrimeiraVisita;

    @Column(name = "data_segunda_visita")
    private LocalDate dataSegundaVisita;

    @Column(name = "servico_finalizado")
    private Boolean servicoFinalizado = false;

    @Column(name = "pendencia", columnDefinition = "TEXT")
    private String pendencia;

    @Column(name = "observacoes_tecnico", columnDefinition = "TEXT")
    private String observacoesTecnico;

    // Relacionamentos inversos
    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Defeito> defeitos;

    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaterialUtilizado> materiais;
}
