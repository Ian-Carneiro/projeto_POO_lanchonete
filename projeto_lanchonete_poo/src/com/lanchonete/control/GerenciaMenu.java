
package com.lanchonete.control;

import java.io.File;
import java.util.ArrayList;

import com.lanchonete.model.Produto;
/**
 * A classe GerenciaMenu possui métodos para criar, ler, atualizar e deletar produtos de um cardápio.
 * @author Leanderson
 * @since 1.10
 * @see java.util.ArrayList
 * @see com.lanchonete.model.Produto
 * @version 1.0 
 * */
public class GerenciaMenu extends DaoListGenerico<Produto> {
	/**
	 * Inicializa a estrutura ArrayList sem conter valores 
	 * */
	private static ArrayList<Produto> produtos = new ArrayList<>();
	private static File file = new File("Produto");
	
	//buscar o indice do produto na lista pelo seu codigo
	private static int buscarProduto(int codigo) {
		if(produtos.isEmpty())
			return -1;
		for(int i = 0; i<produtos.size();i++) {
			if(produtos.get(i).getCodigo() == codigo) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * Lista todos os produtos contidos no cardápio.
	 * @return Lista de Produtos ou null. 
	 * */
	public static ArrayList<Produto> listarProdutos(){
		if(produtos.isEmpty())
			return null;
		return produtos;		
	}
	/**
	 * Seleciona um produto específico pelo seu código.
	 * @param codigo o código para especificar o produto.
	 * @return um produto ou null 
	 * */
	public static Produto EscolherProduto(int codigo) {
		for(Produto p: produtos) {
			if(p.getCodigo() == codigo)
				return p;
		}
		return null;
	}
	/**
	 * Adiciona um produto ao menu(cardápio).
	 * @param produto o produto que será adicionado ao menu.
	 * @return true ou false.
	 * */
	public static boolean adicionarProduto(Produto produto) {
		if(buscarProduto(produto.getCodigo()) >=0 ) {
			return false;//Produto com codigo ja cadastrado
		}
		return produtos.add(produto);
	}
	/**
	 * Exclui o produto do menu(cardápio).
	 * @param codigo o código para especificar o produto que será excluido.
	 * @return true ou false.
	 * */
	public static boolean excluirProduto(int codigo) {
		if(buscarProduto(codigo)<0)//verifica se o produto existe
			return false;
		return produtos.remove(produtos.get(buscarProduto(codigo)));
	}
	
	/**
	 * Edita um produto do menu(cardápio).
	 * @param codigo o código para especificar o produto que será editado.
	 * @param produto o produto que substituirá o atual.
	 * @return true ou false.
	 * */
	public static boolean editarProduto(int codigo, Produto produto) {
		if(buscarProduto(codigo)<0)//verifica se o produto existe para ser editado
			return false;
		produto.setCodigo(codigo);//garante que o produto editado(novo produto) tenha o mesmo codigo
		if(excluirProduto(codigo)) {//se excluir o produto antigo sera adicionado o novo(editado)
			adicionarProduto(produto);//adicionando o produto editado
			return true;
		}
		return false;
	}
}