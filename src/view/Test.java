package view;


import model.data_structures.List;
import model.data_structures.Node;
import mundo.SistemaTaxis;
import mundo.Taxi;
import persistencia.ManejoArchivos;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SistemaTaxis sistema = new SistemaTaxis();
		
		List lista = new List<Taxi>();
		
		Node primero = new Node<>();
		Node segundo = new Node<>();
		
		Taxi miTaxi = new Taxi("1", "Taxis Libres");
		Taxi miTaxi2 = new Taxi("2", "Tappsi");
		
		ManejoArchivos.leerArchivo(sistema);
		
		
		primero.setData(miTaxi);
		segundo.setData(miTaxi2);
		
		lista.insert(primero);
		lista.insert(segundo);
		/*
		for(Node n = lista.getHead();n==null;n.getNext()) {
			System.out.println(n.getData().toString());
		}*/
		Node aux = lista.getHead();
		while(aux != null) {
			System.out.println(aux.getData().toString());
			aux = aux.getNext();
		}
		
		lista.delete(primero);
		System.out.println();
		aux = lista.getHead();
		while(aux != null) {
			System.out.println(aux.getData().toString());
			aux = aux.getNext();
		}
	}

}
