package adt.avltree;

import adt.bst.BSTNode;
import adt.bst.BSTVerifierImpl;

/**
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeVerifierImpl<T extends Comparable<T>> extends BSTVerifierImpl<T> implements AVLTreeVerifier<T> {

	private AVLTreeImpl<T> avlTree;

	public AVLTreeVerifierImpl(AVLTree<T> avlTree) {
		super(avlTree);
		this.avlTree = (AVLTreeImpl<T>) avlTree;
	}

	private AVLTreeImpl<T> getAVLTree() {
		return avlTree;
	}

	@Override
	public boolean isAVLTree() {
		return super.isBST() && this.isAVLTree(this.avlTree.getRoot());

		// return isBST() && ...
	}
	private boolean isAVLTree(BSTNode<T> node) {
		if(node.isEmpty())return true;
		if(node.getLeft().isEmpty() && node.getRight().isEmpty())
			return true;
		if((!node.isEmpty()) && (!node.getRight().isEmpty())) 
			return (isAVLTree((BSTNode<T>) node.getLeft())) 
					&& (isAVLTree((BSTNode<T>) node.getRight()));
		return false;
	}
}
