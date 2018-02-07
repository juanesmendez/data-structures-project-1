package model.data_structures;

import java.util.Iterator;

public class List<T> implements Iterable<T>{

	private Node head;
	private int size;
	private Iterator<T> iterator;
	
	public List() {
		this.head = null;
		this.size = 0;
		//Iterator initialization missing
	}
	
	public class Node<T> {
		T data;
		Node next;
		
		public Node() {
			this.data = null;
			this.next = null;
		}
		public Node(T data) {
			this.data = data;
			this.next = null;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
		
	}
	
	public T getHead() {
		return (T) head.getData();
	}

	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}
	
	public Node createNode(T data) {
		Node node = new Node<T>(data);
		return node;
	}
	
	public void insertBeginning(T data) {
		Node node = new Node<T>(data);
		if(this.head == null) {
			this.head = node;
			this.size++;
		}else {
			Node<T> anteriorPrimero = this.head;
			this.head = node;
			node.setNext(anteriorPrimero);
			this.size++;
		}
	}
	public void insert(T data) {
		Node<T> node = new Node<T>(data);
		if(this.head == null) {
			insertBeginning(data);
		}else {
			Node<T> aux = this.head;
			while(aux.getNext() != null) {
				aux = aux.getNext();
			}
			aux.setNext(node);
			this.size++;
		}
	}
	public void delete(T data) {
		
		if(this.head!=null) {
			Node<T> aux = this.head;
			if(aux.getData() == data) {
				this.head = aux.getNext();
				this.size--;
			}else {
				while(aux.getNext()!=null) {
					if(aux.getNext().getData() == data) {
						Node<T> temp = aux.getNext();
						Node<T> siguiente = temp.getNext();
						temp = null;
						aux.setNext(siguiente);
						this.size--;
						break;
					}
					aux = aux.getNext();
				}
			}
		}
		
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new listIterator();
	}
	private class listIterator implements Iterator<T>{
		private Node current = head;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current != null;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			
			T data = (T) current.getData();
			current = current.getNext();
			return data;
		}
	}
}
