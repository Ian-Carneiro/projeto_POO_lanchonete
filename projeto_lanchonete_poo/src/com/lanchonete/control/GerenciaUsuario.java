package com.lanchonete.control;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.lanchonete.model.Setor;
import com.lanchonete.model.Usuario;

public class GerenciaUsuario {
	private Map<String, Usuario> usuarios;
	
	public GerenciaUsuario() {
		usuarios = new HashMap<>();
		adicionarLogin(new Usuario("111.111.111-11", "master", "master@gmail.com", "123", "9999999999", LocalDate.of(2018, 1, 1), Setor.ATENDIMENTO));
		//login master
	}
	
	public boolean adicionarLogin(Usuario usuario) {
		if(buscarUsuario(usuario.getEmail())==null) {//verifica se ja nao existe o usuario
			usuarios.put(usuario.getEmail(), usuario);
			return true;
		}
		return false;		
	}
	
	public boolean removerLogin(String email) {
		Usuario u = buscarUsuario(email);
		if(u!=null) {//verifica se o usuario ta cadastrasdo: evitando um remove(null)
			return usuarios.remove(email, u);//remove o usuario encontrado a partir da chave passada
		}
		return false;
	}
	
	//recebe um email por fora porque se for buscar pelo email do usuario(usuario editado) passado
	//pode acontecer do email dele ter sido mudado: Busca pelo email antigo
	public boolean editarUsuario(String email, Usuario usuario) {
		if(buscarUsuario(email)!=null) {//verifica se so usuario que vai ser editado existe
			removerLogin(email);//apaga o usuario antigo
			return adicionarLogin(usuario);//adiciona o usuario editado
		}
		return false;
	}
	
	public boolean isAutenticado(String email, String senha) {
		Usuario u = buscarUsuario(email);
		if(u!=null) {//verifica se o usuario ta cadastrado
			if(u.getSenha().equals(senha))//verifica se a senha passada e igual a senha do usuario encontrado com o email
				return true;//senhas iguais: autenticado
		}
		return false;
	}
	
//	busca o funcionario pelo email(chave) retorna null ou o usuario
	public Usuario buscarUsuario(String email) {
		if(usuarios.isEmpty())
			return null;
		return usuarios.get(email);
	}

}
