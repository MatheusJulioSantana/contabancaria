package conta;

import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {

	public static Scanner leia = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		ContaController contas = new ContaController();
		
		Scanner leia = new Scanner(System.in);

		int opcao,numero,agencia,tipo,aniversario;
		String titular;
		float saldo,limite;

		while (true) {
			
			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND + "*****************************************************");
			System.out.println("                                                     ");
			System.out.println("                BANCO DO BRAZIL COM Z                ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Conta                          ");
			System.out.println("            2 - Listar todas as Contas               ");
			System.out.println("            3 - Buscar Conta por Numero              ");
			System.out.println("            4 - Atualizar Dados da Conta             ");
			System.out.println("            5 - Apagar Conta                         ");
			System.out.println("            6 - Sacar                                ");
			System.out.println("            7 - Depositar                            ");
			System.out.println("            8 - Transferir valores entre Contas      ");
			System.out.println("            9 - Sair                                 ");
			System.out.println("                                                     "); 
			System.out.println("*****************************************************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                     ");
			opcao = leia.nextInt();
			
			try {
				opcao = leia.nextInt();
			}catch(InputMismatchException e){
				System.out.println("\nDigite valores inteiros!");
				leia.nextLine();
				opcao=0;
			}
			
			if(opcao == 9) {
				System.out.println(Cores.TEXT_WHITE_BOLD +"\nBanco do Brazil com Z - O seu futuro começa aqui!");
				leia.close();
				System.exit(0);
			}
			
			switch(opcao) {
			case 1:
				System.out.println(Cores.TEXT_WHITE + "\n Criar Conta");
				System.out.println("Digite o numero da Agência: ");
				agencia = leia.nextInt();
				System.out.println("Digite o Nome do Titular: ");
				leia.skip("\\R?");
				titular = leia.nextLine();
				
				do {
					System.out.println("Digite o Tipo da Conta (1-CC ou 2-CP): ");
					tipo = leia.nextInt();
				}while(tipo < 1 && tipo > 2);
				
				System.out.println("Digite o Slado da conta (R$): ");
				saldo = leia.nextFloat();
				switch(tipo) {
				case 1 -> {
					System.out.println("Digite o limite de Crédito (R$): ");
					limite = leia.nextFloat();
					contas.cadastrar(new ContaCorrente(contas.gerarNumero(),agencia,tipo,titular,saldo,limite));
				}
				case 2 -> {
					System.out.println("Digite o dia do Aniversario da Conta: ");
					aniversario  = leia.nextInt();
					contas.cadastrar(new ContaPoupanca(contas.gerarNumero(),agencia,tipo,titular,saldo,aniversario));
				}
				}
				break;
                
			case 2:
				System.out.println(Cores.TEXT_WHITE + "\n Listar todas as Contas");
				contas.listarTodas();
				
                 break;
			case 3:
				System.out.println(Cores.TEXT_WHITE + "\n Buscar Conta por número");
				System.out.println("\n Digite o numero da conta: ");
				numero = leia.nextInt();
				contas.procurarPorNumero(numero);
				break;
			case 4:
				System.out.println(Cores.TEXT_WHITE + "\n Atualizar dados da Conta");
				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();
				var buscaConta = contas.buscarNaCollection(numero);
				if (buscaConta != null) {
					tipo = buscaConta.getTipo();
					
					System.out.println("Digite o Numero da Agência: ");
					agencia = leia.nextInt();
					System.out.println("Digite o Nome do titular: ");
					leia.skip("\\R?");
					titular = leia.nextLine();
					System.out.println("Digite o Saldo da conta(R$): ");
					saldo = leia.nextFloat();
					
					switch(tipo) {
					case 1 -> {
						System.out.println("Digite o Limite de Crédito (R$): ");
						limite = leia.nextFloat();
						
						contas.atualizar(new ContaCorrente(numero,agencia,tipo,titular,saldo,limite));
					}
					case 2 -> {
						System.out.println("Digite o dia do Aniversário da Conta: ");
						aniversario = leia.nextInt();
						
						contas.atualizar(new ContaPoupanca(numero,agencia,tipo,titular,saldo,aniversario));
					}
					default -> {
						System.out.println("Tipo de conta inválido!");
					}
					}						
				}else {
					System.out.println("A conta não foi encontrada!");
				}
				
				break;
			case 5:
				System.out.println(Cores.TEXT_WHITE + "\n Apagar Conta");
				
				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();
				
				contas.deletar(numero);
                 break;
			case 6:
				System.out.println(Cores.TEXT_WHITE + "\n Sacar");
				
				break;
             case 7:
				System.out.println(Cores.TEXT_WHITE + "\n Depositar");
				
				break;
             case 8:
				System.out.println(Cores.TEXT_WHITE + "\n Transferir");
				
				break;
			default:
				System.out.println(Cores.TEXT_WHITE + "\nOpção Inválida");
                 break;
			}
        }
	}

}