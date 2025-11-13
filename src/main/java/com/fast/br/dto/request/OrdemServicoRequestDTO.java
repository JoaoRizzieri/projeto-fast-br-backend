package com.fast.br.dto.request;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrdemServicoRequestDTO {
    private Long idCliente;
    private Long idTecnico;
    private String pedido;
    private LocalDate dataFaturamento;
    private Boolean garantia;
    private String empresa;
    private String cidadeEmpresa;
    private String ufEmpresa;
    private LocalDate dataAbertura;
    private String descricaoChamado;
    private String observacoesCliente;
    private LocalDate dataPrimeiraVisita;
    private LocalDate dataSegundaVisita;
    private Boolean servicoFinalizado;
    private String pendencia;
    private String observacoesTecnico;
    private List<DefeitoRequestDTO> defeitos;
    private List<MaterialUtilizadoRequestDTO> materiais;
}