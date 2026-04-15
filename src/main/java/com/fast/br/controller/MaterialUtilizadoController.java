package com.fast.br.controller;

import com.fast.br.dto.MaterialUtilizadoDTO;
import com.fast.br.mapper.MaterialUtilizadoMapper;
import com.fast.br.model.MaterialUtilizado;
import com.fast.br.model.OrdemServico;
import com.fast.br.repository.MaterialUtilizadoRepository;
import com.fast.br.repository.OrdemServicoRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/materiais")
public class MaterialUtilizadoController {

    private final MaterialUtilizadoRepository repository;
    private final MaterialUtilizadoMapper mapper;
    private final OrdemServicoRepository osRepository;

    public MaterialUtilizadoController(MaterialUtilizadoRepository repository, MaterialUtilizadoMapper mapper, OrdemServicoRepository osRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.osRepository = osRepository;
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
        if (dto.getIdOs() == null) {
            throw new RuntimeException("ID da OS é obrigatório");
        }
        
        OrdemServico os = osRepository.findById(dto.getIdOs())
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada: " + dto.getIdOs()));
        
        MaterialUtilizado obj = mapper.toEntity(dto, os);
        obj = repository.save(obj);
        return mapper.toDto(obj);
    }

    @PutMapping("/{id}")
    public MaterialUtilizadoDTO atualizar(@PathVariable("id") Long id, @RequestBody MaterialUtilizadoDTO dto) {
        MaterialUtilizado existente = repository.findById(id).orElseThrow();
        existente.setNomeMaterial(dto.getNomeMaterial());
        existente.setQuantidade(dto.getQuantidade());
        existente.setValorUnitario(dto.getValorUnitario());
        existente.setValorTotal(dto.getValorTotal());
        MaterialUtilizado atualizado = repository.save(existente);
        return mapper.toDto(atualizado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }
}