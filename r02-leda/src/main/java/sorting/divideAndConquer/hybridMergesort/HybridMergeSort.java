package sorting.divideAndConquer.hybridMergesort;

import sorting.AbstractSorting;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes: - Ter
 * contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 * que essa informação possa ser capturada pelo teste. - A cada chamado do
 * método de sort(T[] array) esses contadores são resetados. E a cada chamada
 * interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 * INSERTIONSORT_APPLICATIONS são incrementados. - O InsertionSort utilizado no
 * algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort algorithm
	 * will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		INSERTIONSORT_APPLICATIONS= 0;
		MERGESORT_APPLICATIONS = 0;
		
		if (leftIndex >= rightIndex || array.length == 0 || array.length==1 || rightIndex > array.length || leftIndex < 0 || rightIndex<=0) return;
		
		else if (rightIndex - leftIndex <= SIZE_LIMIT) {
			insertionSort(array, leftIndex, rightIndex);
			
		} else {
			int middle = (leftIndex + rightIndex) / 2;
			sort(array, leftIndex, middle);
			sort(array, middle + 1, rightIndex);

			mergeSort(array, leftIndex, middle, rightIndex);
		}
	}

	public void mergeSort(T[] array, int left, int middle, int right) {
		MERGESORT_APPLICATIONS++;
		Integer[] array_aux = new Integer[array.length];

		for (int i = left; i <= right; i++)
			array_aux[i] = (Integer) array[i];

		int i = left;
		int j = middle + 1;
		int k = left;

		while (i <= middle && j <= right) {
			if (array_aux[i].compareTo(array_aux[j]) < 0) {
				array[k] = (T) array_aux[i++];
			} else {
				array[k] = (T) array_aux[j++];
			}
			k++;
		}
		while (i <= middle) {
			array[k++] = (T) array_aux[i++];
		}
		while (j <= right) {
			array[k++] = (T) array_aux[j++];
		}
	}

	public void insertionSort(T[] array, int leftIndex,int rightIndex) {
		INSERTIONSORT_APPLICATIONS++;
		T key;
		for(int i=1;i<array.length;i++) {
			key = array[i];
			int j = i-1;
			while(j>-1 && array[j].compareTo(key) > 0) {
				array[j+1] = array[j];
				j-=1;
			}
			array[j+1]=key;
		}
	}
}
