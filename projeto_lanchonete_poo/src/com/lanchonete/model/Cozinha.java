package com.lanchonete.model;

import java.util.ArrayList;
import java.util.List;

import com.lanchonete.control.GerenciaMesa;
/**
 * A classe Cozinha modela a entidade cozinha.
 * @author Leanderson
 * @since 1.10
 * @see java.util.ArrayList
 * @see com.lanchonete.model.Pedido
 * @version 1.0 
 * */
public class Cozinha {
	private List<Pedido> pedidos;
	/**
	 * Inicializa a estrutura ArrayList sem nenhum dado.
	 * */
	public Cozinha(){
		pedidos = new ArrayList<>();
	}
	/**
	 * Adiciona um pedido a lista.
	 * @param p o pedido que será adicionado na lista.
	 * @return true ou false.
	 * */
	public boolean addPedido(Pedido p) {
		return pedidos.add(p);
	}
	/**
	 * Remove um pedido buscando pelo seu numero.
	 * @param numeroPedido o número do pedido.
	 * @return true ou false.
	 * */
	public boolean removerPedido(int numeroPedido) {// relaciona esta classe com GerenciaMesa
		// não precisa de verificação porque ela é feita na classe GerenciaMesa(só ela usará esta função) 
		return pedidos.remove(pedidos.get(buscar(numeroPedido)));// pedidos.get(buscar(numeroPedido) retorna um objeto em uma determinada posição que é removido da lista 
	}
	
	private int buscar(int numeroPedido) {
		if(!pedidos.isEmpty()) {
			for(int i = 0; i<pedidos.size(); i++) {
				if(pedidos.get(i).getNumeroPedido() == numeroPedido) {
					return i;
				}
			}
		}
		return -1;
	}
	/**
	 * Atende um pedido buscado pelo seu código.
	 * @param numeroPedido o número do pedido. 
	 * @param gm o objeto que está gerenciando a mesa que fez o pedido.
	 * @return true ou false.
	 * */
	public boolean atender(int numeroPedido, GerenciaMesa gm) {
		if(buscar(numeroPedido)==-1) {
			return false;
		}
		int mesa = pedidos.get(buscar(numeroPedido)).getMesa();//descobre a mesa de um determinado pedido
		gm.getComanda(mesa).getPedido(numeroPedido).mudarStatus();// muda o status de não atendido para atendido
		return pedidos.remove(pedidos.get(buscar(numeroPedido)));//remove da cozinha
	}
	/**
	 * Visualiza todos os pedidos não atendidos pela cozinha.
	 * @return String contendo todas as informações dos pedidos ou nenhuma.
	 * */
	public String visualizarPedidos() {
		String s = "";
		for(Pedido p : pedidos) {
			s+="Mesa:"+ p.getMesa()+ "           N°:" +p.getNumeroPedido() + "\n" + p.toString()+"______________________________\n";
		}
		return s;
	}
}
