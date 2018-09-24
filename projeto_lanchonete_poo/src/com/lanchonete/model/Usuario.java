package com.lanchonete.model;
import java.time.LocalDate;
/**
 * A classe Usuario modela a entidade usuário.
 * @author Ian
 * @see GerenciaUsuario
 * @since 1.10
 * @version 1.0
 * */
public class Usuario {
	private String cpf;
	private String nome;
	private String email;
	private String senha;
	private String telefone;
	private LocalDate nascimento;
	private String setor;
	/**
	 * Inicializa os atributos do Usuário
	 * @param cpf o CPF do usuário.
	 * @param nome o nome do usuário.
	 * @param email o E-mail do usuário.
	 * @param senha a senha do login do usuário.
	 * @param telefone o número do telefone do usuário.
	 * @param nascimento a data de nascimento do usuário.
	 * @param s o setor em que o usuário trabalha.
	 * */
	public Usuario(String cpf, String nome, String email, String senha, String telefone, LocalDate nascimento, String setor) {
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.senha =senha;
		this.telefone = telefone;
		this.nascimento = nascimento;
		this.setor = setor;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public LocalDate getNascimento() {
		return nascimento;
	}
	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	@Override
	public String toString() {
		return "Usuario [cpf=" + cpf + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", telefone="
				+ telefone + ", nascimento=" + nascimento + ", setor=" + setor + "]";
	}
	
}