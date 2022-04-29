package adt.queue;

import adt.stack.StackUnderflowException;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		if (this.isEmpty()) {
			try {
				throw new QueueUnderflowException();
			} catch (QueueUnderflowException e) {
				e.printStackTrace();
			}
		}
		return this.array[0];
	}

	@Override
	public boolean isEmpty() {
		return this.tail == -1;
	}

	@Override
	public boolean isFull() {
		return ((this.tail + 1) % array.length) == array.length-1;
	}

	private void shiftLeft() {
		for (int i = 0; i < tail; i++) {
			array[i] = array[i+1];
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull()) {
			throw new QueueOverflowException();
		} else {
			this.array[++tail] = element;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (!this.isEmpty()) {
			T value = array[0];
			shiftLeft();
			tail--;
			return value;
		} else {
			throw new QueueUnderflowException();
		}
	}

}
