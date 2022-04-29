package adt.bst;

import java.util.ArrayList;
import java.util.List;

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
		return height(this.root);
	}

	private int height(BSTNode<T> no) {
		if(no.isEmpty()) {
			return -1;
		}
		return Math.max(height((BSTNode<T>) no.getLeft()), height((BSTNode<T>) no.getRight())) + 1;
	}

	@Override
	public BSTNode<T> search(T element) {
		if(isEmpty()) {
			return new BSTNode<T>();
		}
		return search(element,this.root);
	}
	private BSTNode<T> search(T element,BSTNode<T> no ){

		if(no.isEmpty()) {
			return new BSTNode<T>();
		}
		if( element.compareTo(no.getData()) == 0) {
			return no;
		}
		if(element.compareTo(no.getData()) < 0) {
			return search(element,(BSTNode<T>) no.getLeft());
		}else {
			return search(element, (BSTNode<T>) no.getRight());
		}
	}

	@Override
	public void insert(T element) {
		insert(element,this.root,new BSTNode<T>());
	}
	private void insert(T element,BSTNode<T> no,BSTNode<T> pai  ) {
		if (no.isEmpty()) {
			no.setData(element);
			no.setLeft(new BSTNode<T>());
			no.getLeft().setParent(no);
			no.setRight(new BSTNode<T>());
			no.getRight().setParent(no);
		} else {
			if (element.compareTo(no.getData()) > 0) {
				insert(element, (BSTNode<T>) no.getRight(), (BSTNode<T>) no.getParent());
			}
			else {
				if (element.compareTo(no.getData()) < 0)
				insert(element, (BSTNode<T>) no.getLeft(), (BSTNode<T>) no.getParent());
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if(isEmpty()) {
			return null;
		}
		return maximum(this.root);
	}

	private BSTNode<T> maximum(BSTNode<T> no) {
		if (!no.getRight().isEmpty()) {
			return maximum((BSTNode<T>) no.getRight());
		} else {
			return no;
		}
	}

	@Override
	public BSTNode<T> minimum() {
		if(isEmpty()) {
			return null;
		}
		return minimum(this.root);
	}
	private BSTNode<T> minimum(BSTNode<T> no) {
		if(no.getLeft().isEmpty()) {
			return no;
		}
		return minimum((BSTNode<T>) no.getLeft());
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = this.search(element);
		if (!node.isEmpty()) {
			if (!node.getRight().isEmpty()) {
				return minimum((BSTNode<T>) node.getRight());
			}
			else {
				BSTNode<T> parentNode = (BSTNode<T>) node.getParent();

				while (parentNode != null && parentNode.getData().compareTo(node.getData()) < 0) {
					node = parentNode;
					parentNode = (BSTNode<T>) node.getParent();
				}
				return parentNode;
			}
		}
		return null;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> no = this.search(element);
		if (!no.isEmpty()) {
			if (!no.getLeft().isEmpty()) {
				return maximum((BSTNode<T>) no.getLeft());
			}
			else {
				BSTNode<T> pai = (BSTNode<T>) no.getParent();

				while (pai != null && pai.getData().compareTo(no.getData()) > 0) {
					no = pai;
					pai = (BSTNode<T>) no.getParent();
				}
				return pai;
			}
		}
		return null;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> no = search(element);

		if (!no.isEmpty()) {
			if (no.isLeaf()) {
				if(no.equals(this.root)) {
					this.root = new BSTNode<T>();
				}
				else {
					if(no.getData().compareTo(no.getParent().getData()) < 0) {
						no.getParent().setLeft(new BSTNode<T>());
					}
					else {
						no.getParent().setRight(new BSTNode<T>());
					}
				}
			}
			else if (UmFilho(no)) {
				if (no.getParent() != null) {
					if (!no.getParent().getLeft().equals(no)) {
						if (!no.getLeft().isEmpty()) {
							no.getParent().setRight(no.getLeft());
							no.getLeft().setParent(no.getParent());
						} else {
							no.getParent().setRight(no.getRight());
							no.getRight().setParent(no.getParent());
						}
					}
					else {
						if (!no.getLeft().isEmpty()) {
							no.getParent().setLeft(no.getLeft());
							no.getLeft().setParent(no.getParent());
						}
						else {
							no.getParent().setLeft(no.getRight());
							no.getRight().setParent(no.getParent());
						}
					}
				}
				else {
					if (no.getLeft().isEmpty()) {
						this.root = (BSTNode<T>) no.getRight();
					} else {
						this.root = (BSTNode<T>) no.getLeft();
					}
					this.root.setParent(new BSTNode<T>());
				}
			}
			else {
				T sucessorNode = sucessor(no.getData()).getData();
				remove(sucessorNode);
				no.setData(sucessorNode);
			}
		}
	}
	private boolean UmFilho(BSTNode<T> node) {
		return ((node.getRight().isEmpty() && !node.getLeft().isEmpty()
				|| node.getLeft().isEmpty() && !node.getRight().isEmpty()));
	}

	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[size()];

		List<T> aux = new ArrayList<T>();
		if (!getRoot().isEmpty()) {
			preOrder(this.root, aux);
		}
		aux.toArray(array);
		return array;
	}
	private void preOrder(BSTNode<T> node, List<T> array ) {
		if (!node.isEmpty()) {
			array.add(node.getData());
			preOrder((BSTNode<T>) node.getLeft(), array);
			preOrder((BSTNode<T>) node.getRight(), array);
		}
	}

	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[size()];

		List<T> aux = new ArrayList<T>();

		if(!getRoot().isEmpty()) {
			order(this.root,aux);
		}
		aux.toArray(array);
		return array;
	}
	private void order(BSTNode<T> node,List<T> array) {
		if (!node.isEmpty()) {
			order((BSTNode<T>) node.getLeft(), array);
			array.add(node.getData());
			order((BSTNode<T>) node.getRight(), array);
		}
	}

	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[size()];

		List<T> aux = new ArrayList<T>();
		if(!getRoot().isEmpty()) {
			postOrder(this.root, aux);
		}
		aux.toArray(array);
		return array;
	}
	private void postOrder(BSTNode<T> node, List<T> array) {
		if (!node.isEmpty()) {
			postOrder((BSTNode<T>) node.getLeft(), array);
			postOrder((BSTNode<T>) node.getRight(), array);
			array.add(node.getData());
		}
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
