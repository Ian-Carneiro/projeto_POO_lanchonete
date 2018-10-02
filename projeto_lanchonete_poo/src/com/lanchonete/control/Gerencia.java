package com.lanchonete.control;
import java.util.ArrayList;
import java.util.List;
import com.lanchonete.model.Comanda;
import java.time.LocalDate;

/**
 * A classe Gerencia tem funções para gerenciar informações sobre as Mesas já fechadas.
 * @author Ian
 * @since 1.10
 * @see Comanda
 * @version 1.0
 **/
public class Gerencia {
	/**
	 * Lista de mesas
	 * **/
	private static List<Comanda> gerencia = new ArrayList<>();
	
	/**
	 * Adiciona uma mesa que foi encerrada para a lista de mesas de Gerencia. 
	 **/
	static boolean addParaGerencia(Comanda d) {//esta função é usada somente por GerenciaMesa 
		if(!gerencia.isEmpty())
			d.setContador(gerencia.get(gerencia.size()-1).getContador());
		return gerencia.add(d);
	}
	
	/**
	 * Lista as mesas que foram abertas dentro do período informado.
	 * @param inicio recebe a data de início do intervalo de datas.
	 * @param fim recebe a data que determina o fim do intervalo de datas.
	 * @return Todas as mesas que estão com data de criação dentro do período informado.
	 **/
	public static ArrayList<Comanda> listarComandasEntre(LocalDate inicio, LocalDate fim) {
		ArrayList<Comanda> comandas = new ArrayList<>();
		for(Comanda c : gerencia) {
			if(c.getData().isAfter(inicio.plusDays(-1)) && c.getData().isBefore(fim.plusDays(1))) {// se a data de uma comanda c qualquer for depois de "inicio-1" e antes de "fim+1" ela é concatenada como String
				comandas.add(c);
			}
		}
		return comandas;
	}
	
	/**
	 * Calcula o lucro total das mesas encerradas que estão no intervalo de datas informado.
	 * @param inicio recebe a data de início do intervalo de datas.
	 * @param fim recebe a data que determina o fim do intervalo de datas.
	 * @return O somatório do valor total de todas as comandas dentro do período.
	 **/
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
