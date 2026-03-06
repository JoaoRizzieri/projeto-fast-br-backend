package com.fast.br.service;

import com.fast.br.dto.OrdemServicoDTO;
import com.fast.br.dto.request.CustosTecnicoRequestDTO;
import com.fast.br.dto.request.OrdemServicoRequestDTO;
import com.fast.br.mapper.OrdemServicoMapper;
import com.fast.br.model.*;
import com.fast.br.repository.ClienteRepository;
import com.fast.br.repository.OrdemServicoRepository;
import com.fast.br.repository.TecnicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrdemServicoService {

    private final OrdemServicoRepository repository;
    private final ClienteRepository clienteRepository;
    private final TecnicoRepository tecnicoRepository;
    private final OrdemServicoMapper mapper;

    @Transactional(readOnly = true)
    public List<OrdemServicoDTO> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OrdemServicoDTO buscarPorId(Long id) {
        OrdemServico obj = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada com ID: " + id));
        return mapper.toDto(obj);
    }

    @Transactional
    public OrdemServicoDTO criar(OrdemServicoRequestDTO dto) {
        // 1. Buscar entidades relacionadas
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + dto.getIdCliente()));
        Tecnico tecnico = tecnicoRepository.findById(dto.getIdTecnico())
                .orElseThrow(() -> new RuntimeException("Técnico não encontrado com ID: " + dto.getIdTecnico()));

        // 2. Mapear DTO para a entidade principal
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
        ordemServico.setCliente(cliente);
        ordemServico.setTecnico(tecnico);

        // 3. Mapear e associar as listas filhas
        if (dto.getDefeitos() != null) {
            List<Defeito> defeitos = dto.getDefeitos().stream()
                    .map(d -> new Defeito(null, ordemServico, d.getCategoria(), d.getDescricao()))
                    .toList();
            ordemServico.setDefeitos(defeitos);
        }
        if (dto.getMateriais() != null) {
            List<MaterialUtilizado> materiais = dto.getMateriais().stream()
                    .map(m -> new MaterialUtilizado(null, ordemServico, m.getNomeMaterial(), m.getQuantidade(),
                            m.getValorUnitario(), m.getValorTotal()))
                    .toList();
            ordemServico.setMateriais(materiais);
        }

        // 4. Salvar a entidade pai
        OrdemServico salvo = repository.save(ordemServico);
        return mapper.toDto(salvo);
    }


    @Transactional
    public OrdemServicoDTO atualizarCompletamente(Long id, OrdemServicoDTO dto) {
        // 1. Busca a entidade existente no banco de dados.
        OrdemServico osExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada com ID: " + id));

        // 2. Atualiza os campos simples (dados diretos da OS).
        osExistente.setPedido(dto.getPedido());
        osExistente.setDataAbertura(dto.getDataAbertura());
        osExistente.setDataFaturamento(dto.getDataFaturamento());
        osExistente.setGarantia(dto.getGarantia());
        osExistente.setEmpresa(dto.getEmpresa());
        osExistente.setCidadeEmpresa(dto.getCidadeEmpresa());
        osExistente.setUfEmpresa(dto.getUfEmpresa());
        osExistente.setDescricaoChamado(dto.getDescricaoChamado());
        osExistente.setObservacoesCliente(dto.getObservacoesCliente());
        osExistente.setObservacoesTecnico(dto.getObservacoesTecnico());
        osExistente.setDataPrimeiraVisita(dto.getDataPrimeiraVisita());
        osExistente.setDataSegundaVisita(dto.getDataSegundaVisita());
        osExistente.setServicoFinalizado(dto.getServicoFinalizado());
        osExistente.setPendencia(dto.getPendencia());
        osExistente.setAssinaturaBase64(dto.getAssinaturaBase64());


        if (dto.getCliente() != null && dto.getCliente().getIdCliente() != null) {
            Cliente cliente = clienteRepository.findById(dto.getCliente().getIdCliente())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + dto.getCliente().getIdCliente()));
            osExistente.setCliente(cliente);
        }
        if (dto.getTecnico() != null && dto.getTecnico().getIdTecnico() != null) {
            Tecnico tecnico = tecnicoRepository.findById(dto.getTecnico().getIdTecnico())
                    .orElseThrow(() -> new RuntimeException("Técnico não encontrado com ID: " + dto.getTecnico().getIdTecnico()));
            osExistente.setTecnico(tecnico);
        }

        // Atualiza Defeitos
        osExistente.getDefeitos().clear(); // Remove todos os defeitos antigos da OS.
        if (dto.getDefeitos() != null) {
            dto.getDefeitos().forEach(defeitoDto -> {
                Defeito novoDefeito = new Defeito();
                novoDefeito.setCategoria(defeitoDto.getCategoria());
                novoDefeito.setDescricao(defeitoDto.getDescricao());
                novoDefeito.setOrdemServico(osExistente); // Associa o novo defeito à OS existente.
                osExistente.getDefeitos().add(novoDefeito); // Adiciona à lista.
            });
        }

        // Atualiza Materiais
        osExistente.getMateriais().clear(); // Remove todos os materiais antigos da OS.
        if (dto.getMateriais() != null) {
            dto.getMateriais().forEach(materialDto -> {
                MaterialUtilizado novoMaterial = new MaterialUtilizado();
                novoMaterial.setNomeMaterial(materialDto.getNomeMaterial());
                novoMaterial.setQuantidade(materialDto.getQuantidade());
                novoMaterial.setValorUnitario(materialDto.getValorUnitario());
                novoMaterial.setValorTotal(materialDto.getValorTotal());
                novoMaterial.setOrdemServico(osExistente); // Associa o novo material à OS existente.
                osExistente.getMateriais().add(novoMaterial); // Adiciona à lista.
            });
        }

        // 5. Salva a entidade.
        OrdemServico osSalva = repository.save(osExistente);

        // 6. Retorna o DTO atualizado.
        return mapper.toDto(osSalva);
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Ordem de Serviço não encontrada com ID: " + id);
        }
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<OrdemServicoDTO> listarMinhasOrdens(Long idTecnico, String status) {
        List<OrdemServico> ordens;

        if (status == null || status.isEmpty()) {
            ordens = repository.findByTecnicoIdTecnico(idTecnico);
        } else {
            switch (status) {
                case "iniciar":
                    ordens = repository.findByTecnicoIdTecnicoAndServicoFinalizadoAndDataPrimeiraVisitaIsNull(idTecnico, false);
                    break;
                case "em_andamento":
                    ordens = repository.findByTecnicoIdTecnicoAndServicoFinalizadoAndDataPrimeiraVisitaIsNotNull(idTecnico, false);
                    break;
                case "concluido":
                    ordens = repository.findByTecnicoIdTecnicoAndServicoFinalizado(idTecnico, true);
                    break;
                default:
                    ordens = repository.findByTecnicoIdTecnico(idTecnico);
            }
        }

        return ordens.stream()
                .map(mapper::toDto)
                .toList();
    }

    @Transactional
    public OrdemServicoDTO atualizarStatus(Long id, String status, String observacao) {
        OrdemServico os = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada com ID: " + id));
        
        os.setStatus(status);
        if (observacao != null && !observacao.isEmpty()) {
            os.setPendencia(observacao);
        }
        
        // Atualiza servicoFinalizado baseado no status
        if ("finalizada".equals(status) || "cancelada".equals(status)) {
            os.setServicoFinalizado(true);
        } else {
            os.setServicoFinalizado(false);
        }
        
        OrdemServico atualizado = repository.save(os);
        return mapper.toDto(atualizado);
    }

    @Transactional
    public OrdemServicoDTO salvarCustosTecnico(Long id, CustosTecnicoRequestDTO custos) {
        OrdemServico os = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada com ID: " + id));
        
        // Salvar custos nas observações do técnico (em formato JSON)
        StringBuilder observacoes = new StringBuilder();
        
        if (custos.getNomeAjudante() != null) {
            os.setTecnico(tecnicoRepository.findByTecnicoAjudante(custos.getNomeAjudante()));
        }
        
        if (custos.getCustosDeslocamento() != null) {
            observacoes.append("Deslocamento: ").append(custos.getCustosDeslocamento().getTotalHoras())
                      .append(" - R$ ").append(custos.getCustosDeslocamento().getTotalRs()).append("; ");
        }
        
        if (custos.getCustosHoraTrabalhada() != null) {
            observacoes.append("Hora Trabalhada: ").append(custos.getCustosHoraTrabalhada().getTotalHoras())
                      .append(" - R$ ").append(custos.getCustosHoraTrabalhada().getTotalRs()).append("; ");
        }
        
        if (custos.getCustosKm() != null) {
            observacoes.append("KM: ").append(custos.getCustosKm().getKm())
                      .append(" - R$ ").append(custos.getCustosKm().getTotalRs()).append("; ");
        }
        
        if (custos.getValorTotalGeral() != null) {
            observacoes.append("Total: R$ ").append(custos.getValorTotalGeral());
        }
        
        os.setObservacoesTecnico(observacoes.toString());
        
        // Salvar materiais
        if (custos.getDespesasMateriais() != null && !custos.getDespesasMateriais().isEmpty()) {
            custos.getDespesasMateriais().forEach(m -> {
                MaterialUtilizado material = new MaterialUtilizado();
                material.setNomeMaterial(m.getNomeMaterial());
                material.setQuantidade(m.getQuantidade());
                material.setValorUnitario(m.getValorUnitario());
                material.setValorTotal(m.getQuantidade() * m.getValorUnitario());
                material.setOrdemServico(os);
                os.getMateriais().add(material);
            });
        }
        
        OrdemServico atualizado = repository.save(os);
        return mapper.toDto(atualizado);
    }
}