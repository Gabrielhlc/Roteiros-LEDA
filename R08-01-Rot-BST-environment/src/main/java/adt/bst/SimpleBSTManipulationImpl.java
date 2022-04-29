package adt.bst;

/**
 * - Esta eh a unica classe que pode ser modificada 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements SimpleBSTManipulation<T> {

	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {
		boolean isEqual;
		if (tree1.size() != tree2.size()) {
			isEqual = false;
		}
		else if (tree1.isEmpty() && tree2.isEmpty()) {
			isEqual = true;
		} else {
			isEqual = equalsRecursivo((BSTNode<T>) tree1.getRoot(), (BSTNode<T>) tree2.getRoot());
		}
		return isEqual;
	}

	private boolean equalsRecursivo(BSTNode<T> node1, BSTNode<T> node2) {
		if (node1.isEmpty() && node2.isEmpty())
			return true;

		boolean isEqual = node1.equals(node2);
		if (isEqual) {
			boolean isLeftTreeEquals = this.equalsRecursivo((BSTNode<T>) node1.getLeft(), (BSTNode<T>)  node2.getLeft());
			boolean isRightTreeEquals = this.equalsRecursivo((BSTNode<T>) node1.getRight(), (BSTNode<T>) node2.getRight());
			isEqual = isLeftTreeEquals && isRightTreeEquals;
		}
		return isEqual;
	}

	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		boolean isSimilar;
		if (tree1.size() != tree2.size()) {
			isSimilar = false;
		} else if (tree1.isEmpty() && tree2.isEmpty()) {
			isSimilar = true;
		} else {
			isSimilar = this.isSimilarRecursivo((BSTNode<T>) tree1.getRoot(), (BSTNode<T>) tree2.getRoot());
		}
		return isSimilar;
	}

	private boolean isSimilarRecursivo(BSTNode<T> node1, BSTNode<T> node2) {
		if (node1.isEmpty() && node2.isEmpty())
			return true;

		boolean isSimilar = false;
		boolean isBothLeaf = node1.isLeaf() && node2.isLeaf();
		if (!isBothLeaf) {
			boolean leftTree = this.isSimilarRecursivo((BSTNode<T>) node1.getLeft(), (BSTNode<T>) node2.getLeft());
			boolean rightTree = this.isSimilarRecursivo((BSTNode<T>) node1.getRight() , (BSTNode<T>) node2.getRight());
			isSimilar = leftTree && rightTree;
		}
		return isSimilar;
	}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		if (tree == null || tree.isEmpty() || k > tree.size() || k < 1)
			return null;

		T order;

		if (k == tree.size()) {
			order = tree.maximum().getData();
		}
		else if (k == 1) {
			order = tree.minimum().getData();
		}
		else {
			order = this.orderStatistic(tree, tree.minimum(), 1, k);
		}
		return order;
	}

	private T orderStatistic(BST<T> tree, BSTNode<T> node, int start, int k) {
		if (start < k) {
			node = tree.sucessor(node.getData());
			return this.orderStatistic(tree, node, start + 1, k);
		}
		return node.getData();
	}

}
