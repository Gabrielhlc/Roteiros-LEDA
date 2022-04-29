package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return - noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> pai = (BSTNode<T>) node.getParent();
		BSTNode<T> trocaDireita = (BSTNode<T>) node.getRight();
		if (!pai.isEmpty()) {
			if (pai.getLeft().equals(node)) {
				pai.setLeft(trocaDireita);
			}
			else {
				pai.setRight(trocaDireita);
			}
		}
		node.setRight(trocaDireita.getLeft());
		node.getRight().setParent(node);
		trocaDireita.setParent(node.getParent());
		trocaDireita.setLeft(node);
		trocaDireita.getLeft().setParent(trocaDireita);

		return trocaDireita;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> pai = (BSTNode<T>) node.getParent();
		BSTNode<T> pivot = (BSTNode<T>) node.getLeft();
		BSTNode<T> rightPivot = (BSTNode<T>) node.getRight();
		if (!pai.isEmpty()) {
			if (pai.getLeft().equals(node)) {
				pai.setLeft(pivot);

			}
			else {
				pai.setRight(pivot);
			}
		}
		node.setLeft(rightPivot);
		node.getLeft().setParent(node);
		pivot.setParent(pai);
		pivot.setRight(node);
		pivot.getRight().setParent(pivot);
		return pivot;
	}
	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		T[] array = (T[]) new Comparable[size];

		return array;
	}
}
