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
		GerenciaUsuario gu = new GerenciaUsuario();

//-------------------------------------------------------------------------------------------------------------------------------------------------
		gMenu.adicionarProduto(new Produto(001, "Pão", "é um Pão", 0.50f));
		gMenu.adicionarProduto(new Produto(002, "Café", "é um Café", 0.30f));
		gMenu.adicionarProduto(new Produto(003, "Batata Frita", "são Batatas Fritas", 3.0f));
		gMenu.adicionarProduto(new Produto(004, "X-burger", "é um X-Burger", 6.0f));
		gMenu.adicionarProduto(new Produto(005, "Refrigerante", "é um Refrigerante 300ml", 1.0f));
//-------------------------------------------------------------------------------------------------------------------------------------------------
		int i, codProduto = 0, quantProduto, mesa, numeroPedido;
		float precoProduto;
		String nomeProd, descricaoProd, usuario, senha;
		boolean interruptor = true;
		LocalDate ld1 = null, ld2 = null;
//-------------------------------------------------------------------------------------------------------------------------------------------------
		//usuario >>>> master@gmail.com
		//senha >>>>>> 123
		while(interruptor) {
			System.out.print("----------------TELA INICIAL--------------\n"
					+ "Usuário(email):");
			usuario = ler.next();
			System.out.print("Senha:");
			senha = ler.next();
			System.out.print("1-Autenticar        2-Criar nova conta        0-Voltar\n>>>>>");
			i = ler.nextInt();
//---------------------------------------------------------------------------------------------------------------------------------------------------	
			if(i == 1 && gu.isAutenticado(usuario, senha)) {
				while(interruptor) {
					System.out.print("1-Cardápio     2-Mesas     3-Minha Conta"
							+ "\n4-Cozinha     5-Gerência     0-Sair\n>>>>");
					i = ler.nextInt();
//------------------------------------------------------------------------------------------------					
					if(i == 1) {
						System.out.print("Gerênciar Menu\n\nLista de Produtos disponiveis:\n");
						for(Produto p:gMenu.listarProdutos()) {
							System.out.println(p);
						}
						System.out.print("1-Salvar     2-Excluir     3-Editar     0-Sair\n>>>>");
						i = ler.nextInt();
						if(i>=1 && i<=3) {
							System.out.print("Informe o codigo do produto:");
							codProduto = ler.nextInt();
						}
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
//------------------------------------------------------------------------------------------------					
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
								for(Produto p:gMenu.listarProdutos()) {
									System.out.println(p);
								}
								System.out.print("Informe o codigo do produto:");
								codProduto = ler.nextInt();
								System.out.print("Escolha a quantidade:");
								quantProduto = ler.nextInt();
								System.out.println(gm.fazerPedido(mesa, new Pedido(quantProduto, gMenu.EscolherProduto(codProduto)), c)
										+"\nNovo Pedido para a mesa "+mesa+"?\n1-Sim     0-Não");
							}while(ler.nextInt()==1);
						}
						else if(i == 4) {
							System.out.println(gm.encerrarComanda(mesa));
						}
					}
//------------------------------------------------------------------------------------------------
					else if(i == 3) {
						System.out.print("Informe\n1-Editar Usuário     2-Excluir Usuário     0-Sair\n>>>>");
						i = ler.nextInt();
						if(i==1 || i==2) {
							System.out.print("Informe o E-mail que deseja alterar:");
							usuario = ler.next();
						}
						if(i==1) {
							System.out.println(gu.editarUsuario(usuario, construirFuncionario(ler)));
						}
						else if(i == 2) {
							System.out.println(gu.removerLogin(usuario));
						}
					}
//------------------------------------------------------------------------------------------------
					else if(i == 4) {
						System.out.print(c.visualizarPedidos()+"\nNumero do pedido(informe 0 para sair):");
						numeroPedido = ler.nextInt();
						if(numeroPedido > 0) {
							System.out.println(c.atender(numeroPedido, gm));
						}
					}
					else if(i == 5){
						System.out.println("1-Ver Comandas em um determinado Periodo     2-Ver Valor obtido em Determinado Periodo     0-Sair");
						i = ler.nextInt();
						if(i == 1 || i == 2) {
							System.out.println("Informe os valores da data de inicio:");
							ld1 = construirData(ler);
							System.out.println("Informe os valores da data de fim:");
							ld2 = construirData(ler);
						}
						if(i == 1) {
							System.out.println(Gerencia.listarComandasEntre(ld1, ld2));
						}
						else if(i == 2){
							System.out.println(Gerencia.lucroTotalEntre(ld1, ld2));
						}
					}
					else {
						interruptor=false;
					}
				}
			}
//----------------------------------------------------------------------------------------------------------------------------------------------------	
			else if(i == 2 && gu.isAutenticado(usuario, senha)) {
				gu.adicionarLogin(construirFuncionario(ler));
			}
//---------------------------------------------------------------------------------------------------------------------------------------------------			
			interruptor = true;
		}
//---------------------------------------------------------------------------------------------------------------------------------------------------
	}
	static Usuario construirFuncionario(Scanner ler) {
		System.out.print("Informe o CPF:");
		String cpf = ler.next();
		System.out.print("Informe o nome:");
		ler.nextLine();
		String nome = ler.nextLine();
		System.out.print("Informe o E-mail:");
		String email = ler.next();
		System.out.print("Informe a senha:");
		String senha = ler.next();
		System.out.print("Informe o telefone:");
		ler.nextLine();
		String telefone = ler.nextLine();
		System.out.println("Informe a data de Nascimento:");
		LocalDate ld = construirData(ler);
		System.out.print("1-Atendimento\n2-Cozinha\n3-Caixa\n4-Gerencia\nInforme o setor:");
		int setor = ler.nextInt();
		if(setor>4 || setor<1) {
			return null;
		}
		Setor s = null;
		switch(setor) {
		case 1:s = Setor.ATENDIMENTO;break;
		case 2:s = Setor.COZINHA;break;
		case 3:s = Setor.CAIXA;break;
		case 4:s = Setor.GERENCIA;break;
		}
		return new Usuario(cpf, nome, email, senha, telefone, ld, s);
	} 
	static LocalDate construirData(Scanner ler) {
		System.out.print("Informe o ano:");
		int ano = ler.nextInt();
		System.out.print("Informe o mês:");
		int mes = ler.nextInt();
		System.out.print("Informe o dia do mês:");
		int dia = ler.nextInt();
		return LocalDate.of(ano, mes, dia);
	}
}
