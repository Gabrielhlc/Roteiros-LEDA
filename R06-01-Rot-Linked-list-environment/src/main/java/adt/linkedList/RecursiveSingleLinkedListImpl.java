package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}


	@Override
	public boolean isEmpty() {
		return this.getData() == null;
	}

	@Override
	public int size() {
		if (this.isEmpty())
			return 0;
		return 1 + this.next.size();
	}

	@Override
	public T search(T element) {
		if (element != null) {
			if (!this.isEmpty()) {
				if (this.getData().equals(element))
					return this.getData();
				else {
					return this.getNext().search(element);
				}
			}
		}
		return null;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.next == null) {
				this.setData(element);
				this.setNext(new RecursiveSingleLinkedListImpl<>());
			} else {
				this.getNext().insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			if (this.getData().equals(element)) {
				this.setData(this.getNext().getData());
				this.setNext(this.getNext().getNext());
			} else {
				this.getNext().remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[this.size()];
		if (this.isEmpty()) {
			return (T[]) new Comparable[0];
		}
		toArray(array, 0, this);
		return array;
	}

	private void toArray(T[] array, int i, RecursiveSingleLinkedListImpl<T> node) {
		if (!node.isEmpty()) {
			array[i] = node.getData();
			this.toArray(array, i+1, node.getNext());
		}
	}
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
