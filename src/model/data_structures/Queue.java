package model.data_structures;

import model.data_structures.Stack.Node;

public class Queue <T extends Comparable<T>>{

	private Node first;
	private Node last;
	private int size;
	
	public Queue() {
		this.first = null;
		this.last = null;
		this.size = 0;
	}
	
	public class Node<T extends Comparable<T>> {
		T item;
		Node next;
		
		public Node() {
			this.item = null;
			this.next = null;
		}
		public Node(T item) {
			this.item = item;
			this.next = null;
		}

		public T getItem() {
			return item;
		}

		public void setItem(T item) {
			this.item = item;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
		
	}
	
	public void enqueue(T item) {
		Node<T> node = new Node<T>(item);
		if(isEmpty()) {
			this.first = node;
			this.last = node;
			this.size++;
		}else {
			this.last.setNext(node);
			this.last = node;
			this.size++;
		}
	}
	
	public T dequeue() {
		T item = (T) this.first.getItem();
		this.first = this.first.getNext();
		this.size--;
		if(isEmpty()) {
			this.last = null;
		}
		return item;
	}
	
	public boolean isEmpty() {
		if(this.size == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public int size() {
		return this.size;
	}
}
