package adt.linkedList;

import org.w3c.dom.Node;

import java.sql.Array;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		int cont = 0;
		SingleLinkedListNode aux = this.head;
		if (this.head.getData() != null) {
			while (aux.getNext() != null) {
				aux = aux.getNext();
				cont++;
			}
		}
		return cont;
	}

	@Override
	public T search(T element) {
		T retorno = null;
		if (element != null) {
			SingleLinkedListNode<T> aux = this.head;

			while (!aux.isNIL()) {
				if (aux.getData().equals(element)) {
					retorno = aux.getData();
					break;
				}
				aux = aux.getNext();
			}
		}
		return retorno;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			SingleLinkedListNode<T> auxNode = this.head;

			while (!auxNode.isNIL())
				auxNode = auxNode.getNext();

			auxNode.setNext(new SingleLinkedListNode<T>());
			auxNode.setData(element);
		}

	}

	@Override
	public void remove(T element) {
		if (element != null) {
			if (this.head.getData().equals(element)) {
				this.head = this.head.getNext();
			} else {
				SingleLinkedListNode<T> prevNode = this.head;
				SingleLinkedListNode<T> aux = prevNode.getNext();

				while (!aux.isNIL()) {
					if (aux.getData().equals(element)) {
						prevNode.setNext(aux.getNext());
					}
					prevNode = aux;
					aux = aux.getNext();
				}
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[this.size()];
		SingleLinkedListNode<T> aux = this.head;
		for (int i = 0; i < this.size(); i++) {
			array[i] = aux.getData();
			aux = aux.getNext();
		}
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
