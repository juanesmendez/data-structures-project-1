package model.data_structures;

public class List<T> {

	private Node head;
	private int size;
	
	public List() {
		this.head = null;
		this.size = 0;
	}
	
	
	public Node getHead() {
		return head;
	}


	public void setHead(Node head) {
		this.head = head;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public void insertBeginning(Node<T> node) {
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
	public void insert(Node<T> node) {
		if(this.head == null) {
			insertBeginning(node);
		}else {
			Node<T> aux = this.head;
			while(aux.getNext() != null) {
				aux = aux.getNext();
			}
			aux.setNext(node);
			this.size++;
		}
	}
	public void delete(Node<T> node) {
		if(this.head!=null) {
			Node<T> aux = this.head;
			if(aux == node) {
				this.head = aux.getNext();
				this.size--;
			}else {
				while(aux.getNext()!=null) {
					if(aux.getNext() == node) {
						Node<T> siguiente = node.getNext();
						node = null;
						aux.setNext(siguiente);
						this.size--;
						break;
					}
					aux = aux.getNext();
				}
			}
		}
		
	}
}
