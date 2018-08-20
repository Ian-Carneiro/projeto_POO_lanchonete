package com.lanchonete.model;

import java.util.ArrayList;

public class GerenciarMenu {
	private ArrayList<Produto> produtos;
	
	public GerenciarMenu() {
		produtos = new ArrayList<>();
	}
	
	//buscar o indice do produto na lista pelo seu codigo
	public int buscarProduto(int codigo) {
		for(int i = 0; i<produtos.size();i++) {
			if(produtos.get(i).getCodigo() == codigo) {
				return i;
			}
		}
		return -1;
	}
	
	//buscar e retornar o produto
	public Produto buscarProduto(Produto produto) {
		for(Produto p: produtos) {
			if(p.equals(produto))
				return p;
		}
		return null;
	}
	
	public boolean adicionarProduto(Produto produto) {
		if(buscarProduto(produto.getCodigo()) >=0 ) {
			return false;//Produto com codigo ja cadastrado
		}
		return produtos.add(produto);
	}
	
	public boolean excluirProduto(int codigo) {
		if(buscarProduto(codigo)<0)//verifica se o produto existe
			return false;
		return produtos.remove(produtos.get(buscarProduto(codigo)));
	}
	
	//recebe o codigo do produto que sera editado e um novo produto com informações novas do produto
	public boolean editarProduto(int codigo, Produto produto) {
		if(buscarProduto(codigo)<0)
			return false;
		produto.setCodigo(codigo);//garante que o produto editado(novo produto) tenha o mesmo codigo
		if(excluirProduto(codigo)) {//se excluit o produto antigo sera adicionado o novo(editado)
			adicionarProduto(produto);//adicionando o produto editado
			return true;
		}
		return false;
	}
}
