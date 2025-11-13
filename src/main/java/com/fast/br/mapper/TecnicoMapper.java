package com.fast.br.mapper;

import com.fast.br.dto.TecnicoDTO;
import com.fast.br.model.Tecnico;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TecnicoMapper {
    TecnicoMapper INSTANCE = Mappers.getMapper(TecnicoMapper.class);

    TecnicoDTO toDto(Tecnico entity);
    Tecnico toEntity(TecnicoDTO dto);
}
