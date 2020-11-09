package adt.hashtable.open;

import java.util.LinkedList;

import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size, HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (this.isFull())
			throw new HashtableOverflowException();
		if (element != null && search(element) == null) {
			int sondagem = 0;
			int hash = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, sondagem);
			while(this.table[hash] != null && !this.table[hash].equals(deletedElement)) {
				hash = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, ++sondagem);
				this.COLLISIONS++;
			}
			this.table[hash] = element;
			this.elements++;
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !this.isEmpty() && this.search(element) != null) {
			int sondagem = 0;
			int hash = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, sondagem);
			while (this.table[hash] != null && sondagem < table.length ) {
				if(this.table[hash].equals(element)) {
					this.table[hash] = deletedElement;
					this.elements--;
					break;
				}
				 hash = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, ++sondagem);
			}
		}
	}

	
	@Override
	public T search(T element) {
		T retorno = null;
		if (element != null && !this.isEmpty()) {
			if (this.indexOf(element) != -1)
				retorno = (T) this.table[this.indexOf(element)];
		}
		return retorno;
	}

	@Override
	public int indexOf(T element) {
		int retorno = -1;
		if (element != null && !this.isEmpty()) {
			int sondagem = 0;
			int hash = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, sondagem);
			while (sondagem < this.table.length && this.table[hash] != null) {
				if (this.table[hash].equals(element)) {
					retorno = hash;
					break;
				}
				hash = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, ++sondagem);
			}
		}
		return retorno;
	}
}
