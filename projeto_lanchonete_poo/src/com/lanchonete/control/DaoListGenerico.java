package com.lanchonete.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class DaoListGenerico<T>{
	
	public static<T> ArrayList<T> getEstrutura(File file) throws FileNotFoundException, IOException, ClassNotFoundException{
		
		if(file.length()>0) {
			try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(file)) ){
				return (ArrayList<T>)in.readObject();
			}
		}
		return new ArrayList<T>();
	}
	public static<T> void push(ArrayList<T> obj, File file) throws FileNotFoundException, IOException {
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))){
			out.writeObject(obj);
		}
	}
}