package com.lanchonete.view;
import java.time.LocalDate;
import java.util.Scanner;
import com.lanchonete.model.*;

public class App {
	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);
		Cozinha c = new Cozinha();
		GerenciaMesa gm = new GerenciaMesa();
		GerenciaMenu gMenu = new GerenciaMenu();
//---------------------------------------------------------------------------------------------------------
		gMenu.adicionarProduto(new Produto(001, "Pão", "é um Pão", 0.50f));
		gMenu.adicionarProduto(new Produto(002, "Café", "é um Café", 0.30f));
		gMenu.adicionarProduto(new Produto(003, "Batata Frita", "são Batatas Fritas", 3.0f));
		gMenu.adicionarProduto(new Produto(004, "X-burger", "é um X-Burger", 6.0f));
		gMenu.adicionarProduto(new Produto(005, "Refrigerante", "é um Refrigerante 300ml", 1.0f));
//--------------------------------------------------------------------------------------------------------- 
		int i, codProduto, quantProduto, mesa, numeroPedido, dia1=0, mes1=0, ano1=0, dia2=0, mes2=0, ano2=0;
		float precoProduto;
		String nomeProd, descricaoProd, usuario, senha;
		boolean interruptor = true;
		
//---------------------------------------------------------------------------------------------------------		
		while(interruptor) {
			System.out.print("----------------TELA INICIAL--------------\n"
					+ "Usuário:");
			usuario = ler.nextLine();
			System.out.print("Senha:");
			senha = ler.nextLine();
			System.out.print("1-Autenticar        2-Criar nova conta        0-Sair\n>>>>>");
			i = ler.nextInt();
//---------------------------------------------------------------------------------------------------------			
			if(i == 1) {
				while(interruptor) {
					System.out.print("1-Cardápio     2-Mesas     3-Minha Conta"
							+ "\n4-Cozinha     5-Gerência     0-Sair\n>>>>");
					i = ler.nextInt();
//---------------------------------------------------------------------------------------------------------------------------------------------------					
					if(i == 1) {
						System.out.print("Gerênciar Menu\n\nLista de Produtos disponiveis:\n");
						for(Produto p:gMenu.listarProdutos()) {
							System.out.println(p);
						}
						System.out.print("Informe o codigo do produto:");
						codProduto = ler.nextInt();
						System.out.println("1-Salvar     2-Excluir     3-Editar     0-Sair");
						i = ler.nextInt();
						ler.nextLine();
						if(i == 1 || i == 3) {
							System.out.print("Informe o nome do produto:");
							nomeProd = ler.nextLine();
							System.out.print("Informe a descrição do produto:");
							descricaoProd = ler.nextLine();
							System.out.print("Informe o preço do produto:");
							precoProduto = ler.nextFloat();
							if(i == 1) {
								System.out.println(gMenu.adicionarProduto(new Produto(codProduto, nomeProd, descricaoProd, precoProduto)));
							}
							else if(i == 3) {
								System.out.println(gMenu.editarProduto(codProduto, new Produto(codProduto, nomeProd, descricaoProd, precoProduto)));
							}
						}
						else if(i == 2) {
							System.out.println(gMenu.excluirProduto(codProduto));
						}	
					}
//--------------------------------------------------------------------------------------------------------------------------------------------------					
					else if(i==2) {
						System.out.print("Informe uma mesa:");
						mesa = ler.nextInt();
						System.out.print("1-Nova comanda     2-Ver pedidos     3-Fazer pedido     4-Encerrar comanda     0-Sair\n>>>>>");
						i = ler.nextInt();
						if(i == 1) {
							System.out.println(gm.novaComanda(mesa));
						}
						else if(i == 2) {
							System.out.println(gm.verPedidos(mesa));
						}
						else if(i == 3) {
							do {
								System.out.println();
								quantProduto = ler.nextInt();
								for(Produto p:gMenu.listarProdutos()) {
									System.out.println(p);
								}
								System.out.println();
								codProduto = ler.nextInt();
								System.out.println(gm.fazerPedido(mesa, new Pedido(quantProduto, gMenu.EscolherProduto(codProduto)), c)
										+"\nNovo Pedido para a mesa:"+mesa+"\n1-Sim     0-Não");
							}while(ler.nextInt()==1);
						}
						else if(i == 4) {
							System.out.println(gm.encerrarComanda(mesa));
						}
					}
//---------------------------------------------------------------------------------------------------------------------------------------------------
					else if(i == 3) {
						
					}
//---------------------------------------------------------------------------------------------------------------------------------------------------
					else if(i == 4) {
						System.out.print(c.visualizarPedidos()+"\nNumero do pedido(informe 0 para sair):");
						numeroPedido = ler.nextInt();
						if(numeroPedido == 0) {
							break;
						}
						System.out.println(c.atender(numeroPedido, gm));
					}
					else if(i == 5){
						System.out.println("1-Ver Comandas em um determinado Periodo     2-Ver Valor obtido em Determinado Periodo     0-Sair");
						i = ler.nextInt();
						if(i == 1 || i == 2) {
							System.out.println("Informe os valores da data de inicio:");
							System.out.print("Informe o ano:");
							ano1 = ler.nextInt();
							System.out.print("Informe o mês:");
							mes1 = ler.nextInt();
							System.out.print("Informe o dia do mês:");
							dia1 = ler.nextInt();
							System.out.println("Informe os valores da data de fim:");
							System.out.print("Informe o ano:");
							ano2 = ler.nextInt();
							System.out.print("Informe o mês:");
							mes2 = ler.nextInt();
							System.out.print("Informe o dia do mês:");
							dia2= ler.nextInt();
						}
						if(i == 1) {
							System.out.println(Gerencia.listarComandasEntre(LocalDate.of(ano1, mes1, dia1), LocalDate.of(ano2, mes2, dia2)));
						}
						else if(i == 2){
							System.out.println(Gerencia.lucroTotalEntre(LocalDate.of(ano1, mes1, dia1), LocalDate.of(ano2, mes2, dia2)));
						}
					}
				}
			}
//---------------------------------------------------------------------------------------------------------			
			else if(i == 2) {
				
				
				
			}
			else {
			
				interruptor = false;
				
			}
			System.out.println("Fim do Programa :)");
		}
	}
}
