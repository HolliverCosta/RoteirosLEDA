package adt.heap.extended;

import java.util.Comparator;

import adt.heap.HeapImpl;

public class FloorCeilHeapImpl extends HeapImpl<Integer> implements FloorCeilHeap {

	public FloorCeilHeapImpl(Comparator<Integer> comparator) {
		super(comparator);
	}

	@Override
	public Integer floor(Integer[] array, double numero) {
		Integer result = array[0];
		boolean achou = false;
		
		for(int i = 0 ;i<array.length-1;i++) {
			if(array[i] == numero || array[i] < numero) {
				result = array[i];
				achou = true;
			}
		}
		if(achou==false)result = null;
		return result;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		Integer result = array[0];
		boolean achou = false;
		for(int i = 0 ;i<array.length-1;i++) {
			if(array[i] == numero || array[i] > numero && array[i] < result) {
				result = array[i];
				achou = true;
			}
		}
		return result;
	}

}
