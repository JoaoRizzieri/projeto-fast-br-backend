package com.fast.br.controller;

import com.fast.br.dto.TecnicoDTO;
import com.fast.br.dto.request.TecnicoRequestDTO;
import com.fast.br.mapper.TecnicoMapper;
import com.fast.br.model.Tecnico;
import com.fast.br.repository.TecnicoRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoController {

    private final TecnicoRepository repository;
    private final TecnicoMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public TecnicoController(TecnicoRepository repository, TecnicoMapper mapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<TecnicoDTO> listarTodos() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}/configuracao")
    public TecnicoDTO buscarConfiguracao(@PathVariable Long id) {
        Tecnico tecnico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Técnico não encontrado"));
        
        if (tecnico.getValorHoraTrabalhada() == null) {
            tecnico.setValorHoraTrabalhada(50.0);
        }
        if (tecnico.getValorDeslocamento() == null) {
            tecnico.setValorDeslocamento(30.0);
        }
        if (tecnico.getValorPorKm() == null) {
            tecnico.setValorPorKm(0.75);
        }
        if (tecnico.getValorHoraExtra() == null) {
            tecnico.setValorHoraExtra(75.0);
        }
        
        return mapper.toDto(tecnico);
    }

    @PostMapping
    public TecnicoDTO criar(@RequestBody TecnicoRequestDTO dto) {
        Tecnico tecnico = new Tecnico();
        tecnico.setNomeTecnico(dto.getNomeTecnico());
        tecnico.setTelefone(dto.getTelefone());
        tecnico.setEmail(dto.getEmail());
        tecnico.setNomeAjudante(dto.getNomeAjudante());
        tecnico.setTelefoneAjudante(dto.getTelefoneAjudante());
        tecnico.setSenha(passwordEncoder.encode(dto.getSenha()));
        
        tecnico.setValorHoraTrabalhada(50.0);
        tecnico.setValorDeslocamento(30.0);
        tecnico.setValorPorKm(0.75);
        tecnico.setValorHoraExtra(75.0);
        
        Tecnico salvo = repository.save(tecnico);
        return mapper.toDto(salvo);
    }

    @PutMapping("/{id}")
    public TecnicoDTO atualizar(@PathVariable Long id, @RequestBody TecnicoRequestDTO dto) {
        Tecnico tecnico = repository.findById(id).orElseThrow();
        tecnico.setNomeTecnico(dto.getNomeTecnico());
        tecnico.setTelefone(dto.getTelefone());
        tecnico.setEmail(dto.getEmail());
        tecnico.setNomeAjudante(dto.getNomeAjudante());
        tecnico.setTelefoneAjudante(dto.getTelefoneAjudante());
        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            tecnico.setSenha(passwordEncoder.encode(dto.getSenha()));
        }
        Tecnico atualizado = repository.save(tecnico);
        return mapper.toDto(atualizado);
    }

    @PutMapping("/{id}/configuracao")
    public TecnicoDTO atualizarConfiguracao(@PathVariable Long id, @RequestBody TecnicoDTO dto) {
        Tecnico tecnico = repository.findById(id).orElseThrow();
        
        if (dto.getValorHoraTrabalhada() != null) {
            tecnico.setValorHoraTrabalhada(dto.getValorHoraTrabalhada());
        }
        if (dto.getValorDeslocamento() != null) {
            tecnico.setValorDeslocamento(dto.getValorDeslocamento());
        }
        if (dto.getValorPorKm() != null) {
            tecnico.setValorPorKm(dto.getValorPorKm());
        }
        if (dto.getValorHoraExtra() != null) {
            tecnico.setValorHoraExtra(dto.getValorHoraExtra());
        }
        
        Tecnico atualizado = repository.save(tecnico);
        return mapper.toDto(atualizado);
    }
}