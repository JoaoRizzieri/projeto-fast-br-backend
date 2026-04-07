# Levantamento de Requisitos - API Sistema de Ordens de Serviço

## Visão Geral
Sistema mobile para técnicos em campo gerenciarem Ordens de Serviço (OS).

---

## Endpoints - Receber Dados (API → App)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/auth/login` | Autenticação do técnico |
| GET | `/ordens-servico` | Listar todas OS |
| GET | `/ordens-servico/minhas-os?status=` | Listar OS do técnico (status: iniciar, em_andamento, concluido) |
| GET | `/ordens-servico/{id}` | Detalhes de uma OS |
| GET | `/tecnicos/{id}/configuracao` | Configurações do técnico (R$/hora, R$/km) |
| GET | `/materiais?categoria=` | Catálogo de materiais |
| GET | `/chat/{idOs}/mensagens` | Listar mensagens do chat |

### Response: Login
```json
{
  "token": "eyJhbGc...",
  "tecnico": {
    "idTecnico": 1,
    "nomeTecnico": "Carlos",
    "telefone": "(11) 99999-9999",
    "email": "carlos@email.com",
    "nomeAjudante": "João",
    "telefoneAjudante": "(11) 88888-8888",
    "valorHoraTrabalhada": 50.00,
    "valorDeslocamento": 30.00,
    "valorPorKm": 0.75,
    "valorHoraExtra": 75.00
  }
}
```

### Response: Listar OS
```json
{
  "content": [
    {
      "idOs": 123,
      "pedido": "PED-001",
      "status": "pendente",
      "servicoFinalizado": false,
      "dataAbertura": "2024-01-15",
      "empresa": "Empresa ABC",
      "cidadeEmpresa": "São Paulo",
      "ufEmpresa": "SP",
      "garantia": false,
      "descricaoChamado": "...",
      "cliente": { ... },
      "tecnico": { ... }
    }
  ],
  "totalElements": 50
}
```

### Response: Detalhes OS
```json
{
  "idOs": 123,
  "pedido": "PED-001",
  "status": "pendente",
  "servicoFinalizado": false,
  "dataAbertura": "2024-01-15",
  "dataFaturamento": "2024-01-20",
  "garantia": false,
  "empresa": "Empresa ABC",
  "cidadeEmpresa": "São Paulo",
  "ufEmpresa": "SP",
  "descricaoChamado": "...",
  "observacoesCliente": "...",
  "dataPrimeiraVisita": null,
  "dataSegundaVisita": null,
  "pendencia": null,
  "observacoesTecnico": null,
  "assinaturaBase64": null,
  "cliente": {
    "idCliente": 1,
    "nomeCliente": "João Silva",
    "telefone": "(11) 99999-9999",
    "endereco": "Rua das Flores",
    "numero": "123",
    "bairro": "Centro",
    "cidade": "São Paulo",
    "uf": "SP"
  },
  "tecnico": { "idTecnico": 1, "nomeTecnico": "Carlos" },
  "defeitos": [{ "categoria": "Elétrico", "descricao": "..." }],
  "materiais": [{ "nomeMaterial": "Fio 2.5mm", "quantidade": 10, "valorUnitario": 2.50, "valorTotal": 25.00 }]
}
```

### Response: Configuração Técnico
```json
{
  "idTecnico": 1,
  "nomeTecnico": "Carlos",
  "telefone": "(11) 99999-9999",
  "email": "carlos@email.com",
  "nomeAjudante": "João",
  "telefoneAjudante": "(11) 88888-8888",
  "valorHoraTrabalhada": 50.00,
  "valorDeslocamento": 30.00,
  "valorPorKm": 0.75,
  "valorHoraExtra": 75.00
}
```

### Response: Catálogo Materiais
```json
{
  "materiais": [
    { "idMaterial": 1, "nomeMaterial": "Fio 2.5mm", "categoria": "Elétrico", "valorUnitario": 2.50 },
    { "idMaterial": 2, "nomeMaterial": "Disjuntor 20A", "categoria": "Elétrico", "valorUnitario": 15.00 }
  ]
}
```

