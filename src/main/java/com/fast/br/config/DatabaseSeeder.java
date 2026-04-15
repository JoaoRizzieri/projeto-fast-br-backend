package com.fast.br.config;

import com.fast.br.model.*;
import com.fast.br.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final TecnicoRepository tecnicoRepository;
    private final ClienteRepository clienteRepository;
    private final OrdemServicoRepository osRepository;
    private final PasswordEncoder passwordEncoder;

    public DatabaseSeeder(TecnicoRepository tecnicoRepository, ClienteRepository clienteRepository, 
                          OrdemServicoRepository osRepository, PasswordEncoder passwordEncoder) {
        this.tecnicoRepository = tecnicoRepository;
        this.clienteRepository = clienteRepository;
        this.osRepository = osRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (tecnicoRepository.count() > 0) {
            System.out.println(">>> DatabaseSeeder: Dados já existem, pulando seed...");
            return;
        }

        System.out.println(">>> DatabaseSeeder: Criando dados de teste...");

        Tecnico tecnico = new Tecnico();
        tecnico.setNomeTecnico("Ricardo Técnico");
        tecnico.setEmail("teste@teste.com");
        tecnico.setSenha(passwordEncoder.encode("123456"));
        tecnico.setTelefone("(11) 97777-6666");
        tecnico.setNomeAjudante("Carlos Ajudante");
        tecnico.setTelefoneAjudante("(11) 96666-5555");
        tecnico.setValorHoraTrabalhada(60.0);
        tecnico.setValorDeslocamento(40.0);
        tecnico.setValorPorKm(0.85);
        tecnico.setValorHoraExtra(90.0);
        tecnico = tecnicoRepository.save(tecnico);

        Cliente cliente1 = new Cliente();
        cliente1.setNomeCliente("Supermercado Alvorada");
        cliente1.setContato("Sr. João");
        cliente1.setTelefone("(11) 98888-7777");
        cliente1.setEndereco("Av. das Nações");
        cliente1.setNumero("1000");
        cliente1.setBairro("Centro");
        cliente1.setCidade("São Paulo");
        cliente1.setUf("SP");
        cliente1.setCodigoCliente("CLI001");
        cliente1 = clienteRepository.save(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setNomeCliente("Loja Fast Br Unidade 2");
        cliente2.setContato("Dra. Ana");
        cliente2.setTelefone("(11) 95555-4444");
        cliente2.setEndereco("Rua das Flores");
        cliente2.setNumero("500");
        cliente2.setBairro("Jardins");
        cliente2.setCidade("Osasco");
        cliente2.setUf("SP");
        cliente2.setCodigoCliente("CLI002");
        cliente2 = clienteRepository.save(cliente2);

        Cliente cliente3 = new Cliente();
        cliente3.setNomeCliente("Indústria 123");
        cliente3.setTelefone("(21) 97777-7777");
        cliente3.setCidade("Rio de Janeiro");
        cliente3.setUf("RJ");
        cliente3 = clienteRepository.save(cliente3);

        Cliente cliente4 = new Cliente();
        cliente4.setNomeCliente("Maria Oliveira");
        cliente4.setTelefone("(11) 97777-6666");
        cliente4.setEndereco("Rua Nova");
        cliente4.setNumero("100");
        cliente4.setBairro("Vila Nova");
        cliente4.setCidade("São Paulo");
        cliente4.setUf("SP");
        cliente4 = clienteRepository.save(cliente4);

        criarOs(tecnico, cliente1, "PED-2024-001", "Fast Gôndolas", "Manutenção preventiva nas câmaras frias e troca de prateleiras.", "pendente", false);
        criarOs(tecnico, cliente2, "PED-2024-002", "Fast Br Serviços", "Troca de compressor de ar condicionado industrial.", "em_andamento", true);
        criarOsFinalizada(tecnico, cliente1, "PED-2024-003", "Fast Gôndolas", "Reparo emergencial no painel elétrico.", LocalDate.now().minusDays(5));
        criarOs(tecnico, cliente3, "PED-2024-004", "Indústria 123", "Instalação de novo equipamento.", "pendente", false);
        criarOs(tecnico, cliente4, "PED-2024-005", "Empresa Nova", "Instalação de ar condicionado.", "pendente", false);

        System.out.println(">>> DatabaseSeeder: Dados de teste criados com sucesso!");
        System.out.println(">>> Login: teste@teste.com / 123456");
    }

    private void criarOs(Tecnico tecnico, Cliente cliente, String pedido, String empresa, String descricao, String status, Boolean garantia) {
        OrdemServico os = new OrdemServico();
        os.setTecnico(tecnico);
        os.setCliente(cliente);
        os.setPedido(pedido);
        os.setDataAbertura(LocalDate.now());
        os.setEmpresa(empresa);
        os.setDescricaoChamado(descricao);
        os.setStatus(status);
        os.setGarantia(garantia);
        os.setServicoFinalizado(false);
        osRepository.save(os);
    }

    private void criarOsFinalizada(Tecnico tecnico, Cliente cliente, String pedido, String empresa, String descricao, LocalDate data) {
        OrdemServico os = new OrdemServico();
        os.setTecnico(tecnico);
        os.setCliente(cliente);
        os.setPedido(pedido);
        os.setDataAbertura(data);
        os.setDataFaturamento(data.plusDays(5));
        os.setEmpresa(empresa);
        os.setDescricaoChamado(descricao);
        os.setStatus("finalizada");
        os.setServicoFinalizado(true);
        os.setDataPrimeiraVisita(data.plusDays(2));
        osRepository.save(os);
    }
}
