package com.lanchonete.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.util.ArrayList;
//import java.util.List;



public class DaoListGenerico{
	
	public static<T> T getEstrutura(File file) throws FileNotFoundException, IOException, ClassNotFoundException{
		
		if(file.length()>0) {
			try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(file)) ){
				return (T)in.readObject();
			}
		}
		return null;
	}
	public static<T> void push(T obj, File file) throws FileNotFoundException, IOException {
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))){
			out.writeObject(obj);
		}
	}
}
