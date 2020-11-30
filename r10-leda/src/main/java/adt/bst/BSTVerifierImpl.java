package adt.bst;

import adt.bt.BTNode;

/**
 * 
 * Performs consistency validations within a BST instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class BSTVerifierImpl<T extends Comparable<T>> implements BSTVerifier<T> {
	
	private BSTImpl<T> bst;

	public BSTVerifierImpl(BST<T> bst) {
		this.bst = (BSTImpl<T>) bst;
	}
	
	private BSTImpl<T> getBSt() {
		return bst;
	}

	@Override
	public boolean isBST() {
		return isBST(bst.getRoot(), bst.getRoot().getLeft(),bst.getRoot().getRight());
	}
	private boolean isBST(BSTNode<T> root, BTNode<T> left ,BTNode<T> right) {
		if(root.isEmpty()) 
			return true;
		if (!left.isEmpty() && root.getData().compareTo(left.getData()) <= 0)  
	        return false; 
		if (!right.isEmpty() && root.getData().compareTo(right.getData()) >= 0)  
	        return false; 
		return isBST((BSTNode<T>) root.getLeft(), left, root) 
				&& isBST((BSTNode<T>) root.getRight(), root, right);
	}
}
