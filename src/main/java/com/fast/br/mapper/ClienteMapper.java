package com.fast.br.mapper;

import com.fast.br.dto.ClienteDTO;
import com.fast.br.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    ClienteDTO toDto(Cliente entity);
    Cliente toEntity(ClienteDTO dto);
}