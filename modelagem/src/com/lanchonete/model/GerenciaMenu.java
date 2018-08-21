package com.lanchonete.model;

import java.util.ArrayList;

public class GerenciaMenu {
	private ArrayList<Produto> produtos;
	
	public GerenciaMenu() {
		produtos = new ArrayList<>();
	}
	
	//buscar o indice do produto na lista pelo seu codigo
	int buscarProduto(int codigo) {
		if(produtos.isEmpty())
			return -1;
		for(int i = 0; i<produtos.size();i++) {
			if(produtos.get(i).getCodigo() == codigo) {
				return i;
			}
		}
		return -1;
	}
	
	public ArrayList<Produto> listarProdutos(){
		if(produtos.isEmpty())
			return null;
		return produtos;		
	}
	
    //buscar e retornar o produto
	public Produto EscolherProduto(int codigo) {
		for(Produto p: produtos) {
			if(p.getCodigo() == codigo)
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