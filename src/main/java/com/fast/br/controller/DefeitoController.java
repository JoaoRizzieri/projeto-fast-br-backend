package com.fast.br.controller;

import com.fast.br.dto.DefeitoDTO;
import com.fast.br.mapper.DefeitoMapper;
import com.fast.br.model.Defeito;
import com.fast.br.model.OrdemServico;
import com.fast.br.repository.DefeitoRepository;
import com.fast.br.repository.OrdemServicoRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/defeitos")
public class DefeitoController {

    private final DefeitoRepository repository;
    private final DefeitoMapper mapper;
    private final OrdemServicoRepository osRepository;

    public DefeitoController(DefeitoRepository repository, DefeitoMapper mapper, OrdemServicoRepository osRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.osRepository = osRepository;
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
        if (dtos == null || dtos.isEmpty()) {
            return List.of();
        }
        
        Long idOs = dtos.get(0).getIdOs();
        OrdemServico os = osRepository.findById(idOs)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada: " + idOs));
        
        List<Defeito> entidades = dtos.stream()
                .map(dto -> mapper.toEntity(dto, os))
                .collect(Collectors.toList());
        
        List<Defeito> salvos = repository.saveAll(entidades);
        return salvos.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public DefeitoDTO atualizar(@PathVariable("id") Long id, @RequestBody DefeitoDTO dto) {
        Defeito existente = repository.findById(id).orElseThrow();
        existente.setCategoria(dto.getCategoria());
        existente.setDescricao(dto.getDescricao());
        Defeito atualizado = repository.save(existente);
        return mapper.toDto(atualizado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/os/{idOs}/detalhes")
    public List<DefeitoDTO> buscarPorOs(@PathVariable("idOs") Long idOs) {
        return repository.findByOrdemServicoIdOs(idOs).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}