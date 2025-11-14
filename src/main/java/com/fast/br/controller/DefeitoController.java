package com.fast.br.controller;

import com.fast.br.dto.DefeitoDTO;
import com.fast.br.mapper.DefeitoMapper;
import com.fast.br.model.Defeito;
import com.fast.br.repository.DefeitoRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/defeitos")
public class DefeitoController {

    private final DefeitoRepository repository;
    private final DefeitoMapper mapper;

    public DefeitoController(DefeitoRepository repository, DefeitoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DefeitoDTO> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DefeitoDTO buscarPorId(@PathVariable("id") Long id) {
        Defeito obj = repository.findById(id).orElseThrow();
        return mapper.toDto(obj);
    }

    @PostMapping
    public List<DefeitoDTO> criar(@RequestBody List<DefeitoDTO> dtos) {
        List<Defeito> entidades = dtos.stream().map(mapper::toEntity).collect(Collectors.toList());
        List<Defeito> salvos = repository.saveAll(entidades);
        return salvos.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public DefeitoDTO atualizar(@PathVariable("id") Long id, @RequestBody DefeitoDTO dto) {
        Defeito atualizado = mapper.toEntity(dto);
        atualizado.setIdDefeito(id);
        atualizado = repository.save(atualizado);
        return mapper.toDto(atualizado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }
}