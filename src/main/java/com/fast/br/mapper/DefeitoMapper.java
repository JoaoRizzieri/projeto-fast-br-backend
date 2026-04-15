package com.fast.br.mapper;

import com.fast.br.dto.DefeitoDTO;
import com.fast.br.model.Defeito;
import com.fast.br.model.OrdemServico;
import org.springframework.stereotype.Component;

@Component
public class DefeitoMapper {

    public DefeitoDTO toDto(Defeito entity) {
        if (entity == null) return null;
        DefeitoDTO dto = new DefeitoDTO();
        dto.setIdDefeito(entity.getIdDefeito());
        dto.setIdOs(entity.getOrdemServico() != null ? entity.getOrdemServico().getIdOs() : null);
        dto.setCategoria(entity.getCategoria());
        dto.setDescricao(entity.getDescricao());
        return dto;
    }

    public Defeito toEntity(DefeitoDTO dto, OrdemServico ordemServico) {
        if (dto == null) return null;
        Defeito entity = new Defeito();
        if (dto.getIdDefeito() != null) {
            entity.setIdDefeito(dto.getIdDefeito());
        }
        entity.setOrdemServico(ordemServico);
        entity.setCategoria(dto.getCategoria());
        entity.setDescricao(dto.getDescricao());
        return entity;
    }
}