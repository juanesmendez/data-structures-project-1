package view;

import java.util.Stack;

import model.data_structures.LinkedList;
import model.data_structures.List;
import model.data_structures.Queue;
import model.world.Company;
import model.world.Taxi;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LinkedList<Taxi> lista = new List<>();
		
		Taxi taxi1 = new Taxi("1");
		Taxi taxi2 = new Taxi("2");
		Taxi taxi3 = new Taxi("3");
		Taxi taxi4 = new Taxi("4");
		Taxi taxi5 = new Taxi("5");
		Taxi taxi6 = new Taxi("6");
		Taxi taxi7 = new Taxi("7");
		
		Taxi taxi42 = new Taxi("4");
		
		
		lista.addInOrder(taxi4);
		lista.addInOrder(taxi7);
		lista.addInOrder(taxi42);
		/*
		lista.addInOrder(taxi4);
		System.out.println(lista.getLast().getTaxiId());
		lista.addInOrder(taxi5);
		System.out.println(lista.getLast().getTaxiId());
		lista.addInOrder(taxi1);
		System.out.println(lista.getLast().getTaxiId());
		lista.addInOrder(taxi6);
		System.out.println(lista.getLast().getTaxiId());*/
		//lista.addInOrder(taxi1);
		/*
		lista.addInOrder(taxi2);
		lista.addInOrder(taxi1);
		lista.addInOrder(taxi5);
		lista.addInOrder(taxi3);
		lista.addInOrder(taxi7);
		lista.addInOrder(taxi6);
		*/
		for(Taxi t:lista) {
			System.out.println("Taxi ID: "+t.getTaxiId());
		}
		System.out.println();
		System.out.println("Size: "+lista.size());
		
		
		/*
		Company company1 = new Company("Taxis Libres");
		Company company2 = new Company("Tappsi");
		Company company3 = new Company("Taxis Cheveres");
		//LIST
		LinkedList<Company> lista =new List<>();
		System.out.println("LISTA: ");
		System.out.println();
		lista.add(company1);
		lista.add(company2);
		lista.add(company3);
		System.out.println("Size Lista: "+lista.size());
		System.out.println("Itero la lista:");
		for(Company c:lista) {
			System.out.println(c.toString());
		}
		System.out.println();
		System.out.println("Accedo a elemento T por medio de metodo get():");
		System.out.println(lista.get(company2).toString());
		System.out.println();
		System.out.println("Accedo a elemento T por medio de metodo get() por posicion:");
		System.out.println(lista.get(2).toString());
		
		System.out.println();
		System.out.println();
		System.out.println();
		//STACK
		Stack<Company> pila = new Stack<>();
		
		
		// Trying out Stack
		System.out.println("STACK:");
		System.out.println();
		pila.push(company1);
		pila.push(company2);
		pila.push(company3);
		
		while(!pila.isEmpty()) {
			System.out.println("Size Stack: "+pila.size());
			System.out.println(pila.pop().toString());
			System.out.println();
		}
		System.out.println("Size despues de pops: "+pila.size());
		System.out.println();
		System.out.println();
		System.out.println();
		//Trying Queue
		System.out.println("QUEUE:");
		System.out.println();
		Queue<Company> cola = new Queue<>();
		
		cola.enqueue(company1);
		cola.enqueue(company2);
		cola.enqueue(company3);
		
		System.out.println("Size Queue: "+cola.size());
		while(!cola.isEmpty()) {
			System.out.println(cola.dequeue().toString());
		}
		System.out.println("Size:"+cola.size());*/
	}
	

}
