package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return - noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> aux = (BSTNode<T>) node.getRight();
		BSTNode<T> leftAux = (BSTNode<T>) aux.getLeft();
		BSTNode<T> parentNode = (BSTNode<T>) node.getParent();
		
		if(!parentNode.isEmpty()) {
			if(parentNode.getLeft().equals(node))
				parentNode.setLeft(aux);
			else
				parentNode.setRight(aux);
		}
		aux.setParent(parentNode);
		node.setParent(aux);	
		node.setRight(leftAux);
		aux.setLeft(node);
		
		if(!leftAux.isEmpty()) 
			leftAux.setParent(node);
		
		return aux;
		
		
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> aux = (BSTNode<T>) node.getLeft();
		BSTNode<T> rightAux = (BSTNode<T>) aux.getRight();
		BSTNode<T> parentNode = (BSTNode<T>) node.getParent();
		
		if(parentNode != null) {
			if(parentNode.getLeft().equals(node))
				parentNode.setLeft(aux);
			else
				parentNode.setRight(aux);
		}
		aux.setParent(parentNode);
		node.setParent(aux);	
		node.setLeft(rightAux);
		aux.setRight(node);
		
		if(!rightAux.isEmpty()) 
			rightAux.setParent(node);
		
		return aux;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
