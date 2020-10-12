package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		int k = getMaiorElemento(array, leftIndex, rightIndex);
		int menor = getMenorElemento(array, leftIndex, rightIndex);

		if (leftIndex >= rightIndex || array.length == 0 || array.length == 1 || rightIndex > array.length
				|| leftIndex < 0 || rightIndex <= 0 || k >= 100)return;
		else
			countingSort(array, leftIndex, rightIndex, k, menor);

	}

	private Integer[] countingSort(Integer[] array, int leftIndex, int rightIndex, int k, int menor) {
		int range = k - menor + 1;

        int[] freq = new int[range];
        // contagem
        for (int i = leftIndex; i < rightIndex +1; i++)
            freq[array[i] - menor]++;

        //frequencia
        for (int i = 0; i < freq.length-1; i++)
            freq[i+1] += freq[i];

        //Ordena
        int[] arrayOrdenado = new int[array.length];
        for (int i = leftIndex; i < rightIndex+1; i++) {
            arrayOrdenado[freq[array[i]-menor]-1] = array[i];
            freq[array[i]-menor]--;
        }

        //Coloca no array original
        for (int i = leftIndex; i < rightIndex + 1; i++)
            array[i] = arrayOrdenado[i];

        return array;

	}

	private int getMaiorElemento(Integer[] array, int leftIndex, int rightIndex) {
		int maior = 0;
		if (array.length > 0) {
			maior = array[leftIndex];
			for (int i = leftIndex; i <= rightIndex; i++)
				if (array[i] > maior)
					maior = array[i];
		}
		return maior;

	}

	private int getMenorElemento(Integer[] array, int leftIndex, int rightIndex) {
		int menor = 0;
		if (array.length > 0) {
			menor = array[leftIndex];
			for (int i = leftIndex; i <= rightIndex; i++)
				if (array[i] < menor)
					menor = array[i];
		}
		return menor;
	}
}
