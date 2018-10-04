package com.lanchonete.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.lanchonete.dao.DaoMapGenerico;
import com.lanchonete.exception.CampoVazioException;
import com.lanchonete.exception.DataNascimentoException;
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

public class GerenciaUsuario extends DaoMapGenerico<Usuario>{
	
	public static File file = new File("Usuario");
	
	/**
	 * Realiza o cadastro do usuário no sistema, sem permitir repetições, definindo como chave o E-mail do usuário.
	 * @param usuario o usuario que será cadastrado no sistema.
	 * @return true ou false.  
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 * @throws DataNascimentoException 
	 * @throws CampoVazioException 
	 * */
	public static boolean adicionarLogin(Usuario usuario) throws FileNotFoundException, ClassNotFoundException, IOException, DataNascimentoException, CampoVazioException {
		validaUsuario(usuario);
		HashMap<String, Usuario> usuarios = getEstrutura(file);
		if(buscarUsuario(usuario.getEmail())==null) {//verifica se ja nao existe o usuario
			usuarios.put(usuario.getEmail(), usuario);
			push(usuarios, file);
			return true;
		}
		return false;		
	}
	/**
	 * Remove o cadastro do usuário através de sua chave(e-mail).
	 * @param email o e-mail do usuário a ser removido do sistema.
	 * @return true ou false.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 * */
	public static boolean removerLogin(String email) throws FileNotFoundException, ClassNotFoundException, IOException {
		HashMap<String, Usuario> usuarios = getEstrutura(file);
		Usuario u = buscarUsuario(email);
		if(u!=null) {//verifica se o usuario ta cadastrasdo: evitando um remove(null)
			usuarios.remove(email);//remove o usuario encontrado a partir da chave passada
			push(usuarios, file);
			return true;
		}
		return false;
	}
	/**
	 * Edita o usuário que possui um determinado e-mail.
	 * @param email o e-mail utilizado para identificação do usuário
	 * @param usuario será utilizado para atualização do usuário.
	 * @return true ou false
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 * @throws DataNascimentoException 
	 * @throws CampoVazioException 
	 * */
	//recebe um email por fora porque se for buscar pelo email do usuario(usuario editado) passado
	//pode acontecer do email dele ter sido mudado: Busca pelo email antigo
	public static boolean editarUsuario(String email, Usuario usuario) throws FileNotFoundException, ClassNotFoundException, IOException, DataNascimentoException, CampoVazioException {
		validaUsuario(usuario);
		if(buscarUsuario(email)!=null) {//verifica se so usuario que vai ser editado existe
			if(usuario.getNascimento().isAfter(LocalDate.now())) {
				throw new DataNascimentoException();
			}
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
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 * */
	public static boolean isAutenticado(String email, String senha) throws FileNotFoundException, ClassNotFoundException, IOException {
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
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 * */
	public static Usuario buscarUsuario(String email) throws FileNotFoundException, ClassNotFoundException, IOException {
		HashMap<String, Usuario> usuarios = getEstrutura(file);
		Usuario u = usuarios.get(email);
		if(usuarios.isEmpty() || u == null)
			return null;
		return u;
	}
	
	public static void validaUsuario(Usuario usuario) throws DataNascimentoException, CampoVazioException {
		if(usuario.getCpf().equals("   .   .   -  ")) {
			throw new CampoVazioException();
		}
		if(usuario.getNascimento().isAfter(LocalDate.now())) {
			throw new DataNascimentoException();
		}
		String[] email = usuario.getEmail().split("@");
		if(email[0].equals("")||usuario.getNome().equals("")||usuario.getSenha().equals("")) {
			throw new CampoVazioException();
		}
	}
}
