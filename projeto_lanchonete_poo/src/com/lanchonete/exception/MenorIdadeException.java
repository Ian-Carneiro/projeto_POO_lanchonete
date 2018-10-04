package com.lanchonete.exception;

public class MenorIdadeException extends Exception{
	public MenorIdadeException() {
		super("Usuário com idade inadequada");
	}
}
