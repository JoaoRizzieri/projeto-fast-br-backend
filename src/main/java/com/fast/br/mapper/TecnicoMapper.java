package com.fast.br.mapper;

import com.fast.br.dto.TecnicoDTO;
import com.fast.br.model.Tecnico;
import org.springframework.stereotype.Component;

@Component
public class TecnicoMapper {

    public TecnicoDTO toDto(Tecnico entity) {
        if (entity == null) return null;
        TecnicoDTO dto = new TecnicoDTO();
        dto.setIdTecnico(entity.getIdTecnico());
        dto.setNomeTecnico(entity.getNomeTecnico());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        dto.setNomeAjudante(entity.getNomeAjudante());
        dto.setTelefoneAjudante(entity.getTelefoneAjudante());
        dto.setValorHoraTrabalhada(entity.getValorHoraTrabalhada());
        dto.setValorDeslocamento(entity.getValorDeslocamento());
        dto.setValorPorKm(entity.getValorPorKm());
        dto.setValorHoraExtra(entity.getValorHoraExtra());
        return dto;
    }

    public Tecnico toEntity(TecnicoDTO dto) {
        if (dto == null) return null;
        Tecnico entity = new Tecnico();
        if (dto.getIdTecnico() != null) {
            entity.setIdTecnico(dto.getIdTecnico());
        }
        entity.setNomeTecnico(dto.getNomeTecnico());
        entity.setEmail(dto.getEmail());
        entity.setTelefone(dto.getTelefone());
        entity.setNomeAjudante(dto.getNomeAjudante());
        entity.setTelefoneAjudante(dto.getTelefoneAjudante());
        entity.setValorHoraTrabalhada(dto.getValorHoraTrabalhada());
        entity.setValorDeslocamento(dto.getValorDeslocamento());
        entity.setValorPorKm(dto.getValorPorKm());
        entity.setValorHoraExtra(dto.getValorHoraExtra());
        return entity;
    }
}
