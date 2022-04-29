package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (this.isFull())
			throw new HashtableOverflowException();

		if (element != null) {
			int probe = 0;
			int hash = getHash(element, probe);
			while (this.table[hash] != null && !this.table[hash].equals(deletedElement)) {
				hash = getHash(element, ++probe);
				this.COLLISIONS++;
			}
			this.table[hash] = element;
			this.elements++;
		}
	}

	@Override
	public void remove(T element) {
		if (!this.isEmpty() && element != null && this.search(element) != null) {
			int probe = 0;
			int hash = getHash(element, probe);
			while (probe < this.table.length && this.table[hash] != null) {
				if (this.table[hash].equals(element)) {
					this.table[hash] = deletedElement;
					elements--;
					break;
				}
				hash = getHash(element, ++probe);
			}
		}
	}

	@Override
	public T search(T element) {
		if (this.isEmpty() || element == null)
			return null;

		int index = this.indexOf(element);

		if (index == -1) {
			return null;
		} else {
			return (T) this.table[index];
		}
	}

	@Override
	public int indexOf(T element) {
		if (this.isEmpty() || element == null)
			return -1;

		int probe = 0;
		int i = -1;
		int hash = getHash(element, probe);

		while (probe < this.table.length && this.table[hash] != null) {
			if (this.table[hash].equals(element)) {
				i = hash;
				break;
			}
			hash = getHash(element, ++probe);
		}
		return i;
	}

	private int getHash(T element, int probe) {
		return ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, probe);
	}

}
