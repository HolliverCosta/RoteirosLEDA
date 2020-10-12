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
	
	public void sort(Integer[] array, int leftIndex, int rightIndex) {	
		int k = getMaiorElemento(array, leftIndex, rightIndex);
	
		if(leftIndex >= rightIndex || array.length == 0 || array.length==1 || rightIndex > array.length
				|| leftIndex < 0 || rightIndex<=0 || k>=100)return;
		else
			countingSort(array, leftIndex, rightIndex, k);

	}
	private Integer[] countingSort(Integer[] array, int leftIndex, int rightIndex,int k) {
		int[] freq = new int[k+1];
		for (int i = leftIndex; i < rightIndex+1; i++) 
			freq[array[i]] += 1;
		
		for (int i = 1; i < freq.length; i++) 
			freq[i] += freq[i-1];
		
		int[] arrayOrdenado = new int[array.length];
		for (int i = rightIndex; i >= leftIndex; i--) {
			arrayOrdenado[freq[array[i]]-1] = array[i];
			freq[array[i]] -= 1;
		}
		for (int i = leftIndex; i < rightIndex+1; i++) 
			array[i] = arrayOrdenado[i];
		
		
		return array;
	}
	private int getMaiorElemento(Integer[] array, int leftIndex, int rightIndex) {
		Integer maior = 0;
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i] > maior)
				maior = array[i];
		}
		return maior;
	}
}

