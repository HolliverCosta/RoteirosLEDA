package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;
/**
 * This bubble sort variation has two internal iterations. In the first, it
 * pushes big elements to the right, like the normal bubble sort does. Then in
 * the second, iterates the array backwards, that is, from right to left, while
 * pushing small elements to the left. This process is repeated until the array
 * is sorted.
 */
public class BidirectionalBubbleSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int left = 0;
		int right = array.length-1;
		boolean swapped = true;
		while(left<right && swapped == true) {
			swapped  =false;
			for(int i = left;i<right;i++) {
				if(array[i].compareTo(array[i+1])>0) {
					Util.swap(array,i,i+1);
					swapped  =true;
				}
				
			}
			right--;
			
			for(int i=right;i>left;i--) {
				if(array[i].compareTo(array[i-1])<0) {
					Util.swap(array,i,i-1);
					swapped = true;
				}
			}
			left++;
		}
	}
}
