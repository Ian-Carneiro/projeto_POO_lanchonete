package com.lanchonete.control;
import java.util.ArrayList;
import java.util.List;

import com.lanchonete.model.Comanda;
import com.lanchonete.model.Cozinha;
import com.lanchonete.model.Pedido;

/**
 * Classe que controla as mesas da Lanchonete
 * @author Ian
 * @author Leanderson
 * @since 1.8
 * @see Comanda
 * @see Gerencia
 * @see Pedido
 * @version 1.0
 **/
public class GerenciaMesa {
	private List<Comanda> mesas;
	
	/**
	 * Inicia a lista de mesas da classe GerenciaMesa.
	 **/
	public GerenciaMesa() {
		mesas = new ArrayList<>();
	}
	
	/**
	 * Cria uma nova mesa na lista de mesas da classe.
	 * @param mesa recebe o valor <b>único</b> da nova mesa que será adicionada
	 * @return true se a mesa for inserida na lista ou false se a mesa não for inserida.
	 **/
	public boolean novaComanda(int mesa) {
		if(buscar(mesa)!=-1) {// verifica se a mesa existe ou não 
			return false; // não pode ser aberta duas ou mais comandas na mesma mesa
		}
		return mesas.add(new Comanda(mesa));		
	}
	
	/**
	 * Encerra uma comanda removendo-a da lista e adiciona a mesma na Gerencia.
	 * @param mesa recebe o valor <b>único</b> da mesa que será removida.
	 * @return true se a mesa for encerrada ou false se a mesa não for encerrada.
	 **/
	public boolean encerrarComanda(int mesa) {
		if(buscar(mesa)==-1) {// verifica se a mesa existe ou não
			return false;// não se pode encerrar uma comanda que não existe
		}
		for(Pedido p : getComanda(mesa).getListaPedidos()) {
			if(p.isAtendido() == false) {// não se pode encerrar uma comanda que não tenha sido atendido todos pedidos
				return false;
			}
		}
		return Gerencia.addParaGerencia(mesas.remove(buscar(mesa)));//  adiciona a comanda para gerenciar 
	}
	
	/**
	 * Faz um novo pedido para uma comanda já criada.
	 * @param mesa recebe o valor <b>único</b> da mesa que receberá o novo pedido
	 * @param p recebe o pedido que será atribuido a comanda
	 * @param c recebe a cozinha para adicionar o novo pedido a mesma.
	 * @return true se o pedido for adicionado a comanda e a cozinha ou false se o pedido nao for adicionado.
	 **/
	public boolean fazerPedido(int mesa, Pedido p, Cozinha c) {
		if(buscar(mesa)!=-1 && p.getProduto()!=null) {                                              
			c.addPedido(p); // adiciona à cozinha                            
			return mesas.get(buscar(mesa)).addPedido(p);
		}
		Pedido.setContadorPedidos(Pedido.getContadorPedidos()-1);// decrementa o contador de pedido pois o pedido p não pode ser feito então ele não sera contado com os demais
		return false;
	}
	
	/**
	 * Mostra todos os pedidos de uma mesa.
	 * @param mesa valor <b>único</b> da mesa que contem os pedidos.
	 * @return String dos pedidos da mesa.
	 **/
	public String verPedidos(int mesa) {// retorna uma string com todos os pedidos de uma determinada comanda
		if(buscar(mesa)==-1) {
			return "";
		}
		return mesas.get(buscar(mesa)).toString();
	}
	
