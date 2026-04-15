package com.fast.br.mapper;

import com.fast.br.dto.MaterialUtilizadoDTO;
import com.fast.br.model.MaterialUtilizado;
import com.fast.br.model.OrdemServico;
import org.springframework.stereotype.Component;

@Component
public class MaterialUtilizadoMapper {

    public MaterialUtilizadoDTO toDto(MaterialUtilizado entity) {
        if (entity == null) return null;
        MaterialUtilizadoDTO dto = new MaterialUtilizadoDTO();
        dto.setIdMaterial(entity.getIdMaterial());
        dto.setIdOs(entity.getOrdemServico() != null ? entity.getOrdemServico().getIdOs() : null);
        dto.setNomeMaterial(entity.getNomeMaterial());
        dto.setQuantidade(entity.getQuantidade());
        dto.setValorUnitario(entity.getValorUnitario());
        dto.setValorTotal(entity.getValorTotal());
        return dto;
    }

    public MaterialUtilizado toEntity(MaterialUtilizadoDTO dto, OrdemServico ordemServico) {
        if (dto == null) return null;
        MaterialUtilizado entity = new MaterialUtilizado();
        if (dto.getIdMaterial() != null) {
            entity.setIdMaterial(dto.getIdMaterial());
        }
        entity.setOrdemServico(ordemServico);
        entity.setNomeMaterial(dto.getNomeMaterial());
        entity.setQuantidade(dto.getQuantidade());
        entity.setValorUnitario(dto.getValorUnitario());
        entity.setValorTotal(dto.getValorTotal());
        return entity;
    }
}