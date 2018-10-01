package com.lanchonete.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class DaoMapGenerico <T>{
	public static <K, V> HashMap<K, V> getEstrutura(File file) throws FileNotFoundException, IOException, ClassNotFoundException{
		if(file.length()>0) {
			try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
				return (HashMap<K, V>) in.readObject();
			}
		}
		return new HashMap<>();
	}
	
	public static <V, K> void push(HashMap<K, V> map, File file) throws FileNotFoundException, IOException{
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))){
			out.writeObject(map);
		}
	}
}
