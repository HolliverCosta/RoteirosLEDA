package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		if(leftIndex >= rightIndex || array.length == 0 || array.length==1 || rightIndex > array.length || leftIndex < 0 || rightIndex<=0)return;
		
		else {
			int middle = (leftIndex + rightIndex)/2;
			sort(array,leftIndex,middle);
			sort(array,middle + 1,rightIndex);
			
			merge(array,leftIndex,middle,rightIndex);
			
		}
	}
	public void merge(T[] array,int left,int middle,int right) {
		Integer[] array_aux =  new Integer[array.length];
		
		for(int i =left;i<=right;i++) 
			array_aux[i] = (Integer) array[i];
		
		int i = left;
		int j = middle + 1 ;
		int k = left;
		
		while(i<=middle && j<=right) {
			if(array_aux[i].compareTo(array_aux[j])<0) {
				array[k] = (T) array_aux[i++];
			}else {
				array[k] = (T) array_aux[j++];
			}
			k++;
		}
		while(i<=middle) {
			array[k++] = (T) array_aux[i++];
		}
		while(j<=right) {
			array[k++] = (T) array_aux[j++];
		}
	}
}
