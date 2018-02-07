package view;


import java.util.Iterator;

import model.data_structures.List;
import mundo.SistemaTaxis;
import mundo.Taxi;
import persistencia.ManejoArchivos;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SistemaTaxis sistema = new SistemaTaxis();
		
		List lista = new List<Taxi>();
		
		
		Taxi miTaxi = new Taxi("1", "Taxis Libres");
		Taxi miTaxi2 = new Taxi("2", "Tappsi");
		
		//Taxi t  = (Taxi) lista.createNode(miTaxi).getData();
		//Taxi t2 = (Taxi) lista.createNode(miTaxi2).getData();
		
		lista.insert(miTaxi);
		lista.insert(miTaxi2);
		ManejoArchivos.leerArchivo(sistema);
		
		
		
		//lista.insert(t);
		//lista.insert(t2);
		/*
		for(Node n = lista.getHead();n==null;n.getNext()) {
			System.out.println(n.getData().toString());
		}*/
		
		/*
		Node aux = lista.getHead();
		while(aux != null) {
			System.out.println(aux.getData().toString());
			aux = aux.getNext();
		}*/
		Iterator iterator = lista.iterator();
		
		while(iterator.hasNext()) {
			System.out.println(iterator.next().toString());
		}
		
		
		System.out.println();
			
			
		lista.delete(miTaxi);
		iterator = lista.iterator();
		
		while(iterator.hasNext()) {
			System.out.println(iterator.next().toString());
		}
		
		
	}

}
