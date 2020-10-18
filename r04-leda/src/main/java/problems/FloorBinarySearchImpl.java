package problems;

import util.Util;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		return getFloor(array,0,array.length-1,x);	
	}

	private Integer getFloor(Integer[] array, Integer left, Integer right, Integer x) {
		sort(array,left,right);
		
		if (x < array[left] || array.length==0) return null;
		
		if(x>=array[right])
			return array[right];
		
		int middle = (left + right) / 2;
		if(middle == left)
			return array[left];
		if(array[middle] == x)
			return array[middle];
		else if(array[middle]<x)
			return getFloor(array,middle,right,x);
		else
			return getFloor(array,left,middle -1,x);
	}

	private void sort(Integer[] array, int leftIndex, int rightIndex) {

		if (leftIndex >= rightIndex || array.length == 0 || array.length == 1 || rightIndex > array.length
				|| leftIndex < 0 || rightIndex <= 0)
			return;

		if (leftIndex < rightIndex) {
			int index_pivot = selectPivot(array, leftIndex, rightIndex);
			sort(array, leftIndex, index_pivot - 1);
			sort(array, index_pivot + 1, rightIndex);
		}
	}

	private int selectPivot(Integer[] array, int left, int right) {
		int middle = (left + right) / 2;
		int lPivot = left;
		int rPivot = right - 2;

		if (array[middle].compareTo(array[left]) < 0)
			Util.swap(array, middle, left);
		if (array[right].compareTo(array[left]) < 0)
			Util.swap(array, right, left);
		if (array[right].compareTo(array[middle]) < 0)
			Util.swap(array, right, middle);

		Util.swap(array, left, right - 1);

		Integer pivot = array[right - 1];

		while (lPivot <= rPivot) {
			if (array[lPivot].compareTo(pivot) <= 0)
				lPivot++;
			else if (array[rPivot].compareTo(pivot) > 0)
				rPivot--;
			else {
				Util.swap(array, lPivot, rPivot);
				lPivot++;
				rPivot--;
			}
		}
		Util.swap(array, right - 1, lPivot);
		return lPivot;
	}

}
