package com.lanchonete.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
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
public class GerenciaMenu extends DaoListGenerico<Produto>{
	/**
	 * Inicializa a estrutura ArrayList sem conter valores 
	 * */
	private static File file = new File("Produto");
	
	
	//buscar o indice do produto na lista pelo seu codigo
	private static int buscarProduto(int codigo) throws FileNotFoundException, ClassNotFoundException, IOException {
		ArrayList<Produto> produtos = getEstrutura(file);
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
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 * */
	public static ArrayList<Produto> listarProdutos() throws FileNotFoundException, ClassNotFoundException, IOException{
		ArrayList<Produto> produtos = getEstrutura(file);
		if(produtos.isEmpty())
			return null;
		return produtos;		
	}
	/**
	 * Seleciona um produto específico pelo seu código.
	 * @param codigo o código para especificar o produto.
	 * @return um produto ou null 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 * */
	public static Produto EscolherProduto(int codigo) throws FileNotFoundException, ClassNotFoundException, IOException {
		ArrayList<Produto> produtos = (getEstrutura(file));
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
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 * */
	public static boolean adicionarProduto(Produto produto) throws FileNotFoundException, ClassNotFoundException, IOException{
		ArrayList<Produto> produtos = (getEstrutura(file));
		if(buscarProduto(produto.getCodigo()) >=0 ) {
			return false;//Produto com codigo ja cadastrado
		}
		produtos.add(produto);
		push(produtos, file);
		return true;
	}
	/**
	 * Exclui o produto do menu(cardápio).
	 * @param codigo o código para especificar o produto que será excluido.
	 * @return true ou false.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 * */
	public static boolean excluirProduto(int codigo) throws FileNotFoundException, ClassNotFoundException, IOException {
		ArrayList<Produto> produtos = (getEstrutura(file));
		if(buscarProduto(codigo)<0)//verifica se o produto existe
			return false;
		produtos.remove(produtos.get(buscarProduto(codigo)));
		push(produtos, file);
		return true;
	}
	
	/**
	 * Edita um produto do menu(cardápio).
	 * @param codigo o código para especificar o produto que será editado.
	 * @param produto o produto que substituirá o atual.
	 * @return true ou false.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 * */
	public static boolean editarProduto(int codigo, Produto produto) throws FileNotFoundException, ClassNotFoundException, IOException {
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