package com.fast.br.dto;

import java.time.LocalDate;
import java.util.List;

public class OrdemServicoDTO {
    private Long idOs;
    private ClienteDTO cliente;
    private TecnicoDTO tecnico;
    private String pedido;
    private String status;
    private String empresa;
    private String cidadeEmpresa;
    private String ufEmpresa;
    private LocalDate dataAbertura;
    private LocalDate dataFaturamento;
    private Boolean garantia;
    private String descricaoChamado;
    private String observacoesCliente;
    private String observacoesTecnico;
    private String pendencia;
    private Boolean servicoFinalizado;
    private String assinaturaBase64;
    private List<DefeitoDTO> defeitos;
    private List<MaterialUtilizadoDTO> materiais;

    // Getters e Setters Manuais
    public Long getIdOs() { return idOs; }
    public void setIdOs(Long idOs) { this.idOs = idOs; }

    public ClienteDTO getCliente() { return cliente; }
    public void setCliente(ClienteDTO cliente) { this.cliente = cliente; }

    public TecnicoDTO getTecnico() { return tecnico; }
    public void setTecnico(TecnicoDTO tecnico) { this.tecnico = tecnico; }

    public String getPedido() { return pedido; }
    public void setPedido(String pedido) { this.pedido = pedido; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }

    public String getCidadeEmpresa() { return cidadeEmpresa; }
    public void setCidadeEmpresa(String cidadeEmpresa) { this.cidadeEmpresa = cidadeEmpresa; }

    public String getUfEmpresa() { return ufEmpresa; }
    public void setUfEmpresa(String ufEmpresa) { this.ufEmpresa = ufEmpresa; }

    public LocalDate getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(LocalDate dataAbertura) { this.dataAbertura = dataAbertura; }

    public LocalDate getDataFaturamento() { return dataFaturamento; }
    public void setDataFaturamento(LocalDate dataFaturamento) { this.dataFaturamento = dataFaturamento; }

    public Boolean getGarantia() { return garantia; }
    public void setGarantia(Boolean garantia) { this.garantia = garantia; }

    public String getDescricaoChamado() { return descricaoChamado; }
    public void setDescricaoChamado(String descricaoChamado) { this.descricaoChamado = descricaoChamado; }

    public String getObservacoesCliente() { return observacoesCliente; }
    public void setObservacoesCliente(String observacoesCliente) { this.observacoesCliente = observacoesCliente; }

    public String getObservacoesTecnico() { return observacoesTecnico; }
    public void setObservacoesTecnico(String observacoesTecnico) { this.observacoesTecnico = observacoesTecnico; }

    public String getPendencia() { return pendencia; }
    public void setPendencia(String pendencia) { this.pendencia = pendencia; }

    public Boolean getServicoFinalizado() { return servicoFinalizado; }
    public void setServicoFinalizado(Boolean servicoFinalizado) { this.servicoFinalizado = servicoFinalizado; }

    public String getAssinaturaBase64() { return assinaturaBase64; }
    public void setAssinaturaBase64(String assinaturaBase64) { this.assinaturaBase64 = assinaturaBase64; }

    public List<DefeitoDTO> getDefeitos() { return defeitos; }
    public void setDefeitos(List<DefeitoDTO> defeitos) { this.defeitos = defeitos; }

    public List<MaterialUtilizadoDTO> getMateriais() { return materiais; }
    public void setMateriais(List<MaterialUtilizadoDTO> materiais) { this.materiais = materiais; }
}
