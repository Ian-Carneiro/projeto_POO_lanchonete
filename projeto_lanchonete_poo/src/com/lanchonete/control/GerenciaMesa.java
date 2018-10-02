package com.lanchonete.control;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.lanchonete.dao.DaoListGenerico;
import com.lanchonete.model.Comanda;
import com.lanchonete.model.Cozinha;
import com.lanchonete.model.Pedido;

/**
 * Classe que controla as mesas da Lanchonete
 * @author Ian
 * @since 1.10
 * @see Comanda
 * @see Gerencia
 * @see Pedido
 * @version 1.0
 **/
public class GerenciaMesa{
	
<<<<<<< HEAD
	public static List<Comanda> mesas = new ArrayList<>();
=======
	private static ArrayList<Comanda> mesas = new ArrayList<>();
	
>>>>>>> 96a4384544c177a3a53b423974cfb10b003a2096

	/**
	 * Cria uma nova mesa na lista de mesas da classe.
	 * @param mesa recebe o valor <b>único</b> da nova mesa que será adicionada
	 * @return true se a mesa for inserida na lista ou false se a mesa não for inserida.
	 **/
<<<<<<< HEAD
	public static boolean novaComanda(int mesa) {
=======
	public static boolean novaComanda(int mesa) throws FileNotFoundException, ClassNotFoundException, IOException {
>>>>>>> 96a4384544c177a3a53b423974cfb10b003a2096
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
<<<<<<< HEAD
	public static boolean encerrarComanda(int mesa) {
=======
	public static boolean encerrarComanda(int mesa) throws FileNotFoundException, ClassNotFoundException, IOException {
>>>>>>> 96a4384544c177a3a53b423974cfb10b003a2096
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
<<<<<<< HEAD
	public static boolean fazerPedido(int mesa, Pedido p) {
		if(buscar(mesa)!=-1 && p.getProduto()!=null) {
			Cozinha.addPedido(p); // adiciona à cozinha                            
			return mesas.get(buscar(mesa)).addPedido(p);
=======
	public static boolean fazerPedido(int mesa, Pedido p) throws FileNotFoundException, ClassNotFoundException, IOException {
		if(buscar(mesa)!=-1 && p.getProduto()!=null) {
			Cozinha.addPedido(p); // adiciona à cozinha                            
			mesas.get(buscar(mesa)).addPedido(p);
			return true;
>>>>>>> 96a4384544c177a3a53b423974cfb10b003a2096
		}
		Pedido.setContadorPedidos(Pedido.getContadorPedidos()-1);// decrementa o contador de pedido pois o pedido p não pode ser feito então ele não sera contado com os demais
		return false;
	}
	
	/**
	 * Mostra todos os pedidos de uma mesa.
	 * @param mesa valor <b>único</b> da mesa que contem os pedidos.
	 * @return String dos pedidos da mesa.
	 **/
<<<<<<< HEAD
	public static String verPedidos(int mesa)  {// retorna uma string com todos os pedidos de uma determinada comanda
=======
	public static String verPedidos(int mesa) throws FileNotFoundException, ClassNotFoundException, IOException {// retorna uma string com todos os pedidos de uma determinada comanda
>>>>>>> 96a4384544c177a3a53b423974cfb10b003a2096
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
<<<<<<< HEAD
	public static boolean modificarPedido(int mesa, Pedido p, int numeroPedido) {
=======
	public static boolean modificarPedido(int mesa, Pedido p, int numeroPedido) throws FileNotFoundException, ClassNotFoundException, IOException {
>>>>>>> 96a4384544c177a3a53b423974cfb10b003a2096
		if(ehNumeroPedidoValido(mesa, numeroPedido)) {// verifica se o numero do pedido existe (tambem verifica se a mesa existe) 
			if(!(mesas.get(buscar(mesa)).getPedido(numeroPedido).isAtendido())) {//verifica se ele já não foi atendido
				Pedido.setContadorPedidos(Pedido.getContadorPedidos()-1);//pelo fato de ter sido necessário instanciar um novo pedido, so para modificar um já existente, é decrementado o contador de pedidos
				p.setNumeroPedido(numeroPedido); // o pedido novo, que servirá para modificar o antigo, tem seu numero definido para o do pedido antigo 
				excluirPedido(mesa, numeroPedido);//o pedido antigo é excluido da comanda e tambem da cozinha
				return fazerPedido(mesa, p);//o novo pedido é adicionado a comanda e a cozinha, substituindo o antigo
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
<<<<<<< HEAD
	public static boolean excluirPedido(int mesa, int numeroPedido) {
=======
	public static boolean excluirPedido(int mesa, int numeroPedido) throws FileNotFoundException, ClassNotFoundException, IOException {
>>>>>>> 96a4384544c177a3a53b423974cfb10b003a2096
		if(ehNumeroPedidoValido(mesa, numeroPedido)) {// verifica se o numero do pedido existe (tambem verifica se a mesa existe) 
			if(!(mesas.get(buscar(mesa)).getPedido(numeroPedido).isAtendido())) {//verifica se ele já não foi atendido
				Cozinha.removerPedido(numeroPedido); //remover da lista da cozinha
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
<<<<<<< HEAD
	static int buscar(int mesa){
=======
	static int buscar(int mesa) throws FileNotFoundException, ClassNotFoundException, IOException {
>>>>>>> 96a4384544c177a3a53b423974cfb10b003a2096
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
<<<<<<< HEAD
	public static Comanda getComanda(int mesa) {//retorna a (tudo da)comanda de uma mesa especifica (relaciona esta classe com Cozinha) /////não confundir com getListaPedido() que retorna a lista de todos os pedidos da comanda mas não retorna data, numero e mesa 
=======
	public static Comanda getComanda(int mesa) throws FileNotFoundException, ClassNotFoundException, IOException{//retorna a (tudo da)comanda de uma mesa especifica (relaciona esta classe com Cozinha) /////não confundir com getListaPedido() que retorna a lista de todos os pedidos da comanda mas não retorna data, numero e mesa 
>>>>>>> 96a4384544c177a3a53b423974cfb10b003a2096
		if(buscar(mesa)!=-1)
			return mesas.get(buscar(mesa));
		return null;
	}
	
	/**
	 * Verfica se a mesa existe juntamente com um pedido.
	 * @param mesa recebe o valor <b>único</b> da mesa que será buscada.
	 * @param numeroPedido recebe o valor <b>único</b> do pedido que será buscado.
	 * @return true caso a mesa exista e o pedido esteja na mesa ou false caso não seja encontrado o pedido na mesa ou a própria mesa. 
	 **/
<<<<<<< HEAD
	private static boolean ehNumeroPedidoValido(int mesa, int numeroPedido) { // verifica se o numero do pedido e a mesa existem
=======
	private static boolean ehNumeroPedidoValido(int mesa, int numeroPedido) throws FileNotFoundException, ClassNotFoundException, IOException { // verifica se o numero do pedido e a mesa existem
>>>>>>> 96a4384544c177a3a53b423974cfb10b003a2096
		if(buscar(mesa)!=-1) {
			return mesas.get(buscar(mesa)).buscarPedido(numeroPedido)!=-1; // retorna o valor lógico da comparação
		}
		return false;
	}
}
