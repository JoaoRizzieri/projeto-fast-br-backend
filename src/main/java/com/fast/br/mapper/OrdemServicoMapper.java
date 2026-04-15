package com.fast.br.mapper;

import com.fast.br.dto.OrdemServicoDTO;
import com.fast.br.dto.ClienteDTO;
import com.fast.br.dto.TecnicoDTO;
import com.fast.br.dto.DefeitoDTO;
import com.fast.br.dto.MaterialUtilizadoDTO;
import com.fast.br.model.OrdemServico;
import com.fast.br.model.Cliente;
import com.fast.br.model.Tecnico;
import com.fast.br.model.Defeito;
import com.fast.br.model.MaterialUtilizado;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrdemServicoMapper {

    public OrdemServicoDTO toDto(OrdemServico entity) {
        if (entity == null) return null;
        
        OrdemServicoDTO dto = new OrdemServicoDTO();
        dto.setIdOs(entity.getIdOs());
        dto.setPedido(entity.getPedido());
        dto.setStatus(entity.getStatus());
        dto.setEmpresa(entity.getEmpresa());
        dto.setCidadeEmpresa(entity.getCidadeEmpresa());
        dto.setUfEmpresa(entity.getUfEmpresa());
        dto.setDataAbertura(entity.getDataAbertura());
        dto.setDataFaturamento(entity.getDataFaturamento());
        dto.setGarantia(entity.getGarantia());
        dto.setDescricaoChamado(entity.getDescricaoChamado());
        dto.setObservacoesCliente(entity.getObservacoesCliente());
        dto.setObservacoesTecnico(entity.getObservacoesTecnico());
        dto.setPendencia(entity.getPendencia());
        dto.setServicoFinalizado(entity.getServicoFinalizado());
        dto.setAssinaturaBase64(entity.getSignatureBase64());
        
        if (entity.getCliente() != null) {
            dto.setCliente(toClienteDto(entity.getCliente()));
        }
        if (entity.getTecnico() != null) {
            dto.setTecnico(toTecnicoDto(entity.getTecnico()));
        }
        if (entity.getDefeitos() != null) {
            dto.setDefeitos(entity.getDefeitos().stream().map(this::toDefeitoDto).collect(Collectors.toList()));
        }
        if (entity.getMateriais() != null) {
            dto.setMateriais(entity.getMateriais().stream().map(this::toMaterialDto).collect(Collectors.toList()));
        }
        
        return dto;
    }

    public ClienteDTO toClienteDto(Cliente cliente) {
        if (cliente == null) return null;
        ClienteDTO dto = new ClienteDTO();
        dto.setIdCliente(cliente.getIdCliente());
        dto.setNomeCliente(cliente.getNomeCliente());
        dto.setContato(cliente.getContato());
        dto.setTelefone(cliente.getTelefone());
        dto.setEndereco(cliente.getEndereco());
        dto.setNumero(cliente.getNumero());
        dto.setBairro(cliente.getBairro());
        dto.setCidade(cliente.getCidade());
        dto.setUf(cliente.getUf());
        dto.setCodigoCliente(cliente.getCodigoCliente());
        return dto;
    }

    public TecnicoDTO toTecnicoDto(Tecnico tecnico) {
        if (tecnico == null) return null;
        TecnicoDTO dto = new TecnicoDTO();
        dto.setIdTecnico(tecnico.getIdTecnico());
        dto.setNomeTecnico(tecnico.getNomeTecnico());
        dto.setEmail(tecnico.getEmail());
        dto.setTelefone(tecnico.getTelefone());
        dto.setNomeAjudante(tecnico.getNomeAjudante());
        dto.setTelefoneAjudante(tecnico.getTelefoneAjudante());
        dto.setValorHoraTrabalhada(tecnico.getValorHoraTrabalhada());
        dto.setValorDeslocamento(tecnico.getValorDeslocamento());
        dto.setValorPorKm(tecnico.getValorPorKm());
        dto.setValorHoraExtra(tecnico.getValorHoraExtra());
        return dto;
    }

    public DefeitoDTO toDefeitoDto(Defeito defeito) {
        if (defeito == null) return null;
        DefeitoDTO dto = new DefeitoDTO();
        dto.setIdDefeito(defeito.getIdDefeito());
        dto.setIdOs(defeito.getOrdemServico() != null ? defeito.getOrdemServico().getIdOs() : null);
        dto.setCategoria(defeito.getCategoria());
        dto.setDescricao(defeito.getDescricao());
        return dto;
    }

    public MaterialUtilizadoDTO toMaterialDto(MaterialUtilizado material) {
        if (material == null) return null;
        MaterialUtilizadoDTO dto = new MaterialUtilizadoDTO();
        dto.setIdMaterial(material.getIdMaterial());
        dto.setIdOs(material.getOrdemServico() != null ? material.getOrdemServico().getIdOs() : null);
        dto.setNomeMaterial(material.getNomeMaterial());
        dto.setQuantidade(material.getQuantidade());
        dto.setValorUnitario(material.getValorUnitario());
        dto.setValorTotal(material.getValorTotal());
        return dto;
    }
}
