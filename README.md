# Sistema Bancário e de Investimentos

Este projeto é um **sistema bancário básico** desenvolvido como parte do desafio da DIO, com foco em **Programação Orientada a Objetos (POO)**. O sistema permite a criação e gerenciamento de contas, investimentos, transações via PIX e acompanhamento de histórico de operações.

---

## Funcionalidades

O sistema implementa as seguintes funcionalidades:

1. **Criar conta**: Cadastro de contas com chave PIX e saldo inicial.
2. **Criar investimento**: Cadastro de investimentos com taxa e fundos iniciais.
3. **Fazer um investimento**: Vincular fundos de uma conta a um investimento.
4. **Depositar na conta**: Adicionar fundos a uma conta existente.
5. **Sacar da conta**: Retirar fundos de uma conta.
6. **Transferir dinheiro entre contas**: Transferência via PIX entre contas.
7. **Resgatar investimento**: Retirar fundos de um investimento vinculado.
8. **Listar contas**: Visualizar todas as contas cadastradas e seus saldos.
9. **Listar investimentos**: Visualizar todos os investimentos cadastrados.
10. **Listar carteiras de investimento**: Mostrar investimentos vinculados a cada conta.
11. **Atualizar investimento**: Aplicar atualização percentual aos investimentos.
12. **Histórico de transações**: Consultar histórico de operações de cada conta.
13. **Sair do sistema**: Encerrar o programa.

---

## Tecnologias Utilizadas

- **Java 21**
- **POO (Programação Orientada a Objetos)**
- **Lombok** para simplificação de getters, setters e construtores
- **Enums e Records** para representação de dados
- **Coleções Java** para armazenar contas e investimentos em memória

## Observações

Por enquanto as transações são simuladas em memória, sem persistência em banco de dados.

A aplicação foi desenvolvida como um laboratório para praticar POO, encapsulamento, herança, polimorfismo e reuso de código.

Exceções personalizadas são utilizadas para tratamento de erros, como saldo insuficiente.
