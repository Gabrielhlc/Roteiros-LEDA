package sorting.divideAndConquer.hybridMergesort;

import sorting.AbstractSorting;

import static util.Util.swap;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 *   que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados. E a cada chamada
 *   interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 *   INSERTIONSORT_APPLICATIONS são incrementados.
 * - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		MERGESORT_APPLICATIONS = 0;
		INSERTIONSORT_APPLICATIONS = 0;
		hybrid(array, leftIndex, rightIndex);
	}

	public void hybrid(T[] array, int left, int right) {
		if (left < 0 || array.length == 0 || right > array.length-1)
			return;

		if (left - right <= SIZE_LIMIT) {
			if (left < 0 || right > array.length-1)
				return;

			for (int i = left+1; i <= right; i++) {
				int j = i;

				while (j > left && array[j].compareTo(array[j - 1]) < 0) {
					swap(array, j, j - 1);
					j--;
				}
			}
			INSERTIONSORT_APPLICATIONS++;
		}
		else {
			int middle = (left + right) / 2;
			MERGESORT_APPLICATIONS++;
			hybrid(array, left, middle);
			hybrid(array, middle + 1, right);
			merge(array, left, middle, right);
		}
	}
	private void merge(T[] array, int left, int middle, int right) {
		T[] aux = (T[]) new Comparable[array.length];
		for (int i = left; i <= right; i++) {
			aux[i] = array[i];
		}

		int i = left;
		int j = middle + 1;
		int k = left;
		while (i <= middle && j <= right) {

			if (aux[i].compareTo(aux[j]) < 0) {
				array[k] = aux[i];
				i++;
			} else {
				array[k] = aux[j];
				j++;
			}
			k++;
		}
		while (i <= middle) {
			array[k] = aux[i];
			i++;
			k++;
		}
		while (j <= right) {
			array[k] = aux[j];
			j++;
			k++;
		}
	}
}
