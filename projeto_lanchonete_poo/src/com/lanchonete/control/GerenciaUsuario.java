package com.lanchonete.control;

import java.util.HashMap;
import java.util.Map;

import com.lanchonete.model.Usuario;

/**
 * A classe GerenciaUsuario possui métodos para criar, atualizar e deletar os dados de um usuário do sistema, 
 * com isto é possível garantir a autenticidade do usuário.
 * @author Leanderson
 * @since 1.10
 * @see java.util.HashMap
 * @see com.lanchonete.model.Usuario
 * @version 1.0
 * */

public class GerenciaUsuario {
	/**
	 * Inicializa a estrutura HashMap sem conter valores. 
	 * */
	private static Map<String, Usuario> usuarios = new HashMap<>();
	
	/**
	 * Realiza o cadastro do usuário no sistema, sem permitir repetições, definindo como chave o E-mail do usuário.
	 * @param usuario o usuario que será cadastrado no sistema.
	 * @return true ou false.  
	 * */
	public static boolean adicionarLogin(Usuario usuario) {
		if(buscarUsuario(usuario.getEmail())==null) {//verifica se ja nao existe o usuario
			usuarios.put(usuario.getEmail(), usuario);
			return true;
		}
		return false;		
	}
	/**
	 * Remove o cadastro do usuário através de sua chave(e-mail).
	 * @param email o e-mail do usuário a ser removido do sistema.
	 * @return true ou false.
	 * */
	public static boolean removerLogin(String email) {
		Usuario u = buscarUsuario(email);
		if(u!=null) {//verifica se o usuario ta cadastrasdo: evitando um remove(null)
			return usuarios.remove(email, u);//remove o usuario encontrado a partir da chave passada
		}
		return false;
	}
	/**
	 * Edita o usuário que possui um determinado e-mail.
	 * @param email o e-mail utilizado para identificação do usuário
	 * @param usuario será utilizado para atualização do usuário.
	 * @return true ou false
	 * */
	//recebe um email por fora porque se for buscar pelo email do usuario(usuario editado) passado
	//pode acontecer do email dele ter sido mudado: Busca pelo email antigo
	public static boolean editarUsuario(String email, Usuario usuario) {
		if(buscarUsuario(email)!=null) {//verifica se so usuario que vai ser editado existe
			removerLogin(email);//apaga o usuario antigo
			return adicionarLogin(usuario);//adiciona o usuario editado
		}
		return false;
	}
	/**
	 * Verifica se um determinado usuário é cadastrado ou não.
	 * @param email o e-mail utilizado para identificação do usuário
	 * @param senha a senha utilizada para autenticação do login.
	 * @return true ou false
	 * */
	public static boolean isAutenticado(String email, String senha) {
		Usuario u = buscarUsuario(email);
		if(u!=null) {//verifica se o usuario ta cadastrado
			if(u.getSenha().equals(senha))//verifica se a senha passada e igual a senha do usuario encontrado com o email
				return true;//senhas iguais: autenticado
		}
		return false;
	}
	

	/**
	 * Busca um usuário pelo e-mail 
	 * @param email o e-mail utilizado na busca do usuário
	 * @return Usuário ou null 
	 * */
	public static Usuario buscarUsuario(String email) {
		if(usuarios.isEmpty())
			return null;
		return usuarios.get(email);
	}

}
