package com.lanchonete.control;
import java.util.ArrayList;
import java.util.List;

import com.lanchonete.dao.DaoListGenerico;
import com.lanchonete.model.Comanda;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

/**
 * A classe Gerencia tem funções para gerenciar informações sobre as Mesas já fechadas.
 * @author Ian
 * @since 1.10
 * @see Comanda
 * @version 1.0
 **/
public class Gerencia extends DaoListGenerico{

	private static File file = new File("Comanda");
	
	/**
	 * Adiciona uma mesa que foi encerrada para a lista de mesas de Gerencia. 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 **/
	static boolean addParaGerencia(Comanda d) throws FileNotFoundException, ClassNotFoundException, IOException {//esta função é usada somente por GerenciaMesa 
		ArrayList<Comanda> gerencia = getEstrutura(file);
		if(!gerencia.isEmpty())
			d.setContador(gerencia.get(gerencia.size()-1).getContador()+1);
		gerencia.add(d);
		push(gerencia, file);
		return true;
	}
	
	/**
	 * Lista as mesas que foram abertas dentro do período informado.
	 * @param inicio recebe a data de início do intervalo de datas.
	 * @param fim recebe a data que determina o fim do intervalo de datas.
	 * @return Todas as mesas que estão com data de criação dentro do período informado.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 **/
	public static ArrayList<Comanda> listarComandasEntre(LocalDate inicio, LocalDate fim) throws FileNotFoundException, ClassNotFoundException, IOException {
		ArrayList<Comanda> gerencia = getEstrutura(file);
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
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 **/
	public static float lucroTotalEntre(LocalDate inicio, LocalDate fim) throws FileNotFoundException, ClassNotFoundException, IOException {
		float lucro = 0f;
		ArrayList<Comanda> gerencia = getEstrutura(file);
		for(Comanda c : gerencia) {
			if(c.getData().isAfter(inicio.plusDays(-1)) && c.getData().isBefore(fim.plusDays(1))) {
				lucro+=c.valorTotal();
			}
		}
		return lucro;
	}
}
