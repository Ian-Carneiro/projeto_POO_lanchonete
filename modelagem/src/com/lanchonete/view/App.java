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
		gMenu.adicionarProduto(new Produto(001, "Pão", "é um Pão", 0.50f));
		gMenu.adicionarProduto(new Produto(002, "Café", "é um Café", 0.30f));
		gMenu.adicionarProduto(new Produto(003, "Batata Frita", "são Batatas Fritas", 3.0f));
		gMenu.adicionarProduto(new Produto(004, "X-burger", "é um X-Burger", 6.0f));
		gMenu.adicionarProduto(new Produto(005, "Refrigerante", "é um Refrigerante 300ml", 1.0f));
		
		
		int i = 0, produto = 0, mesa = 0, numeroPedido = 0, quantidade = 0, dia1 = 0, mes1 = 0, ano1 = 0, dia2 = 0, mes2 = 0, ano2 = 0;
		boolean interruptor = true;
		while(interruptor) {
			System.out.print("1-Criar nova comanda\n"
					+ "2-Encerrar comanda\n"
					+ "3-Fazer pedido\n"
					+ "4-Excluir pedido\n"
					+ "5-Modificar pedido\n"
					+ "6-Ver Pedidos da comanda\n"
					+ "7-Atender pedido\n"
					+ "8-Visualizar pedidos não atendidos\n"
					+ "9-Verificar comandas fechadas de um determinado periodo\n"
					+ "10-Verificar lucro por periodo\n"
					+ "qualquer outro valor para sair\n==>");
			i = ler.nextInt();
			if(i<7 && i!=0) {
				System.out.print("Informe o numero da mesa:");
				mesa = ler.nextInt();
			}
			if(i == 4 || i == 5 || i == 7) {
				System.out.print("Informe o numero do pedido:");
				numeroPedido = ler.nextInt();
			}
			if(i == 3 || i == 5) {
				for(Produto p : gMenu.listarProdutos()) {
					System.out.println(p.toString());
				}
				System.out.println();
				System.out.print("Informe o codigo do produto:");
				produto = ler.nextInt();
				System.out.print("Informe a quantidade do mesmo produto:");
				quantidade = ler.nextInt();
			}
			if(i == 9 || i == 10) {
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
			switch(i) {
			case 1:System.out.println(gm.novaComanda(mesa));break;
			case 2:System.out.println(gm.encerrarComanda(mesa));break;
			case 3:System.out.println(gm.fazerPedido(mesa, new Pedido(quantidade, gMenu.EscolherProduto(produto)), c));break;
			case 4:System.out.println(gm.excluirPedido(mesa, numeroPedido, c));break;
			case 5:System.out.println(gm.modificarPedido(mesa, new Pedido(quantidade, gMenu.EscolherProduto(produto)), numeroPedido, c));break;
			case 6:System.out.println(gm.verPedidos(mesa));break;
			case 7:System.out.println(c.atender(numeroPedido, gm));break;
			case 8:System.out.println(c.visualizarPedidos());break;
			case 9:System.out.println(Gerencia.listarComandasEntre(LocalDate.of(ano1, mes1, dia1), LocalDate.of(ano2, mes2, dia2)));break;
			case 10:System.out.println(Gerencia.lucroTotalEntre(LocalDate.of(ano1, mes1, dia1), LocalDate.of(ano2, mes2, dia2)));break;
			default:interruptor = false;
			}
			limpaTelaPlusHiperAdvancedMegaBlasterUltraExtremesBestFunctionEverForever();
		}
		System.out.println("\n\nFim do Programa\n");
		
	}
	public static void limpaTelaPlusHiperAdvancedMegaBlasterUltraExtremesBestFunctionEverForever() {
		for(int i = 0; i<100; i++) {
			System.out.println();
		}
	}
}
