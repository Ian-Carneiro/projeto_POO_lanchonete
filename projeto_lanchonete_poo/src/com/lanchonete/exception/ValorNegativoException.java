package com.lanchonete.exception;

public class ValorNegativoException extends Exception{
	public ValorNegativoException() {
		super("Opção inválida: Valor negativo");
	}
}
