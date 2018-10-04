package com.lanchonete.exception;

public class CampoVazioException extends Exception{
	public CampoVazioException() {
		super("Campo obrigatório não preenchido");
	}
}
