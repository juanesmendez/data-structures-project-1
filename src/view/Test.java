package view;

import java.util.Stack;

import model.data_structures.Queue;
import model.world.Taxi;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Stack<Taxi> pila = new Stack<>();
		Taxi aux;
		Taxi tax1 = new Taxi("1", "Taxis Libres");
		Taxi taxi2 = new Taxi("2", "Tappsi");
		Taxi taxi3 = new Taxi("3", "Taxis Cheveres");
		
		
		// Trying out Stack
		System.out.println("Stack:");
		System.out.println();
		pila.push(tax1);
		pila.push(taxi2);
		pila.push(taxi3);
		
		while(!pila.isEmpty()) {
			System.out.println("Size: "+pila.size());
			aux = pila.pop();
			System.out.println(aux.toString());
			System.out.println();
		}
		
		//Trying Queue
		System.out.println("Queue:");
		System.out.println();
		Queue<Taxi> cola = new Queue<>();
		
		cola.enqueue(tax1);
		cola.enqueue(taxi2);
		cola.enqueue(taxi3);
		
		while(!cola.isEmpty()) {
			System.out.println(cola.dequeue());
		}
		System.out.println("Size:"+cola.size());
	}
	

}
