package com.fast.br.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrdemServicoDTO {
    private Long idOs;
    private ClienteDTO cliente;
    private TecnicoDTO tecnico;
    private String pedido;
    private LocalDate dataAbertura;
    private Boolean servicoFinalizado;
    private String descricaoChamado;
    private List<DefeitoDTO> defeitos;
    private List<MaterialUtilizadoDTO> materiais;
}