package com.lanchonete.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class Comanda {
	private List<Pedido> comanda; 
	private int mesa;
	private static int id;
	private int numeroComanda;
	private final LocalDate data;
	public Comanda(int mesa) {
		numeroComanda = ++id;
		comanda = new ArrayList<>();
		this.mesa = mesa;
		data = LocalDate.now();
	}
	int getTamanho() {
		return comanda.size();
	}
	public LocalDate getData() {
		return data;
	}
	public int getMesa() {
		return mesa;
	}
	public int buscarPedido(int numeroPedido) {// busca um pedido pelo seu numero na comanda retornando o indice da lista(comanda)
		if(!comanda.isEmpty()) {
			for(int i=0;i<comanda.size();i++) {
				if(comanda.get(i).getNumeroPedido() == numeroPedido) {
					return i;
				}
			}
		}
		return -1;
	}
	public boolean addPedido(Pedido p) {
		p.setMesa(mesa);//define o numero da mesa do pedido(que é o mesmo da comanda) 
		return comanda.add(p);
	}
	public boolean removePedido(int numeroPedido) {
		if(comanda.remove(buscarPedido(numeroPedido))!=null) {
			return true;
		}
		return false;
	}
	public float valorTotal() {//valor total da comanda
		float total = 0;
		for(Pedido p : comanda) {
			total += p.getValorTotal();
		}
		return total;
	}
	public Pedido getPedido(int numeroPedido) {// retorna um pedido específico da comanda (relaciona esta classe com GerenciaComanda)
		return comanda.get(buscarPedido(numeroPedido));
	}
	public List<Pedido> getListaPedidos(){// retorna uma lista de todos os pedidos da comanda (relaciona esta classe com GerenciaComanda)
		return comanda;
	}
	@Override
	public String toString() {
		String s = data.toString() + " |Mesa:" + mesa + " |N°:" + numeroComanda +"\n==================================\n" ;
		for(Pedido p : comanda) {
			s+=p.toString();
		}
		return s+"===========TOTAL:"+valorTotal()+" R$===========\n\n";
	} 
}