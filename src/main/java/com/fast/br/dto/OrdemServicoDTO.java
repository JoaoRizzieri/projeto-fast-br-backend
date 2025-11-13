package com.fast.br.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrdemServicoDTO {

    // --- Dados da Ordem de Serviço ---
    private Long idOs;
    private String pedido;
    private LocalDate dataAbertura;
    private LocalDate dataFaturamento;

    // --- Dados da Garantia e Empresa ---
    private Boolean garantia;
    private String empresa;
    private String cidadeEmpresa;
    private String ufEmpresa;

    // --- Descrições e Observações ---
    private String descricaoChamado;
    private String observacoesCliente;
    private String observacoesTecnico;

    // --- Datas das Visitas ---
    private LocalDate dataPrimeiraVisita;
    private LocalDate dataSegundaVisita;

    // --- Status da Ordem de Serviço ---
    private Boolean servicoFinalizado;
    private String pendencia;

    // --- Relacionamentos ---
    private ClienteDTO cliente;
    private TecnicoDTO tecnico;
    private List<DefeitoDTO> defeitos;
    private List<MaterialUtilizadoDTO> materiais;

}