package com.fast.br.service;

import com.fast.br.dto.OrdemServicoDTO;
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

@Service // Anotação que identifica esta classe como um componente de serviço do Spring
@AllArgsConstructor // Lombok para injeção de dependências via construtor
public class OrdemServicoService {

    // O serviço agora é quem gerencia os repositórios e mappers
    private final OrdemServicoRepository repository;
    private final ClienteRepository clienteRepository;
    private final TecnicoRepository tecnicoRepository;
    private final OrdemServicoMapper mapper;

    // Métodos que apenas leem dados podem ser otimizados com readOnly = true
    @Transactional(readOnly = true)
    public List<OrdemServicoDTO> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OrdemServicoDTO buscarPorId(Long id) {
        // É uma boa prática usar uma exceção mais específica, mas vamos manter a original por enquanto
        OrdemServico obj = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada com ID: " + id));
        return mapper.toDto(obj);
    }

    // Métodos que modificam dados devem ser transacionais para garantir a integridade
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

        // 4. Salvar a entidade pai (o JPA/Hibernate salvará as filhas em cascata)
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

        // 3. Atualiza os relacionamentos simples (Cliente e Técnico).
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

        // 5. Salva a entidade. O JPA/Hibernate cuidará de gerar os SQLs de UPDATE, DELETE e INSERT necessários.
        OrdemServico osSalva = repository.save(osExistente);

        // 6. Retorna o DTO atualizado.
        return mapper.toDto(osSalva);
    }

    @Transactional
    public void deletar(Long id) {
        // Verifica se o registro existe antes de tentar deletar para evitar exceções
        if (!repository.existsById(id)) {
            throw new RuntimeException("Ordem de Serviço não encontrada com ID: " + id);
        }
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<OrdemServicoDTO> listarMinhasOrdens(Long idTecnico) {
        return repository.findByTecnicoIdTecnico(idTecnico)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
