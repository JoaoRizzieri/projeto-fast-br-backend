package com.fast.br.service;

import com.fast.br.dto.OrdemServicoDTO;
import com.fast.br.dto.request.OrdemServicoRequestDTO;
import com.fast.br.mapper.OrdemServicoMapper;
import com.fast.br.model.*;
import com.fast.br.repository.ClienteRepository;
import com.fast.br.repository.OrdemServicoRepository;
import com.fast.br.repository.TecnicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class OrdemServicoService {

    private final OrdemServicoRepository repository;
    private final ClienteRepository clienteRepository;
    private final TecnicoRepository tecnicoRepository;
    private final OrdemServicoMapper mapper;

    public OrdemServicoService(OrdemServicoRepository repository, ClienteRepository clienteRepository, 
                              TecnicoRepository tecnicoRepository, OrdemServicoMapper mapper) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.tecnicoRepository = tecnicoRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Page<OrdemServicoDTO> listarTodos(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public OrdemServicoDTO buscarPorId(Long id) {
        OrdemServico obj = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada com ID: " + id));
        return mapper.toDto(obj);
    }

    @Transactional
    public OrdemServicoDTO criar(OrdemServicoRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + dto.getIdCliente()));
        Tecnico tecnico = tecnicoRepository.findById(dto.getIdTecnico())
                .orElseThrow(() -> new RuntimeException("Técnico não encontrado com ID: " + dto.getIdTecnico()));

        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setPedido(dto.getPedido());
        ordemServico.setGarantia(dto.getGarantia());
        ordemServico.setEmpresa(dto.getEmpresa());
        ordemServico.setDataAbertura(dto.getDataAbertura());
        ordemServico.setDescricaoChamado(dto.getDescricaoChamado());
        ordemServico.setCliente(cliente);
        ordemServico.setTecnico(tecnico);
        ordemServico.setStatus(dto.getStatus() != null ? dto.getStatus() : "pendente");

        OrdemServico salvo = repository.save(ordemServico);
        return mapper.toDto(salvo);
    }

    @Transactional(readOnly = true)
    public Page<OrdemServicoDTO> listarMinhasOrdens(Long idTecnico, String status, Pageable pageable) {
        Page<OrdemServico> ordens;
        if (idTecnico == null || idTecnico == 0) {
            ordens = repository.findAll(pageable);
        } else {
            ordens = repository.findByTecnicoIdTecnico(idTecnico, pageable);
        }
        return ordens.map(mapper::toDto);
    }

    @Transactional
    public OrdemServicoDTO atualizarStatus(Long id, String status, String observacao) {
        OrdemServico os = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada com ID: " + id));
        os.setStatus(status);
        if (observacao != null) {
            os.setObservacoesTecnico(observacao);
        }
        return mapper.toDto(repository.save(os));
    }

    @Transactional
    public OrdemServicoDTO atualizar(Long id, Map<String, Object> dados) {
        OrdemServico os = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada com ID: " + id));
        
        if (dados.containsKey("status")) {
            os.setStatus((String) dados.get("status"));
        }
        if (dados.containsKey("observacoesTecnico")) {
            os.setObservacoesTecnico((String) dados.get("observacoesTecnico"));
        }
        if (dados.containsKey("servicoFinalizado")) {
            os.setServicoFinalizado((Boolean) dados.get("servicoFinalizado"));
        }
        if (dados.containsKey("assinaturaBase64")) {
            os.setSignatureBase64((String) dados.get("assinaturaBase64"));
        }
        if (dados.containsKey("dataPrimeiraVisita")) {
            String dataStr = (String) dados.get("dataPrimeiraVisita");
            if (dataStr != null && !dataStr.isEmpty()) {
                os.setDataPrimeiraVisita(java.time.LocalDate.parse(dataStr));
            }
        }
        if (dados.containsKey("dataSegundaVisita")) {
            String dataStr = (String) dados.get("dataSegundaVisita");
            if (dataStr != null && !dataStr.isEmpty()) {
                os.setDataSegundaVisita(java.time.LocalDate.parse(dataStr));
            }
        }
        
        OrdemServico salvo = repository.save(os);
        return mapper.toDto(salvo);
    }

    @Transactional
    public OrdemServicoDTO salvarCustosTecnico(Long id, Map<String, Object> custos) {
        OrdemServico os = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada com ID: " + id));
        
        try {
            com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
            objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
            String custosJson = objectMapper.writeValueAsString(custos);
            os.setObservacoesTecnico(custosJson);
        } catch (Exception e) {
            os.setObservacoesTecnico(custos.toString());
        }
        
        OrdemServico salvo = repository.save(os);
        return mapper.toDto(salvo);
    }
}
