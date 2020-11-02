package adt.linkedList;

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
		int size = 0;
		SingleLinkedListNode<T> node = this.head;
		if (isEmpty())return size;
		
		while (!node.isNIL()) {
			size++;
			node = node.getNext();

		}
		return size;
	}

	@Override
	public T search(T element) {
		T out = null;
		
		if (element != null) {
			SingleLinkedListNode<T> aux = this.getHead();
			while (!aux.isNIL()) {
				if (aux.getData().equals(element)) {
					out = aux.getData();
					break;
				}

				aux = aux.getNext();
			}
		}
		return out;

	}

	@Override
	public void insert(T element) {
		if(element==null)return;
		SingleLinkedListNode<T> aux = this.head;
		SingleLinkedListNode<T> newElement = new SingleLinkedListNode<T>();
		newElement.setData(element);
		newElement.setNext(new SingleLinkedListNode<>());
		
		if (isEmpty())
			this.setHead(newElement);
		else {
			while (!aux.getNext().isNIL()) {
				aux = aux.getNext();
				}
			aux.setNext(newElement);
				
			}
		}

	

	@Override
	public void remove(T element) {
		if (element == null || isEmpty())return;
		if (this.head.getData().equals(element)) {
			this.head = this.head.getNext();
			return;
		}

		SingleLinkedListNode<T> actual = this.head.getNext();
		SingleLinkedListNode<T> prev = this.head;

		while (!actual.isNIL()) {
			if (actual.getData().equals(element)) {
				prev.setNext(actual.getNext());
				break;
			} else {
				prev = actual;
				actual = actual.getNext();
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] output = (T[]) new Comparable[size()];
		
		if (isEmpty())return output;
		
		SingleLinkedListNode<T> aux = this.head;

		Integer contador = 0;
		while (!aux.isNIL()) {
			output[contador++] = aux.getData();
			aux = aux.getNext();
		}
		return output;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
