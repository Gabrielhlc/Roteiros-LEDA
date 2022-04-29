package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		this.setHead(new DoubleLinkedListNode<>());
		this.setLast((DoubleLinkedListNode<T>) this.head);
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> node = new DoubleLinkedListNode<>();
			node.setData(element);

			if (this.isEmpty()) {
				this.head = node;
				this.last = node;
			} else {
				DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<>();
				newHead.setData(this.head.getData());
				node.setNext(this.head);
				newHead.setPrevious(node);
				this.setHead(node);
			}
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>();
			newNode.setData(element);
			newNode.setNext(new DoubleLinkedListNode<>());
			newNode.setPrevious(this.getLast());
			this.getLast().setNext(newNode);
			if (this.getLast().isNIL())
				this.setHead(newNode);

			this.setLast(newNode);
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> node = (DoubleLinkedListNode<T>) this.getHead();
			for (int i = 0; i < this.size(); i++) {
				if (node.getData().equals(element)) {
					if (i == 0)
						removeFirst();
					else if (i == this.size() - 1)
						removeLast();
					else {
						node.getPrevious().setNext(node.getNext());
						((DoubleLinkedListNode<T>) node.getNext()).setPrevious(node.getPrevious());
					}
				}
				node = (DoubleLinkedListNode<T>) node.getNext();
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!this.isEmpty()) {
			if (this.head.getNext() == null) {
				this.head = null;
				this.last = null;
			} else {
				this.head = this.head.getNext();
				((DoubleLinkedListNode<T>) this.getHead()).setPrevious(new DoubleLinkedListNode<>());
			}
		}
	}

	@Override
	public void removeLast() {
		if (!this.isEmpty()) {
			if (this.head.getNext() == null) {
				this.head = null;
				this.last = null;
			} else {
				this.setLast(this.getLast().getPrevious());
				this.getLast().setNext(new DoubleLinkedListNode<>());
			}
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
