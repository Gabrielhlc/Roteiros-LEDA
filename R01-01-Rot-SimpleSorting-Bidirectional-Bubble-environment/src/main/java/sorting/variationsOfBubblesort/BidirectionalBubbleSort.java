package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;

import static util.Util.swap;

/**
 * This bubble sort variation has two internal iterations. In the first, it
 * pushes big elements to the right, like the normal bubble sort does. Then in
 * the second, iterates the array backwards, that is, from right to left, while
 * pushing small elements to the left. This process is repeated until the array
 * is sorted.
 */
public class BidirectionalBubbleSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

		if (leftIndex < 0 || rightIndex > array.length-1)
			return;
		int troca = 0;
		while (troca == 0 && leftIndex < rightIndex) {

			troca = 1;
			for (int i = leftIndex; i < rightIndex; i++) {
				if (array[i].compareTo(array[i+1]) > 0) {
					swap(array, i, i+1);
					troca = 0;
				}
			}
			rightIndex--;
			for (int i = rightIndex; i > leftIndex; i--) {
				if (array[i].compareTo(array[i-1]) < 0) {
					swap(array, i, i-1);
					troca = 0;
				}
			}
			leftIndex++;
		}
	}
}
