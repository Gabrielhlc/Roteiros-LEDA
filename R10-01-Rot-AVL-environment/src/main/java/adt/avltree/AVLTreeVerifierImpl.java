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
		boolean isAVL;
		if (this.avlTree.isEmpty()) {
			isAVL = true;
		}
		else {
			isAVL = isAVLTree(this.avlTree.getRoot());
		}
		return isBST() && isAVL;
	}

	private boolean isAVLTree(BSTNode<T> node) {
		if (node.isEmpty()) {
			return true;
		}
		boolean isAVLLeft = false;
		boolean isAVLRight = false;

		int balance = this.avlTree.calculateBalance(node);
		if (balance <= 1 && balance >= -1) {
			isAVLLeft = isAVLTree((BSTNode<T>) node.getLeft());
			isAVLRight = isAVLTree((BSTNode<T>) node.getRight());
		}
		return isAVLLeft && isAVLRight;
	}
}
