package orderStatistic;

import static util.Util.swap;

/**
 * O quickselect eh um algoritmo baseado no quicksort para
 * descobrir/selectionar, em tempo linear, a k-esima estatistica de ordem
 * (k-esimo menor elemento) de um conjunto de dados.
 * 
 * O quiskselect escolhe um elemento para ser o pivot e particiona o array
 * inicial em dois subarrays da mesma forma que o quicksort (elementos menores
 * que o pivot a esquerda do pivot e elementos maiores que o pivot a direita
 * dele). Entretanto, ao inves de chamar o quicksort recursivo nas duas metades,
 * o quickselect eh executado (recursivamente) apenas na metade que contem o
 * elemento que ele procura (o k-esimo menor elemento). Isso reduz a
 * complexidade de O(n.log n) para O(n)
 * 
 * @author Adalberto
 *
 */
public class QuickSelect<T extends Comparable<T>> {

	/**
	 * O algoritmo quickselect usa a mesma abordagem do quicksort para calclar o
	 * k-esimo menor elemento (k-esima estatistica de ordem) de um determinado
	 * array de dados comparaveis. Primeiro ele escolhe um elemento como o pivot
	 * e particiona os daods em duas partes baseado no pivot (exatemente da
	 * mesma forma que o quicksort). Depois disso, ele chama recursivamente o
	 * mesmo algoritmo em apenas uma das metades (a que contem o k-esimo menor
	 * elemento). Isso reduz a completixade de O(n.log n) para O(n).
	 * 
	 * Caso o array seja vazio ou a ordem (posicao) do elemento desejado esteja
	 * fora do tamanho do array, o metodo deve retornar null.
	 * 
	 * 
	 * @param array
	 *            o array de dados a procurar o k-esimo menor elemento.
	 * @param k
	 *            a ordem do elemento desejado. 1 significa primeiro menor
	 *            elemento, 2 significa segundo menor elemento e assim por
	 *            diante
	 * @return
	 */
	public T quickSelect(T[] array, int k) {
		if (array.length == 0 || k > array.length || k <= 0)
			return null;

		T element;
		element = quickSelectRecursivo(array, k, 0, array.length -1);

		return element;
	}

	private T quickSelectRecursivo(T[] array, int k, int left, int right) {

		if (left >= 0 && left < right && right < array.length) {
			int pivot = particionamento(array, left, right);

			if (k < pivot + 1)
				return quickSelectRecursivo(array, k, left, pivot-1);
			else if (k > pivot + 1)
				return quickSelectRecursivo(array, k, pivot+1, right);
			return array[pivot];
		}

		return array[k-1];
	}

	private int particionamento(T[] array, int left, int right) {
		int middle = (left + right) / 2;
		medianaDeTres(array, left, middle, right);

		T pivot = array[middle];
		swap(array, middle, right -1 );

		int i = right - 1;

		for (int j = i - 1; j > left; j--) {
			if (pivot.compareTo(array[j]) < 0) {
				i -= 1;
				swap(array, i, j);
			}
		}
		swap(array, right - 1, i);
		return i;
	}

	private void medianaDeTres(T[] array, int left, int middle, int right) {

		if (array[left].compareTo(array[middle]) > 0) {
			swap(array, left, middle);
		}
		if (array[left].compareTo(array[right]) > 0) {
			swap(array, left, right);
		}
		if (array[middle].compareTo(array[right]) > 0) {
			swap(array, middle, right);
		}
	}

}