package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;
import adt.bt.Util;

/**
 * 
 * Implementacao de uma arvore AVL
 * A CLASSE AVLTree herda de BSTImpl. VOCE PRECISA SOBRESCREVER A IMPLEMENTACAO
 * DE BSTIMPL RECEBIDA COM SUA IMPLEMENTACAO "OU ENTAO" IMPLEMENTAR OS SEGUITNES
 * METODOS QUE SERAO TESTADOS NA CLASSE AVLTREE:
 *  - insert
 *  - preOrder
 *  - postOrder
 *  - remove
 *  - height
 *  - size
 *
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.
	@Override
	public void remove(T element) {
		BSTNode<T> node = super.search(element);
		if(!node.isEmpty()) {
			super.remove(element);
			rebalanceUp(node);
		}
	}
	@Override
	public void insert(T element) {
		insert(getRoot(), element);
	}

	private void insert(BSTNode<T> node, T element) {
		if(node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.setRight(new BSTNode<T>());
			node.getRight().setParent(node);
			this.root.setParent(new BSTNode<T>());
		}
		else {
			if (element.compareTo(node.getData()) < 0) {
				insert((BSTNode<T>) node.getLeft(), element);
			} else if(element.compareTo(node.getData()) > 0){
				insert((BSTNode<T>) node.getRight(), element);
			}
		}

		this.rebalance(node);
	}
	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		// TODO Auto-generated method stub
		return altura(node.getLeft()) - altura(node.getRight());
	}
	private int altura(BTNode<T> no) {
		if(no.isEmpty()) {
			return -1;
		}
		return Math.max(altura(no.getLeft()), altura(no.getRight())) + 1;
	}
	// AUXILIARY

	protected void rebalance(BSTNode<T> node) {
		BSTNode<T> aux;

		if (calculateBalance(node) > 1) {

			if (calculateBalance((BSTNode<T>) node.getLeft()) >= 0) {
				aux = Util.rightRotation(node);
			} else {
				Util.leftRotation((BSTNode<T>) node.getLeft());
				aux = Util.rightRotation(node);
			}

			if (aux.getParent().isEmpty()) {
				root = aux;
			}
		} else if (calculateBalance(node) < -1) {
			if (calculateBalance((BSTNode<T>) node.getRight()) <= 0) {
				aux = Util.leftRotation(node);
			} else {
				Util.rightRotation((BSTNode<T>) node.getRight());
				aux = Util.leftRotation(node);

			}

			if (aux.getParent().isEmpty()) {
				root = aux;
			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		// TODO Auto-generated method stub

		BTNode<T> parent = node.getParent();
		while(!parent.isEmpty()) {
			rebalance((BSTNode<T>) parent);
			parent = parent.getParent();
		}
	}
}
