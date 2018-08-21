package com.lanchonete.model;

import java.util.ArrayList;
import java.util.List;

public class GerenciaUsuario {
	private List<Usuario> usuarios;
	
	public GerenciaUsuario() {
		usuarios = new ArrayList<>();
	}
	
	public boolean criarNovaConta(Usuario usuario) {
//		se a funcao de adicionarlogin retornar true o usuario nao existe e pode ser adicionado
		if(Autenticar.adicionarLogin(usuario.getEmail(), usuario.getSenha())) {
			return usuarios.add(usuario);
		}
		return false;		
	}
	
	public boolean excluirUsuario(String email) {
		Usuario u = buscarUsuario(email);
		if(u!=null) {//verifica se o usuario ta cadastrasdo: evitando um remove(null)
			if(Autenticar.removerLogin(u.getEmail(), u.getSenha()))//remove o login do usuario que sera removido
				return usuarios.remove(buscarUsuario(email));//remove o usuario
		}
		return false;
	}
	
	public boolean editarUsuario(String email, Usuario usuario) {
		if(buscarUsuario(email)!=null) {//verifica se so usuario que vai ser editado existe
			excluirUsuario(email);//apaga o usuario antigo
			return criarNovaConta(usuario);//adiciona o usuario editado
		}
		return false;
	}
	
	public boolean autenticarUsuario(String email) {
		Usuario u = buscarUsuario(email);
		if(u!=null) {//verifica se o usuario ta cadastrado
			if(Autenticar.isAutenticado(u.getEmail(), u.getSenha()))//autentica usuario
				return true;
		}
		return false;
	}
	
//	busca o funcionario pelo email retorna null ou o usuario
	public Usuario buscarUsuario(String email) {
		if(usuarios.isEmpty())
			return null;
		for(Usuario u : usuarios) {
			if(u.getEmail() == email)
				return u;
		}
		return null;
	}
	
	
	
}
