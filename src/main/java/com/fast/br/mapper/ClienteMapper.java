package com.fast.br.mapper;

import com.fast.br.dto.ClienteDTO;
import com.fast.br.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteDTO toDto(Cliente entity) {
        if (entity == null) return null;
        ClienteDTO dto = new ClienteDTO();
        dto.setIdCliente(entity.getIdCliente());
        dto.setNomeCliente(entity.getNomeCliente());
        dto.setContato(entity.getContato());
        dto.setTelefone(entity.getTelefone());
        dto.setEndereco(entity.getEndereco());
        dto.setNumero(entity.getNumero());
        dto.setBairro(entity.getBairro());
        dto.setCidade(entity.getCidade());
        dto.setUf(entity.getUf());
        dto.setCodigoCliente(entity.getCodigoCliente());
        return dto;
    }

    public Cliente toEntity(ClienteDTO dto) {
        if (dto == null) return null;
        Cliente entity = new Cliente();
        if (dto.getIdCliente() != null) {
            entity.setIdCliente(dto.getIdCliente());
        }
        entity.setNomeCliente(dto.getNomeCliente());
        entity.setContato(dto.getContato());
        entity.setTelefone(dto.getTelefone());
        entity.setEndereco(dto.getEndereco());
        entity.setNumero(dto.getNumero());
        entity.setBairro(dto.getBairro());
        entity.setCidade(dto.getCidade());
        entity.setUf(dto.getUf());
        entity.setCodigoCliente(dto.getCodigoCliente());
        return entity;
    }
}