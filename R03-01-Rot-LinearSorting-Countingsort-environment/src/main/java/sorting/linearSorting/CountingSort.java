package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Voce pode assumir que o maior inteiro armazenado não chega a 100.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length <= 0 || leftIndex < 0 || rightIndex >= array.length)
			return;

		int maior = array[leftIndex];

		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i] > maior)
				maior = array[i];
		}

		int[] C = new int[maior + 1];

		for (int i = leftIndex; i <= rightIndex; i++) {
			C[array[i]] += 1;
		}

		for (int i = 1; i < C.length; i++) {
			C[i] += C[i-1];
		}

		int[] B = new int[rightIndex - leftIndex + 1];

		for (int i = rightIndex; i >= leftIndex; i--) {
			B[C[array[i]] - 1] = array[i];
			C[array[i]] -= 1;
		}
		int j = 0;
		for (int i = leftIndex; i <= rightIndex; i++) {
			array[i] = B[j];
			j++;
		}
	}

}
