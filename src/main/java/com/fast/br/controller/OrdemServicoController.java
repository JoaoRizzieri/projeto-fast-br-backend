package com.fast.br.controller;

import com.fast.br.dto.OrdemServicoDTO;
import com.fast.br.dto.request.OrdemServicoRequestDTO;
import com.fast.br.service.OrdemServicoService; // Importa a nova classe de serviço
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ordens-servico")
@AllArgsConstructor // Lombok para injeção de dependências
public class OrdemServicoController {

    // O Controller agora depende APENAS do Service
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
        // Retorna o status 201 Created, que é o padrão para criação de recursos
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
        // Retorna o status 204 No Content, padrão para deleção bem-sucedida
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/minhas-os")
    public ResponseEntity<List<OrdemServicoDTO>> listarMinhasOrdens(Authentication authentication) {
        Long idTecnico = (Long) authentication.getDetails();
        List<OrdemServicoDTO> lista = service.listarMinhasOrdens(idTecnico);
        return ResponseEntity.ok(lista);
    }
}