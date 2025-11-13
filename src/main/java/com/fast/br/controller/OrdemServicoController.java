package com.fast.br.controller;

import com.fast.br.dto.OrdemServicoDTO;
import com.fast.br.dto.request.OrdemServicoRequestDTO;
import com.fast.br.mapper.OrdemServicoMapper;
import com.fast.br.model.*;
import com.fast.br.repository.ClienteRepository;
import com.fast.br.repository.OrdemServicoRepository;
import com.fast.br.repository.TecnicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {


    private final OrdemServicoRepository repository;
    private final ClienteRepository clienteRepository;
    private final TecnicoRepository tecnicoRepository;
    private final OrdemServicoMapper mapper;

    public OrdemServicoController(OrdemServicoRepository repository, ClienteRepository clienteRepository, TecnicoRepository tecnicoRepository, OrdemServicoMapper mapper) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.tecnicoRepository = tecnicoRepository;
        this.mapper = mapper;
    }

    @GetMapping
    public List<OrdemServicoDTO> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OrdemServicoDTO buscarPorId(@PathVariable("id") Long id) {
        OrdemServico obj = repository.findById(id).orElseThrow();
        return mapper.toDto(obj);
    }

    @PostMapping
    public OrdemServicoDTO criar(@RequestBody OrdemServicoRequestDTO dto) {
        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setPedido(dto.getPedido());
        ordemServico.setDataFaturamento(dto.getDataFaturamento());
        ordemServico.setGarantia(dto.getGarantia());
        ordemServico.setEmpresa(dto.getEmpresa());
        ordemServico.setCidadeEmpresa(dto.getCidadeEmpresa());
        ordemServico.setUfEmpresa(dto.getUfEmpresa());
        ordemServico.setDataAbertura(dto.getDataAbertura());
        ordemServico.setDescricaoChamado(dto.getDescricaoChamado());
        ordemServico.setObservacoesCliente(dto.getObservacoesCliente());
        ordemServico.setDataPrimeiraVisita(dto.getDataPrimeiraVisita());
        ordemServico.setDataSegundaVisita(dto.getDataSegundaVisita());
        ordemServico.setServicoFinalizado(dto.getServicoFinalizado());
        ordemServico.setPendencia(dto.getPendencia());
        ordemServico.setObservacoesTecnico(dto.getObservacoesTecnico());

        // Buscar cliente e técnico pelo ID
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Tecnico tecnico = tecnicoRepository.findById(dto.getIdTecnico())
                .orElseThrow(() -> new RuntimeException("Técnico não encontrado"));
        ordemServico.setCliente(cliente);
        ordemServico.setTecnico(tecnico);

        // Defeitos e materiais
        if (dto.getDefeitos() != null) {
            List<Defeito> defeitos = dto.getDefeitos().stream()
                    .map(d -> new Defeito(null, ordemServico, d.getCategoria(), d.getDescricao())) // <-- ORDEM CORRIGIDA
                    .toList();
            ordemServico.setDefeitos(defeitos);
        }
        if (dto.getMateriais() != null) {
            List<MaterialUtilizado> materiais = dto.getMateriais().stream()
                    .map(m -> new MaterialUtilizado(null, ordemServico, m.getNomeMaterial(), m.getQuantidade(),
                            m.getValorUnitario(), m.getValorTotal() ))
                    .toList();
            ordemServico.setMateriais(materiais);
        }

        OrdemServico salvo = repository.save(ordemServico);
        return mapper.toDto(salvo);
    }

    @PutMapping("/{id}")
    public OrdemServicoDTO atualizar(@PathVariable("id") Long id, @RequestBody OrdemServicoDTO dto) {
        OrdemServico atualizado = mapper.toEntity(dto);
        atualizado.setIdOs(id);
        atualizado = repository.save(atualizado);
        return mapper.toDto(atualizado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }


    @GetMapping("/minhas-os")
    public List<OrdemServicoDTO> listarMinhasOrdens(Authentication authentication) {
        Long idTecnico = (Long) authentication.getDetails();
        return repository.findByTecnicoIdTecnico(idTecnico)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

}