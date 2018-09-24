package com.lanchonete.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Classe que modela um Pedido.
 * @author Leanderson
 * @since 1.10
 * @see com.lanchonete.control.GerenciaMenu
 * @version 1.0
 **/
public class Pedido {
	private int quantidade;
	private Produto produto;
	private static int id;
	private int numeroPedido;
	private int mesa;
	private final LocalDate data;
	private final LocalTime hora;
	private boolean atendido;
	
	/**
	 * Inicia so valores do Pedido
	 * @param quantidade recebe a quantidade de produtos deste pedido.
	 * @param produto recebe o produto deste pedido.
	 **/
	public Pedido(int quantidade, Produto produto) {
		atendido = false;
		data = LocalDate.now();
		hora = LocalTime.now();
		numeroPedido = ++id;
		this.produto = produto;
		this.quantidade = quantidade;
	}
	public int getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(int i) {//define o numero de um pedido específico
		numeroPedido = i;
	}
	public static void setContadorPedidos(int i) {//define o contador de pedidos
		id = i;
	}
	public static int getContadorPedidos() {
		return id;
	}
	public int getMesa() {
		return mesa;
	}
	public void setMesa(int mesa) {
		this.mesa = mesa;
	}
	public LocalDate getData() {
		return data;
	}
	public LocalTime getTime() {
		return hora;
	}
	public boolean isAtendido() {
		return atendido;
	}
	public void mudarStatus() {
		atendido = true;
	}	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	/**
	 * Retorna o valor total do pedido. A multiplica��o do pre�� por unidade vezes a quantidade de produtos.
	 **/
	public float getValorTotal() {
		return produto.getPreco() * quantidade;  //retorna a multiplicação do preço unitário do produto pela quantidade de produtos pedidos
	}
	public Produto getProduto() {
		return produto;
	}
	
	/**
	 * Retorna o toString do Pedido
	 * @return String todas as informa��es do pedido.
	 **/
	@Override
	public String toString() {
		String s = isAtendido()? "Atendido":"Ñ atendido";
		return quantidade+" - "+produto.getNome()+" - Subtotal: "+getValorTotal()+" -- n°:"+getNumeroPedido()+" ==> "+s+"\n";
	}
}
