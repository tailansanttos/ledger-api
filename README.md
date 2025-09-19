# Ledger de Transações Financeiras

Projeto backend em Java + Spring Boot que simula um **ledger** (livro-razão) com suporte a depósitos, saques e transferências.

## 🎯 Objetivo
Este projeto foi desenvolvido com foco em **aprendizado**.  
A ideia é evoluir o sistema por fases, começando pelo básico e adicionando complexidade gradualmente:
- Regras de negócio bancárias,
- Persistência com PostgreSQL,
- Processamento assíncrono com Kafka,
- Segurança com JWT (futuro).
  
## 🛠️ Evolução por Fases

### Fase 1 – CRUD básico
- Criar Conta
- Realizar Depósitos
- Realizar Saques
- Consultar Extrato

### Fase 2 – Regras de Negócio
- Validação de saldo suficiente para saques
- Transferência entre contas
- Cada cliente só pode ter uma conta

### Fase 3 – Kafka (Assíncrono)
- Publicar transações em tópico Kafka
- Consumidor processa transações e atualiza saldos
- Idempotência com `transactionId` único

### Fase 4 – Relatórios e Auditoria
- Histórico completo de transações (imutável)
- Relatórios de saldo médio e total de operações

### Fase 5 – Segurança (futuro)
- Autenticação com JWT
- Usuário só acessa sua própria conta

## ⚙️ Tecnologias
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Kafka (para processamento assíncrono)
- Docker (para orquestrar PostgreSQL e Kafka)
