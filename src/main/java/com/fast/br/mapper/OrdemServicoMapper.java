package com.fast.br.mapper;

import com.fast.br.dto.OrdemServicoDTO;
import com.fast.br.model.OrdemServico;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrdemServicoMapper {
    OrdemServicoMapper INSTANCE = Mappers.getMapper(OrdemServicoMapper.class);

    OrdemServicoDTO toDto(OrdemServico entity);
    OrdemServico toEntity(OrdemServicoDTO dto);
}
