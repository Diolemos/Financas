package org.pedro;

import org.pedro.repository.AccountRepository;
import org.pedro.repository.InvestmentRepository;

public class Main {
    private static AccountRepository accountRepository = new AccountRepository();
    private static InvestmentRepository investmentRepository = new InvestmentRepository();
    
    public static void main(String[] args) {
        System.out.println("Olá, Bem-vindo ao sistema de contas e investimentos!");
    while(true){
        System.out.println("Selecione uma opção:");
        System.out.println("1. Criar conta");
        System.out.println("2. Criar investimento");
        System.out.println("3. Fazer um investimento");
        System.out.println("4. Depositar na conta");
        System.out.println("5. Sacar da conta");
        System.out.println("6. Transferir dinheiro entre contas");
        System.out.println("7. Investir");
        System.out.println("8. Resgatar investimento");
        System.out.println("9. Listar contas ");
        System.out.println("10. Listar investimentos");
        System.out.println("11. Listar carteiras de investimento");
      
        System.out.println("12. Atualizar investimento");
        System.out.println("13. Histórico de conta");
        System.out.println("14. Sair");
        int opcao = Integer.parseInt(System.console().readLine());
        switch(opcao){
            case 1:
                // Criar conta
                System.out.print("Digite a chave PIX: ");
                String pix = System.console().readLine();
                System.out.print("Digite o valor inicial: ");
                long initialAmount = Long.parseLong(System.console().readLine());
                accountRepository.create(java.util.Arrays.asList(pix), initialAmount);
                System.out.println("Conta criada com sucesso!");
                break;
            case 2:
                // Criar investimento
                System.out.print("Digite a taxa do investimento: ");
                long tax = Long.parseLong(System.console().readLine());
                System.out.print("Digite os fundos iniciais: ");
                long funds = Long.parseLong(System.console().readLine());
                try {
                    investmentRepository.createInvestment(tax, funds);
                    System.out.println("Investimento criado com sucesso!");
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }
                break;
            case 3:
                // Fazer um investimento
                System.out.print("Digite a chave PIX da conta: ");
                String accountPix = System.console().readLine();
                System.out.print("Digite o ID do investimento: ");
                long investmentId = Long.parseLong(System.console().readLine());
                try {
                    var account = accountRepository.findByPix(accountPix);
                    investmentRepository.initInvestment(account, investmentId);
                    System.out.println("Investimento realizado com sucesso!");
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }
                break;
            case 4:
                // Depositar na conta
                System.out.print("Digite a chave PIX: ");
                String depositPix = System.console().readLine();
                System.out.print("Digite o valor a depositar: ");
                long depositAmount = Long.parseLong(System.console().readLine());
                try {
                    accountRepository.deposit(depositPix, depositAmount);
                    System.out.println("Depósito realizado com sucesso!");
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }
                break;
            case 5:
                // Sacar da conta
                System.out.print("Digite a chave PIX: ");
                String withdrawPix = System.console().readLine();
                System.out.print("Digite o valor a sacar: ");
                long withdrawAmount = Long.parseLong(System.console().readLine());
                try {
                    accountRepository.withdraw(withdrawPix, withdrawAmount);
                    System.out.println("Saque realizado com sucesso!");
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }
                break;
            case 6:
                // Transferir dinheiro entre contas
                System.out.print("Digite a chave PIX de origem: ");
                String sourcePix = System.console().readLine();
                System.out.print("Digite a chave PIX de destino: ");
                String targetPix = System.console().readLine();
                System.out.print("Digite o valor a transferir: ");
                long transferAmount = Long.parseLong(System.console().readLine());
                try {
                    accountRepository.transferMoney(sourcePix, targetPix, transferAmount);
                    System.out.println("Transferência realizada com sucesso!");
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }
                break;
            case 7:
                // Investir
                System.out.print("Digite a chave PIX: ");
                String investPix = System.console().readLine();
                System.out.print("Digite o valor a investir: ");
                long investAmount = Long.parseLong(System.console().readLine());
                try {
                    investmentRepository.deposit(investPix, investAmount);
                    System.out.println("Investimento realizado com sucesso!");
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }
                break;
            case 8:
                // Resgatar investimento
                System.out.print("Digite a chave PIX: ");
                String redeemPix = System.console().readLine();
                System.out.print("Digite o valor a resgatar: ");
                long redeemAmount = Long.parseLong(System.console().readLine());
                try {
                    investmentRepository.withdraw(redeemPix, redeemAmount);
                    System.out.println("Resgate realizado com sucesso!");
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }
                break;
            case 9:
                // Listar contas
                System.out.println("=== Contas ===");
                accountRepository.getAllAccounts().forEach(account -> {
                    System.out.println("Conta: " + account.getPix() + " - Saldo: " + account.getFunds());
                });
                break;
            case 10:
                // Listar investimentos
                System.out.println("=== Investimentos ===");
                investmentRepository.listInvestments().forEach(investment -> {
                    System.out.println("Investimento ID: " + investment.getInvestment().id() + 
                                     " - Taxa: " + investment.getInvestment().tax() + 
                                     " - Fundos iniciais: " + investment.getInvestment().initalFunds());
                });
                break;
            case 11:
                // Listar carteiras de investimento
                System.out.println("=== Carteiras de Investimento ===");
                investmentRepository.listWallets().forEach(wallet -> {
                    System.out.println("Conta: " + wallet.getAccount().getPix() + 
                                     " - Saldo do investimento: " + wallet.getFunds() +
                                     " - Investimento ID: " + wallet.getInvestment().id());
                });
                break;
            case 12:
                // Atualizar investimento
                System.out.print("Digite a porcentagem de atualização dos investimentos: ");
                long percent = Long.parseLong(System.console().readLine());
                investmentRepository.updateAmount(percent);
                System.out.println("Investimentos atualizados com sucesso!");
                break;
            case 13:
                // Histórico de conta
                System.out.println("Opção 13: Histórico de conta");
                break;
            case 14:
                // Sair
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida!");
                break;   
            }
        }
    }
}