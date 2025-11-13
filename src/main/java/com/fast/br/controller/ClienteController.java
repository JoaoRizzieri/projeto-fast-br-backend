package com.fast.br.controller;

import com.fast.br.dto.ClienteDTO;
import com.fast.br.mapper.ClienteMapper;
import com.fast.br.model.Cliente;
import com.fast.br.repository.ClienteRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    public ClienteController(ClienteRepository repository, ClienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @GetMapping
    public List<ClienteDTO> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ClienteDTO buscarPorId(@PathVariable("id") Long id) {
        Cliente obj = repository.findById(id).orElseThrow();
        return mapper.toDto(obj);
    }

    @PostMapping
    public ClienteDTO criar(@RequestBody ClienteDTO dto) {
        Cliente obj = mapper.toEntity(dto);
        obj = repository.save(obj);
        return mapper.toDto(obj);
    }

    @PutMapping("/{id}")
    public ClienteDTO atualizar(@PathVariable("id") Long id, @RequestBody ClienteDTO dto) {
        Cliente atualizado = mapper.toEntity(dto);
        atualizado.setIdCliente(id);
        atualizado = repository.save(atualizado);
        return mapper.toDto(atualizado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }
}