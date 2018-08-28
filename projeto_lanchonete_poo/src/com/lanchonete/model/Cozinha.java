package com.lanchonete.model;

import java.util.ArrayList;
import java.util.List;

import com.lanchonete.control.GerenciaMesa;

public class Cozinha {
	private List<Pedido> pedidos;
	public Cozinha(){
		pedidos = new ArrayList<>();
	}
	public boolean addPedido(Pedido p) {
		return pedidos.add(p);
	}
	public boolean removerPedido(int numeroPedido) {// relaciona esta classe com GerenciaMesa
		// não precisa de verificação porque ela é feita na classe GerenciaMesa(só ela usará esta função) 
		return pedidos.remove(pedidos.get(buscar(numeroPedido)));// pedidos.get(buscar(numeroPedido) retorna um objeto em uma determinada posição que é removido da lista 
	}
	int buscar(int numeroPedido) {
		if(!pedidos.isEmpty()) {
			for(int i = 0; i<pedidos.size(); i++) {
				if(pedidos.get(i).getNumeroPedido() == numeroPedido) {
					return i;
				}
			}
		}
		return -1;
	}
	public boolean atender(int numeroPedido, GerenciaMesa gm) {
		if(buscar(numeroPedido)==-1) {
			return false;
		}
		int mesa = pedidos.get(buscar(numeroPedido)).getMesa();//descobre a mesa de um determinado pedido
		gm.getComanda(mesa).getPedido(numeroPedido).mudarStatus();// muda o status de não atendido para atendido
		return pedidos.remove(pedidos.get(buscar(numeroPedido)));//remove da cozinha
	}
	public String visualizarPedidos() {
		String s = "";
		for(Pedido p : pedidos) {
			s+="Mesa:"+ p.getMesa()+ "           N°:" +p.getNumeroPedido() + "\n" + p.toString()+"______________________________\n";
		}
		return s;
	}
}