	/**
	 * Modifica um pedido existente em uma mesa e na cozinha, caso a mesa esteja aberta.
	 * @param mesa recebe o valor <b>único</b> da mesa que contém o pedido que será alterado.
	 * @param numeroPedido recebe o valor <b>único</b> do pedido que será alterado.
	 * @param c recebe a cozinha que contém o mesmo pedido que será alterado como na mesa.
	 * @return true caso o pedido seja alterado ou false caso o pedido não tenha sido alterado.
	 **/
	public boolean modificarPedido(int mesa, Pedido p, int numeroPedido, Cozinha c) {
		if(ehNumeroPedidoValido(mesa, numeroPedido)) {// verifica se o numero do pedido existe (tambem verifica se a mesa existe) 
			if(!(mesas.get(buscar(mesa)).getPedido(numeroPedido).isAtendido())) {//verifica se ele já não foi atendido
				Pedido.setContadorPedidos(Pedido.getContadorPedidos()-1);//pelo fato de ter sido necessário instanciar um novo pedido, so para modificar um já existente, é decrementado o contador de pedidos
				p.setNumeroPedido(numeroPedido); // o pedido novo, que servirá para modificar o antigo, tem seu numero definido para o do pedido antigo 
				excluirPedido(mesa, numeroPedido, c);//o pedido antigo é excluido da comanda e tambem da cozinha
				fazerPedido(mesa, p, c);//o novo pedido é adicionado a comanda e a cozinha, substituindo o antigo 
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Exclui um pedido existente na mesa e na cozinha.
	 * @param mesa recebe o valor <b>único</b> da mesa que contém o pedido a ser removido.
	 * @param numeroPedido recebe o valor <b>único</b> do pedido que será removido.
	 * @param c recebe a cozinha terá o pedido removido.
	 * @return true caso o pedido seja removido ou false caso não seja removido.
	 **/
	public boolean excluirPedido(int mesa, int numeroPedido, Cozinha c) {
		if(ehNumeroPedidoValido(mesa, numeroPedido)) {// verifica se o numero do pedido existe (tambem verifica se a mesa existe) 
			if(!(mesas.get(buscar(mesa)).getPedido(numeroPedido).isAtendido())) {//verifica se ele já não foi atendido
				c.removerPedido(numeroPedido); //remover da lista da cozinha
				return mesas.get(buscar(mesa)).removePedido(numeroPedido);
			}
		}
		return false;
	}
	
	/**
	 * Busca a mesa na lista de mesas e retorna seu indice.
	 * @param mesa recebe o valor <b>único</b> da mesa que será buscada.
	 * @return número >= 0 caso a mesa seja encontrasa, sendo o número o indice da mesa, ou -1 caso a mesa não seja encontrada.
	 **/
	int buscar(int mesa) {
		if(!(mesas.isEmpty())) {// não se pode buscar comandas se elas não existem
			for(int i = 0;i < mesas.size();i++) {
				if(mesas.get(i).getMesa() == mesa) {
					return i;
				}
			}
		}
		return -1;
	}
	
	/**
	 * Busca a mesa na lista de mesas e a retorna.
	 * @param mesa recebe o valor <b>único</b> da mesa que será buscada.
	 * @return mesa caso seja encontrada ou null caso não exista;
	 **/
	public Comanda getComanda(int mesa){//retorna a (tudo da)comanda de uma mesa especifica (relaciona esta classe com Cozinha) /////não confundir com getListaPedido() que retorna a lista de todos os pedidos da comanda mas não retorna data, numero e mesa 
		return mesas.get(buscar(mesa));
	}
	
	/**
	 * Verfica se a mesa existe juntamente com um pedido.
	 * @param mesa recebe o valor <b>único</b> da mesa que será buscada.
	 * @param numeroPedido recebe o valor <b>único</b> do pedido que será buscado.
	 * @return true caso a mesa exista e o pedido esteja na mesa ou false caso não seja encontrado o pedido na mesa ou a própria mesa.
	 **/
	private boolean ehNumeroPedidoValido(int mesa, int numeroPedido) { // verifica se o numero do pedido e a mesa existem
		if(buscar(mesa)!=-1) {
			return mesas.get(buscar(mesa)).buscarPedido(numeroPedido)!=-1; // retorna o valor lógico da comparação
		}
		return false;
	}
}
