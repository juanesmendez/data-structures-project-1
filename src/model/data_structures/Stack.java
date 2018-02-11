package model.data_structures;

public class Stack <T extends Comparable<T>>{
	
	private Node top;
	private int size;
	
	public Stack() {
		this.top = null;
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
	
	public void push(T item) {
		
		Node<T> node = new Node<T>(item);
		if(this.top == null) {
			node.setNext(null);
			this.top = node;
			this.size++;
		}else {
			node.setNext(this.top);
			this.top = node;
			this.size++;
		}
	}
	
	public T pop() {
		if(this.top == null) {
			return null;
		}else {
			T item = (T) this.top.getItem();
			this.top = this.top.getNext();
			this.size--;
			return item;
		}
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		if(this.size == 0) {
			return true;
		}else {
			return false;
		}
	}
}
