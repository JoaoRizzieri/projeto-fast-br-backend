package com.fast.br.controller;

import com.fast.br.dto.OrdemServicoDTO;
import com.fast.br.dto.request.CustosTecnicoRequestDTO;
import com.fast.br.dto.request.OrdemServicoRequestDTO;
import com.fast.br.service.OrdemServicoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ordens-servico")
@AllArgsConstructor
public class OrdemServicoController {

    private final OrdemServicoService service;

    @GetMapping
    public ResponseEntity<List<OrdemServicoDTO>> listarTodos() {
        List<OrdemServicoDTO> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServicoDTO> buscarPorId(@PathVariable("id") Long id) {
        OrdemServicoDTO dto = service.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<OrdemServicoDTO> criar(@RequestBody OrdemServicoRequestDTO dto) {
        OrdemServicoDTO novoDto = service.criar(dto);
        return new ResponseEntity<>(novoDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdemServicoDTO> atualizarCompletamente(@PathVariable("id") Long id, @RequestBody OrdemServicoDTO dto) {
        OrdemServicoDTO osAtualizada = service.atualizarCompletamente(id, dto);
        return ResponseEntity.ok(osAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/minhas-os")
    public ResponseEntity<List<OrdemServicoDTO>> listarMinhasOrdens(
            Authentication authentication,
            @RequestParam(required = false) String status) {
        Long idTecnico = (Long) authentication.getDetails();
        List<OrdemServicoDTO> lista = service.listarMinhasOrdens(idTecnico, status);
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrdemServicoDTO> atualizarStatus(@PathVariable("id") Long id, @RequestBody Map<String, String> request) {
        String status = request.get("status");
        String observacao = request.get("observacao");
        OrdemServicoDTO dto = service.atualizarStatus(id, status, observacao);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/custos-tecnico")
    public ResponseEntity<OrdemServicoDTO> salvarCustosTecnico(@PathVariable("id") Long id, @RequestBody CustosTecnicoRequestDTO custos) {
        OrdemServicoDTO dto = service.salvarCustosTecnico(id, custos);
        return ResponseEntity.ok(dto);
    }
}