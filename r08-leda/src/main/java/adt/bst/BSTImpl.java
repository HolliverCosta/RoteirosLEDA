package adt.bst;

import java.util.ArrayList;

import javax.xml.crypto.Data;


import adt.bst.BSTNode.Builder;
import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(getRoot());
		
	}

	private int height(BSTNode<T> node) {
		if(node.isEmpty())return -1;
		return 1 + Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight()));
	}

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> retorno = new BSTNode<T>();
		if (element != null && !isEmpty()) {
			BSTNode<T> aux = getRoot();
			while (aux.getData() != null) {
				if (element.equals(aux.getData()))
					return aux;
				else if (element.compareTo(aux.getData()) < 0)
					aux = (BSTNode<T>) aux.getLeft();
				else if (element.compareTo(aux.getData()) > 0)
					aux = (BSTNode<T>) aux.getRight();
			}
		}
		return retorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(T element) {
		if (element != null && search(element).getData() == null) {
			if (isEmpty()) {
				BSTNode<T> newNode = (BSTNode<T>) new BSTNode.Builder<T>().data(element).left(new BSTNode<T>())
						.right(new BSTNode<T>()).parent(null).build();
				this.root = newNode;
			} else {
				BSTNode<T> aux = getRoot();
				this.insert(aux, element);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void insert(BSTNode<T> node, T element) {
		if (element.compareTo(node.getData()) < 0) {
			if (node.getLeft().isEmpty()) {
				BSTNode<T> newNode = (BSTNode<T>) new BSTNode.Builder<T>().data(element).left(new BSTNode<T>())
						.right(new BSTNode<T>()).parent(node).build();
				node.setLeft(newNode);
			} else
				insert((BSTNode<T>) node.getLeft(), element);
		} else {
			if (node.getRight().isEmpty()) {
				BSTNode<T> newNode = (BSTNode<T>) new BSTNode.Builder<T>().data(element).left(new BSTNode<T>())
						.right(new BSTNode<T>()).parent(node).build();
				node.setRight(newNode);
			} else
				insert((BSTNode<T>) node.getRight(), element);
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if (isEmpty())
			return null;
		else
			return maximum(getRoot());
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node.getRight().isEmpty())
			return node;
		else
			return maximum((BSTNode<T>) node.getRight());
	}

	@Override
	public BSTNode<T> minimum() {
		if (isEmpty())
			return null;
		else
			return minimum(getRoot());
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (node.getLeft().isEmpty())
			return node;
		else
			return minimum((BSTNode<T>) node.getLeft());
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);

		if (node.isEmpty())
			return null;

		if (!node.getRight().isEmpty())
			return minimum((BSTNode<T>) node.getRight());
		else {
			BSTNode<T> aux = (BSTNode<T>) node.getParent();

			while (!aux.isEmpty() && aux.getData().compareTo(node.getData()) < 0) {
				aux = (BSTNode<T>) aux.getParent();
			}
			return aux;
		}
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);
		  if (node.isEmpty()) return null;
	        
	        if (!node.getLeft().isEmpty())
	            return maximum((BSTNode<T>) node.getLeft());
	        else {
	        	if(!node.getParent().isEmpty()) {
	        		BTNode<T> aux = node.getParent();
		            
		            while (aux != null && aux.getData().compareTo(node.getData()) > 0)
		                aux = aux.getParent();
		            return (BSTNode<T>) aux;
	        	}else
	        		return null;
	            
	           
	        }
	}
	@Override
	public void remove(T element) {
		BSTNode<T> nodeAux = search(element);
		if (!nodeAux.isEmpty()) {
			if(nodeAux.isLeaf()) {
				nodeAux.setData(null);
			}else if(temFilho(nodeAux)) {
				if(nodeAux.getParent()!=null) {
					if(!nodeAux.getParent().getLeft().equals(nodeAux)) {
						if(nodeAux.getLeft().isEmpty()) {
							nodeAux.getParent().setRight(nodeAux.getRight());
							nodeAux.getRight().setParent(nodeAux.getParent());
						}else {
							nodeAux.getParent().setRight(nodeAux.getLeft());
							nodeAux.getLeft().setParent(nodeAux.getParent());
						}
					}else {
						if(nodeAux.getLeft().isEmpty()) {
							nodeAux.getParent().setLeft(nodeAux.getRight());
							nodeAux.getRight().setParent(nodeAux.getParent());
						}else {
							nodeAux.getParent().setLeft(nodeAux.getLeft());
							nodeAux.getLeft().setParent(nodeAux.getParent());
						}
					}
				}else {
					if(!nodeAux.getLeft().isEmpty()) {
						this.root = (BSTNode<T>) nodeAux.getLeft();
					}else {
						this.root = (BSTNode<T>) nodeAux.getRight();
					}
					this.root.setParent(null);
				}
				
			}else {
				T sucessor = sucessor(nodeAux.getData()).getData();
				remove(sucessor);
				nodeAux.setData(sucessor);
			}
		}
			
	}
	private boolean temFilho(BSTNode<T> node) {
		return (!node.getRight().isEmpty() && node.getLeft().isEmpty()||!node.getLeft().isEmpty()&&node.getRight().isEmpty());
	}
	@Override
	public T[] preOrder() {
		T[] arrayPreOrder = null;
		ArrayList<T> listAux = new ArrayList<T>();
		if (!isEmpty()) {
			arrayPreOrder = (T[]) new Comparable[this.size()];
			preOrder(getRoot(),listAux);
		}else
			return (T[]) new Comparable[0];
		listAux.toArray(arrayPreOrder);
		return arrayPreOrder;
	}

	private void preOrder(BSTNode<T> node, ArrayList<T> list) {
		if (!node.isEmpty()) {
			list.add(node.getData());
			preOrder((BSTNode<T>)node.getLeft(), list);
			preOrder((BSTNode<T>)node.getRight(),list);
		}
		
	}

	@Override
	public T[] order() {
		T[] arrayOrder = null;
		ArrayList<T> listAux = new ArrayList<T>();
		if (!isEmpty()) {
			arrayOrder = (T[]) new Comparable[this.size()];
			order(getRoot(),listAux);
		}else
			return (T[]) new Comparable[0];
		listAux.toArray(arrayOrder);
		return arrayOrder;

	}

	private void order(BSTNode<T> node, ArrayList<T> list) {
		if (!node.isEmpty()) {
			order((BSTNode<T>) node.getLeft(), list);
			list.add(node.getData());
			order((BSTNode<T>) node.getRight(),list);
		}
	}

	@Override
	public T[] postOrder() {
		T[] arrayPostOrder = null;
		ArrayList<T> listAux = new ArrayList<T>();
		if (!isEmpty()) {
			arrayPostOrder = (T[]) new Comparable[this.size()];
			postOrder(getRoot(),listAux);
		}else
			return (T[]) new Comparable[0];
		listAux.toArray(arrayPostOrder);
		return arrayPostOrder;

	}

	private void postOrder(BSTNode<T> node, ArrayList<T> list) {
		if (!node.isEmpty()) {
			postOrder((BSTNode<T>) node.getLeft(), list);
			postOrder((BSTNode<T>) node.getRight(),list);
			list.add(node.getData());
		}		
	}

	/**
	 * This method is already implemented using recursion. You must understand how
	 * it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