---

## Endpoints - Enviar Dados (App → API)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/defeitos` | Enviar defeitos encontrados |
| POST | `/materiais` | Enviar materiais utilizados |
| PUT | `/ordens-servico/{id}` | Atualizar OS completa |
| PUT | `/ordens-servico/{id}/status` | Alterar status |
| PUT | `/ordens-servico/{id}/custos-tecnico` | Salvar custos do técnico |
| PUT | `/tecnicos/{id}/configuracao` | Atualizar configurações |
| POST | `/chat/{idOs}/mensagens` | Enviar mensagem |

### Request: Atualizar Status
```json
{ "status": "em_andamento", "observacao": "..." }
```

### Request: Salvar Custos Técnico
```json
{
  "nomeAjudante": "João",
  "custosDeslocamento": {
    "chegadaEmpresa": "08:00",
    "saidaEmpresa": "10:00",
    "chegadaCliente": "10:30",
    "saidaCliente": "12:00",
    "totalHoras": "03:30",
    "totalRs": 175.00
  },
  "custosHoraTrabalhada": {
    "chegadaEmpresa": "13:00",
    "saidaEmpresa": "17:00",
    "totalHoras": "04:00",
    "totalRs": 200.00
  },
  "custosKm": {
    "km": "50",
    "rsPorKm": 0.75,
    "totalRs": 37.50
  },
  "despesasMateriais": [
    { "nomeMaterial": "Fio 2.5mm", "quantidade": 10, "valorUnitario": 2.50, "valorTotal": 25.00 }
  ],
  "valorTotalGeral": 437.50
}
```

### Request: Atualizar OS Completa
```json
{
  "status": "finalizada",
  "dataPrimeiraVisita": "2024-01-15T14:30:00",
  "dataSegundaVisita": "2024-01-16T10:00:00",
  "observacoesTecnico": "Serviço concluído",
  "pendencia": "...",
  "assinaturaBase64": "data:image/png;base64,...",
  "servicoFinalizado": true,
  "defeitos": [{ "categoria": "Elétrico", "descricao": "..." }],
  "materiais": [{ "nomeMaterial": "Fio 2.5mm", "quantidade": 10, "valorUnitario": 2.50, "valorTotal": 25.00 }]
}
```

### Request: Chat
```json
{ "mensagem": "Cheguei no local", "remetente": "tecnico" }
```

### Request: Configuração Técnico
```json
{
  "valorHoraTrabalhada": 60.00,
  "valorDeslocamento": 35.00,
  "valorPorKm": 0.80,
  "valorHoraExtra": 90.00
}
```

---

## Status da OS
```
pendente → em_andamento → aguardando_peca → finalizada
                                      ↓
                                  cancelada
```

---

## Regras Técnicas

- **Autenticação:** Bearer Token (JWT) no header
- **Formato Data:** `YYYY-MM-DD` ou `YYYY-MM-DDTHH:mm:ss`
- **Formato Decimal:** ponto (ex: 1250.99)
- **Fotos:** Comprimir máx 1MB
- **Paginação:** `?page=0&size=20`

---

## Dados por Entidade

### Cliente
idCliente, nomeCliente, telefone, endereco, numero, bairro, cidade, uf

### OS
idOs, pedido, dataAbertura, dataFaturamento, garantia, empresa, cidadeEmpresa, ufEmpresa, descricaoChamado, observacoesCliente, dataPrimeiraVisita, dataSegundaVisitastatus, servicoFinalizado, pendencia, observacoesTecnico, assinaturaBase64, defeitos[], materiais[]

### Defeito
categoria, descricao

### Material
nomeMaterial, quantidade, valorUnitario, valorTotal

### Técnico
idTecnico, nomeTecnico, telefone, email, nomeAjudante, telefoneAjudante, valorHoraTrabalhada, valorDeslocamento, valorPorKm, valorHoraExtra

### Chat
idMensagem, idOs, mensagem, remetente (tecnico/empresa), dataEnvio, lida
