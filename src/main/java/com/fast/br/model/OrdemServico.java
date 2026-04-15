package com.fast.br.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
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

    @Column(name = "status", length = 20)
    private String status = "pendente"; // pendente, em_andamento, aguardando_peca, finalizada, cancelada

    @Column(name = "pendencia", columnDefinition = "TEXT")
    private String pendencia;

    @Column(name = "observacoes_tecnico", columnDefinition = "TEXT")
    private String observacoesTecnico;

    @Column(name = "assinatura_base64", columnDefinition = "TEXT")
    private String signatureBase64;

    // Relacionamentos inversos
    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Defeito> defeitos;

    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaterialUtilizado> materiais;

    // Getters e Setters Manuais
    public Long getIdOs() { return idOs; }
    public void setIdOs(Long idOs) { this.idOs = idOs; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Tecnico getTecnico() { return tecnico; }
    public void setTecnico(Tecnico tecnico) { this.tecnico = tecnico; }

    public String getPedido() { return pedido; }
    public void setPedido(String pedido) { this.pedido = pedido; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }

    public LocalDate getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(LocalDate dataAbertura) { this.dataAbertura = dataAbertura; }

    public String getDescricaoChamado() { return descricaoChamado; }
    public void setDescricaoChamado(String descricaoChamado) { this.descricaoChamado = descricaoChamado; }

    public LocalDate getDataFaturamento() { return dataFaturamento; }
    public void setDataFaturamento(LocalDate dataFaturamento) { this.dataFaturamento = dataFaturamento; }

    public Boolean getGarantia() { return garantia; }
    public void setGarantia(Boolean garantia) { this.garantia = garantia; }

    public String getCidadeEmpresa() { return cidadeEmpresa; }
    public void setCidadeEmpresa(String cidadeEmpresa) { this.cidadeEmpresa = cidadeEmpresa; }

    public String getUfEmpresa() { return ufEmpresa; }
    public void setUfEmpresa(String ufEmpresa) { this.ufEmpresa = ufEmpresa; }

    public String getObservacoesCliente() { return observacoesCliente; }
    public void setObservacoesCliente(String observacoesCliente) { this.observacoesCliente = observacoesCliente; }

    public LocalDate getDataPrimeiraVisita() { return dataPrimeiraVisita; }
    public void setDataPrimeiraVisita(LocalDate dataPrimeiraVisita) { this.dataPrimeiraVisita = dataPrimeiraVisita; }

    public LocalDate getDataSegundaVisita() { return dataSegundaVisita; }
    public void setDataSegundaVisita(LocalDate dataSegundaVisita) { this.dataSegundaVisita = dataSegundaVisita; }

    public Boolean getServicoFinalizado() { return servicoFinalizado; }
    public void setServicoFinalizado(Boolean servicoFinalizado) { this.servicoFinalizado = servicoFinalizado; }

    public String getPendencia() { return pendencia; }
    public void setPendencia(String pendencia) { this.pendencia = pendencia; }

    public String getObservacoesTecnico() { return observacoesTecnico; }
    public void setObservacoesTecnico(String observacoesTecnico) { this.observacoesTecnico = observacoesTecnico; }

    public String getSignatureBase64() { return signatureBase64; }
    public void setSignatureBase64(String signatureBase64) { this.signatureBase64 = signatureBase64; }

    public List<Defeito> getDefeitos() { return defeitos; }
    public void setDefeitos(List<Defeito> defeitos) { this.defeitos = defeitos; }

    public List<MaterialUtilizado> getMateriais() { return materiais; }
    public void setMateriais(List<MaterialUtilizado> materiais) { this.materiais = materiais; }
}
