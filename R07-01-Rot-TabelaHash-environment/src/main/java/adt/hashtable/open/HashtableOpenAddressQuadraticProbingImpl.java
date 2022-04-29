package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			int probe = 0;
			int hash = getHash(element, probe);
			while (this.table[hash] != null && probe < this.table.length && !this.table[hash].equals(deletedElement)) {
				hash = getHash(element, ++probe);
				this.COLLISIONS++;
			}

			if (this.table[hash] != null && probe >= this.table.length) {
				throw new HashtableOverflowException();
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
			while (this.table[hash] != null && !this.table[hash].equals(deletedElement) && probe < this.table.length) {
				if (this.table[hash].equals(element)) {
					this.table[hash] = deletedElement;
					this.elements--;
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
		return ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, probe);
	}
}
