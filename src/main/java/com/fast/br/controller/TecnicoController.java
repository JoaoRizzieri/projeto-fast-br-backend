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

    @PostMapping
    public TecnicoDTO criar(@RequestBody TecnicoRequestDTO dto) {
        Tecnico tecnico = new Tecnico();
        tecnico.setNomeTecnico(dto.getNomeTecnico());
        tecnico.setTelefone(dto.getTelefone());
        tecnico.setEmail(dto.getEmail());
        tecnico.setNomeAjudante(dto.getNomeAjudante());
        tecnico.setTelefoneAjudante(dto.getTelefoneAjudante());
        tecnico.setSenha(passwordEncoder.encode(dto.getSenha()));
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
}