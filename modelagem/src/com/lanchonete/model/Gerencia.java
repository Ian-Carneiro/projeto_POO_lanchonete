package com.lanchonete.model;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
public class Gerencia {
	private static List<Comanda> gerencia = new ArrayList<>();
	static boolean addParaGerencia(Comanda d) {//esta função é usada somente por GerenciaMesa 
		return gerencia.add(d);
	}
	public static String listarComandasEntre(LocalDate inicio, LocalDate fim) {
		String s = "";
		for(Comanda c : gerencia) {
			if(c.getData().isAfter(inicio.plusDays(-1)) && c.getData().isBefore(fim.plusDays(1))) {// se a data de uma comanda c qualquer for depois de "inicio-1" e antes de "fim+1" ela é concatenada como String
				s+=c.toString();
			}
		}
		return s;
	}
	public static float lucroTotalEntre(LocalDate inicio, LocalDate fim) {
		float lucro = 0f;
		for(Comanda c : gerencia) {
			if(c.getData().isAfter(inicio.plusDays(-1)) && c.getData().isBefore(fim.plusDays(1))) {
				lucro+=c.valorTotal();
			}
		}
		return lucro;
	}
}
