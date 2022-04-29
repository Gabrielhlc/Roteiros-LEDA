package sorting.divideAndConquer.quicksort3;

import sorting.AbstractSorting;

import java.util.Arrays;


import static util.Util.swap;

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
 * funciona de forma ligeiramente diferente. Relembre que quando o pivô
 * escolhido divide o array aproximadamente na metade, o QuickSort tem um
 * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
 * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar o
 * pivô. Essa técnica consiste no seguinte:
 * 1. Comparar o elemento mais a esquerda, o central e o mais a direita do intervalo.
 * 2. Ordenar os elementos, tal que: A[left] < A[center] < A[right].
 * 3. Adotar o A[center] como pivô.
 * 4. Colocar o pivô na penúltima posição A[right-1].
 * 5. Aplicar o particionamento considerando o vetor menor, de A[left+1] até A[right-1].
 * 6. Aplicar o algoritmo na particao a esquerda e na particao a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends
		AbstractSorting<T> {

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex >= rightIndex || leftIndex < 0 || array.length == 0 || rightIndex > array.length-1)
			return;

		int index_pivot = particionamento(array, leftIndex, rightIndex);
		sort(array, leftIndex, index_pivot-1);
		sort(array, index_pivot+1, rightIndex);
	}

	private int particionamento(T[] array, int left, int right) {
		int pivot = medianOfThree(array, left, right);
		int i = pivot;

		for (int j = right - 2; j > left; j--) {
			if (array[j].compareTo(array[pivot]) >= 0) {
				i--;
				swap(array, i, j);
			}
		}
		swap(array, pivot, i);
		return i;
	}

	private int medianOfThree(T[] array, int left, int right) {
		int mid = (left + right) / 2;
		T esquerda = null, meio = null, direita = null;

		// O elemento [left] é o maior dos três elementos.
		if (array[left].compareTo(array[mid]) >= 0 && array[left].compareTo(array[right]) >= 0) {
			direita = array[left];
			if (array[mid].compareTo(array[right]) >= 0) {
				meio = array[mid];
				esquerda = array[right];
			} else {
				meio = array[right];
				esquerda = array[mid];
			}
		}
		// O elemento [mid] é o maior dos três elementos.
		else if (array[mid].compareTo(array[left]) >= 0 && array[mid].compareTo(array[right]) >= 0) {
			direita = array[mid];
			if (array[right].compareTo(array[left]) >= 0) {
				meio = array[right];
				esquerda = array[left];
			}
			else {
				meio = array[left];
				esquerda = array[right];
			}
		}
		// O elemento [right] é o maior dos três elementos.
		else {
			direita = array[right];
			if (array[left].compareTo(array[mid]) >= 0) {
				meio = array[left];
				esquerda = array[mid];
			}
			else {
				meio = array[mid];
				esquerda = array[left];
			}
		}
		array[left] = esquerda;
		array[mid] = meio;
		array[right] = direita;
		swap(array, mid, right-1);
		return right-1;
	}
}
