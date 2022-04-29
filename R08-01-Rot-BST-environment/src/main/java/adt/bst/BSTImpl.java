package adt.bst;

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
		if (this.isEmpty())
			return -1;
		return heightRecursivo(this.root);
	}

	private int heightRecursivo(BSTNode<T> node) {
		if (!node.isEmpty()) {
			return 1 + Math.max(this.heightRecursivo((BSTNode<T>) node.getLeft()), this.heightRecursivo((BSTNode<T>) node.getRight()));
		}
		return -1;
	}

	@Override
	public BSTNode<T> search(T element) {
		if (this.isEmpty() || element == null)
			return new BSTNode<>();

		return searchRecursivo(element, this.root);
	}

	private BSTNode<T> searchRecursivo(T element, BSTNode<T> node) {
		if (node.isEmpty())
			return new BSTNode<>();

		T data = node.getData();
		if (element.equals(data))
			return node;
		if (element.compareTo(data) < 0)
			return searchRecursivo(element, (BSTNode<T>) node.getLeft());
		else
			return searchRecursivo(element, (BSTNode<T>) node.getRight());
	}

	@Override
	public void insert(T element) {
		if (element != null)
			insert(element, this.root);
	}

	private void insert(T element, BSTNode<T> node) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());
			node.setRight(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());
		} else {
			if (node.getData().compareTo(element) > 0) {
				insert(element, (BSTNode<T>) node.getLeft());
			}
			else {
				insert(element, (BSTNode<T>) node.getRight());
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if (this.root.isEmpty()) {
			return null;
		}
		else {
			return maximumRecursivo(this.root);
		}
	}

	private BSTNode<T> maximumRecursivo(BSTNode<T> node) {
		if (node.getRight().isEmpty()) {
			return node;
		}
		else {
			return maximumRecursivo((BSTNode<T>) node.getRight());
		}
	}

	@Override
	public BSTNode<T> minimum() {
		if (this.root.isEmpty()) {
			return null;
		} else {
			return minimumRecursivo(this.root);
		}
	}

	private BSTNode<T> minimumRecursivo(BSTNode<T> node) {
		if (node.getLeft().isEmpty()) {
			return node;
		} else {
			return minimumRecursivo((BSTNode<T>) node.getLeft());
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> result = search(element);

		if (element != null && !this.root.isEmpty() && !result.isEmpty()) {
			if (!result.getRight().isEmpty()) {
				result = minimumRecursivo((BSTNode<T>) result.getRight());
			} else {
				result = sucessorRecursivo(result);
			}
		} else {
			result = null;
		}
		return result;

	}

	private BSTNode<T> sucessorRecursivo(BSTNode<T> node) {
		BSTNode<T> sucessor = (BSTNode<T>) node.getParent();

		if (node.getParent() != null) {
			if (!sucessor.isEmpty() && sucessor.getRight().equals(node)) {
				sucessor = sucessorRecursivo((BSTNode<T>) node.getParent());
			}
		}
		return sucessor;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> result = search(element);

		if (element != null && !this.root.isEmpty() && !result.isEmpty()) {
			if (!result.getLeft().isEmpty()) {
				result = maximumRecursivo((BSTNode<T>) result.getLeft());
			} else {
				result = predecessorRecursivo(result);
			}
		} else {
			result = null;
		}
		return result;
	}

	private BSTNode<T> predecessorRecursivo(BSTNode<T> node) {
		BSTNode<T> predecessor = (BSTNode<T>) node.getParent();

		if (node.getParent() != null) {
			if (!predecessor.isEmpty() && predecessor.getLeft().equals(node)) {
				predecessor = predecessorRecursivo((BSTNode<T>) node.getParent());
			}
		}
		return predecessor;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);

		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
				node.setLeft(null);
				node.setRight(null);
			}
			else if (node.getRight().isEmpty() || node.getLeft().isEmpty()) {

				BSTNode<T> parent = (BSTNode<T>) node.getParent();

				if (parent != null) {
					if (!parent.getLeft().equals(node)) {
						if (!node.getLeft().isEmpty()) {
							parent.setRight(node.getLeft());
							node.getLeft().setParent(parent);
						} else {
							parent.setRight(node.getRight());
							node.getRight().setParent(parent);
						}
					} else {
						if (!node.getLeft().isEmpty()) {
							parent.setLeft(node.getLeft());
							node.getLeft().setParent(parent);
						} else {
							parent.setLeft(node.getRight());
							node.getRight().setParent(parent);
						}
					}
				} else {
					if (node.getLeft().isEmpty()) {
						this.root = (BSTNode<T>) node.getRight();
					} else {
						this.root = (BSTNode<T>) node.getLeft();
					}
					this.root.setParent(null);
				}
			} else {
				T sucessor = sucessor(node.getData()).getData();
				remove(sucessor);
				node.setData(sucessor);
			}
		}
	}

	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		preOrderRecursivo(this.root, 0, array);
		return array;
	}

	private void preOrderRecursivo(BSTNode<T> node, int i, T[] array) {
		if (!node.isEmpty()) {
			array[i] = node.getData();
			preOrderRecursivo((BSTNode<T>) node.getLeft(), i + 1, array);
			preOrderRecursivo((BSTNode<T>) node.getRight(), i + 1 + size((BSTNode<T>) node.getLeft()), array);
		}
	}

	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[this.size()];
		orderRecursivo(this.root, 0, array);
		return array;
	}

	private int orderRecursivo(BSTNode<T> node, int i, T[] array) {
		if (!node.isEmpty()) {
			i = orderRecursivo((BSTNode<T>) node.getLeft(), i, array);
			array[i++] = node.getData();
			i = orderRecursivo((BSTNode<T>) node.getRight(), i, array);
		}
		return i;
	}

	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		postOrder(this.root, 0, array);
		return array;
	}

	private int postOrder(BSTNode<T> node, int i, T[] array) {
		if (!node.isEmpty()) {
			i = postOrder((BSTNode<T>) node.getLeft(), i, array);
			i = postOrder((BSTNode<T>) node.getRight(), i, array);
			array[i++] = node.getData();
		}
		return i;
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
