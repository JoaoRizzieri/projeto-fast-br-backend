package com.fast.br.controller;

import com.fast.br.dto.OrdemServicoDTO;
import com.fast.br.dto.request.OrdemServicoRequestDTO;
import com.fast.br.service.OrdemServicoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

    private final OrdemServicoService service;

    public OrdemServicoController(OrdemServicoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<OrdemServicoDTO>> listarTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "idOs") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase("asc") 
            ? Sort.by(sortBy).ascending() 
            : Sort.by(sortBy).descending();
        
        Page<OrdemServicoDTO> lista = service.listarTodos(PageRequest.of(page, size, sort));
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServicoDTO> buscarPorId(@PathVariable("id") Long id) {
        OrdemServicoDTO dto = service.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<OrdemServicoDTO> criar(@Valid @RequestBody OrdemServicoRequestDTO dto) {
        OrdemServicoDTO novoDto = service.criar(dto);
        return new ResponseEntity<>(novoDto, HttpStatus.CREATED);
    }

    @GetMapping("/minhas-os")
    public ResponseEntity<Page<OrdemServicoDTO>> listarMinhasOrdens(
            @RequestParam(required = false, defaultValue = "0") Long idTecnico,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "idOs") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase("asc") 
            ? Sort.by(sortBy).ascending() 
            : Sort.by(sortBy).descending();
        
        Page<OrdemServicoDTO> lista = service.listarMinhasOrdens(
            idTecnico, status, PageRequest.of(page, size, sort));
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrdemServicoDTO> atualizarStatus(@PathVariable("id") Long id, @RequestBody Map<String, String> request) {
        String status = request.get("status");
        String observacao = request.get("observacao");
        OrdemServicoDTO dto = service.atualizarStatus(id, status, observacao);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdemServicoDTO> atualizar(@PathVariable("id") Long id, @RequestBody Map<String, Object> request) {
        OrdemServicoDTO dto = service.atualizar(id, request);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/custos-tecnico")
    public ResponseEntity<OrdemServicoDTO> salvarCustosTecnico(@PathVariable("id") Long id, @RequestBody Map<String, Object> custos) {
        OrdemServicoDTO dto = service.salvarCustosTecnico(id, custos);
        return ResponseEntity.ok(dto);
    }
}
