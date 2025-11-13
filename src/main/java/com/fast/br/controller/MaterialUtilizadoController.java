package com.fast.br.controller;

import com.fast.br.dto.MaterialUtilizadoDTO;
import com.fast.br.mapper.MaterialUtilizadoMapper;
import com.fast.br.model.MaterialUtilizado;
import com.fast.br.repository.MaterialUtilizadoRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/materiais")
public class MaterialUtilizadoController {

    private final MaterialUtilizadoRepository repository;
    private final MaterialUtilizadoMapper mapper;

    public MaterialUtilizadoController(MaterialUtilizadoRepository repository, MaterialUtilizadoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @GetMapping
    public List<MaterialUtilizadoDTO> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MaterialUtilizadoDTO buscarPorId(@PathVariable("id") Long id) {
        MaterialUtilizado obj = repository.findById(id).orElseThrow();
        return mapper.toDto(obj);
    }

    @PostMapping
    public MaterialUtilizadoDTO criar(@RequestBody MaterialUtilizadoDTO dto) {
        MaterialUtilizado obj = mapper.toEntity(dto);
        obj = repository.save(obj);
        return mapper.toDto(obj);
    }

    @PutMapping("/{id}")
    public MaterialUtilizadoDTO atualizar(@PathVariable("id") Long id, @RequestBody MaterialUtilizadoDTO dto) {
        MaterialUtilizado atualizado = mapper.toEntity(dto);
        atualizado.setIdMaterial(id);
        atualizado = repository.save(atualizado);
        return mapper.toDto(atualizado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }
}