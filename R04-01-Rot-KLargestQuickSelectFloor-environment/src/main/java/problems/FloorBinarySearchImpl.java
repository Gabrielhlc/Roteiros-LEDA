package problems;

import static util.Util.swap;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		if (array.length > 0 && array != null) {
			quickSort(array, 0, array.length-1);
			return floorRecursivo(array, x, null, 0, array.length-1);
		}
		return null;
	}
	private Integer floorRecursivo(Integer[] array, Integer x, Integer floor, int left, int right) {
		if (left <= right && right < array.length && left >= 0) {
			int middle = (left + right) / 2;
			if (array[middle] == x) {
				return array[middle];
			}

			if (array[middle] > x) {
				return floorRecursivo(array, x, floor, left, middle -1);
			}
			if (array[middle] < x) {
				return floorRecursivo(array, x, array[middle], middle + 1, right);
			}
		}
		return floor;
	}
	private void quickSort(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex >= rightIndex || leftIndex < 0 || array.length == 0 || rightIndex > array.length-1)
			return;

		int index_pivot = particionamento(array, leftIndex, rightIndex);
		quickSort(array, leftIndex, index_pivot-1);
		quickSort(array, index_pivot+1, rightIndex);
	}

	private int particionamento(Integer[] array, int left, int right) {
		int middle = (left + right) / 2;
		medianaDeTres(array, left, middle, right);
		Integer pivot = array[middle];
		swap(array, middle, right -1);

		int i = right - 1;

		for (int j = i - 1; j > left; j--) {
			if (pivot.compareTo(array[j]) < 0) {
				i -= 1;
				swap(array, i, j);
			}
		}
		swap(array, left, i);
		return i;
	}

	private void medianaDeTres(Integer[] array, int left, int middle, int right) {
		if(array[left].compareTo(array[middle]) > 0) {
			swap(array,left, middle);
		}
		if(array[left].compareTo(array[right]) > 0) {
			swap(array, left, right);
		}
		if(array[middle].compareTo(array[right]) > 0) {
			swap(array,middle ,right );
		}
	}

}
