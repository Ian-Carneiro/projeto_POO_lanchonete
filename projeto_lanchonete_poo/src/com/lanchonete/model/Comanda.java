package com.lanchonete.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que modela uma mesa(Comanda) na aplicação.
 * @author Leanderson
 * @since 1.10
 * @see com.lanchonete.control.GerenciaMesa
 * @version 1.0
 **/
public class Comanda {
	private List<Pedido> comanda; 
	private int mesa;
	private static int id;
	private int numeroComanda;
	private final LocalDate data;
	
	/**
	 * Inicia os valores da mesa(Comanda).
	 * @param mesa recebe o valor <b>�nico</b> da nova mesa que ser� criada.
	 **/
	public Comanda(int mesa) {
		numeroComanda = ++id;
		comanda = new ArrayList<>();
		this.mesa = mesa;
		data = LocalDate.now();
	}
	private int getTamanho() {
		return comanda.size();
	}
	public LocalDate getData() {
		return data;
	}
	public int getMesa() {
		return mesa;
	}
	
	/**
	 * Busca o pedido pelo seu n�mero <b>�nico</b> na mesa.
	 * @param numeroPedido recebe o n�mero <b>�nico</b> do pedido.
	 * @return O indice do pedido, >= 0 caso seja encontrado ou -1 caso n�o seja encontrado.
	 **/
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
	
	/**
	 * Adiciona um pedido a mesa.
	 * @param p recebe o novo pedido que ser� adicionado na mesa.
	 * @return true caso o pedido seja adicionado ou false caso n�o seja adicionado.
	 **/
	public boolean addPedido(Pedido p) {
		p.setMesa(mesa);//define o numero da mesa do pedido(que é o mesmo da comanda) 
		return comanda.add(p);
	}
	
	/**
	 * Remove um pedido da mesa.
	 * @param numeroPedido recebe o n�mero <b>�nico</b> do pedido que ser� removido
	 * @return true caso seja removido o pedido ou false caso o pedido n�o seja removido.
	 **/
	public boolean removePedido(int numeroPedido) {
		if(comanda.remove(buscarPedido(numeroPedido))!=null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Calcula o valor total da comanda fazendo o somat�rio do valor total de todos os pedidos.
	 * @return O valor total do somat�rio.
	 **/
	public float valorTotal() {//valor total da comanda
		float total = 0;
		for(Pedido p : comanda) {
			total += p.getValorTotal();
		}
		return total;
	}
	
	/**
	 * Retorna o pedido com o n�mero <b>�nico</b> igual ao que foi passado, caso exista.
	 * @param numeroPedido recebe o n�mero <b>�nico</b> do pedido que ser� removido.
	 * @return O pedido encontrado, caso n�o existe retorna null.
	 **/
	public Pedido getPedido(int numeroPedido) {// retorna um pedido específico da comanda (relaciona esta classe com GerenciaComanda)
		return comanda.get(buscarPedido(numeroPedido));
	}
	
	/**
	 * Retorna a lista de Pedidos j� criados.
	 * @return A lista de pedidos.
	 **/
	public List<Pedido> getListaPedidos(){// retorna uma lista de todos os pedidos da comanda (relaciona esta classe com GerenciaComanda)
		return comanda;
	}
	
	/**
	 * Retorna o toString da classe pedido
	 * @return String das inform��es da lista de pedidos.
	 **/
	@Override
	public String toString() {
		String s = data.toString() + " |Mesa:" + mesa + " |N°:" + numeroComanda +"\n==================================\n" ;
		for(Pedido p : comanda) {
			s+=p.toString();
		}
		return s+"===========TOTAL:"+valorTotal()+" R$===========\n\n";
	} 
}