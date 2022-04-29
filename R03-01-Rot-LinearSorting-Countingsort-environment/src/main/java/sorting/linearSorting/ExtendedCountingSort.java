package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length <= 0 || leftIndex < 0 || rightIndex < 0 || rightIndex >= array.length || leftIndex > rightIndex)
			return;

		int maior = array[0];
		int menor = array[0];

		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i] > maior)
				maior = array[i];
			if (array[i] < menor)
				menor = array[i];
		}

		int[] C = new int[maior - menor + 1];

		for (int i = leftIndex; i <= rightIndex; i++) {
			C[array[i] - menor] += 1;
		}

		for (int i = 1; i < C.length; i++) {
			C[i] += C[i-1];
		}

		int[] B = new int[rightIndex - leftIndex +1];

		for (int i = rightIndex; i >= leftIndex; i--) {
			B[C[array[i] - menor] - 1] = array[i];
			C[array[i] - menor] -= 1;
		}
		int j = 0;
		for (int i = leftIndex; i <= rightIndex; i++) {
			array[i] = B[j];
			j++;
		}
	}

}
