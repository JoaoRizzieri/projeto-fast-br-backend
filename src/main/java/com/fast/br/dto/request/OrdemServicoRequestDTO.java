package com.fast.br.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class OrdemServicoRequestDTO {
    @NotNull(message = "Cliente é obrigatório")
    private Long idCliente;
    
    @NotNull(message = "Técnico é obrigatório")
    private Long idTecnico;
    
    private String pedido;
    private LocalDate dataFaturamento;
    private Boolean garantia;
    private String empresa;
    private String cidadeEmpresa;
    private String ufEmpresa;
    
    @NotNull(message = "Data de abertura é obrigatória")
    private LocalDate dataAbertura;
    
    private String descricaoChamado;
    private String observacoesCliente;
    private LocalDate dataPrimeiraVisita;
    private LocalDate dataSegundaVisita;
    private Boolean servicoFinalizado;
    private String pendencia;
    private String observacoesTecnico;
    private String status;
    private List<DefeitoRequestDTO> defeitos;
    private List<MaterialUtilizadoRequestDTO> materiais;

    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }

    public Long getIdTecnico() { return idTecnico; }
    public void setIdTecnico(Long idTecnico) { this.idTecnico = idTecnico; }

    public String getPedido() { return pedido; }
    public void setPedido(String pedido) { this.pedido = pedido; }

    public LocalDate getDataFaturamento() { return dataFaturamento; }
    public void setDataFaturamento(LocalDate dataFaturamento) { this.dataFaturamento = dataFaturamento; }

    public Boolean getGarantia() { return garantia; }
    public void setGarantia(Boolean garantia) { this.garantia = garantia; }

    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }

    public String getCidadeEmpresa() { return cidadeEmpresa; }
    public void setCidadeEmpresa(String cidadeEmpresa) { this.cidadeEmpresa = cidadeEmpresa; }

    public String getUfEmpresa() { return ufEmpresa; }
    public void setUfEmpresa(String ufEmpresa) { this.ufEmpresa = ufEmpresa; }

    public LocalDate getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(LocalDate dataAbertura) { this.dataAbertura = dataAbertura; }

    public String getDescricaoChamado() { return descricaoChamado; }
    public void setDescricaoChamado(String descricaoChamado) { this.descricaoChamado = descricaoChamado; }

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

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<DefeitoRequestDTO> getDefeitos() { return defeitos; }
    public void setDefeitos(List<DefeitoRequestDTO> defeitos) { this.defeitos = defeitos; }

    public List<MaterialUtilizadoRequestDTO> getMateriais() { return materiais; }
    public void setMateriais(List<MaterialUtilizadoRequestDTO> materiais) { this.materiais = materiais; }
}