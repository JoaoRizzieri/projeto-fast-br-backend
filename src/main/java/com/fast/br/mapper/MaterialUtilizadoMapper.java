package com.fast.br.mapper;

import com.fast.br.dto.MaterialUtilizadoDTO;
import com.fast.br.model.MaterialUtilizado;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MaterialUtilizadoMapper {
    MaterialUtilizadoMapper INSTANCE = Mappers.getMapper(MaterialUtilizadoMapper.class);

    MaterialUtilizadoDTO toDto(MaterialUtilizado entity);
    MaterialUtilizado toEntity(MaterialUtilizadoDTO dto);
}