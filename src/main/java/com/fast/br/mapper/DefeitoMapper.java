package com.fast.br.mapper;

import com.fast.br.dto.DefeitoDTO;
import com.fast.br.model.Defeito;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DefeitoMapper {
    DefeitoMapper INSTANCE = Mappers.getMapper(DefeitoMapper.class);

    DefeitoDTO toDto(Defeito entity);
    Defeito toEntity(DefeitoDTO dto);
}