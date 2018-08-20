package com.lanchonete.model;

import java.util.HashMap;
import java.util.Map;

public class Autenticar {
	private static Map<String, String> autenticados = new HashMap<>();
	static boolean adicionarLogin(String email, String senha) {
		if(!autenticados.containsKey(email)) {//verifica se não ja contem o email  
			autenticados.put(email, senha);	//adiciona
			return true;//put não retorna boolean
		}
		return false;
	}
	static boolean removerLogin(String email, String senha) {
		return autenticados.remove(email, senha);//remove se ouver a chave e retorna boolean
	}
	static boolean isAutenticado(String email, String senha) { 
		if(autenticados.containsKey(email)) {//verifica se contem a chave(email) primeiro
			return autenticados.get(email).equals(senha);//verifica se a senha é a mesma armazenada com a chave(email) específica
		}
		return false;
	}
}
