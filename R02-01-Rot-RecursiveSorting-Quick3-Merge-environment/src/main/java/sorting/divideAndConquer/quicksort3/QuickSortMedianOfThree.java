package sorting.divideAndConquer.quicksort3;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
 * funciona de forma ligeiramente diferente. Relembre que quando o pivô
 * escolhido divide o array aproximadamente na metade, o QuickSort tem um
 * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
 * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar o
 * pivô. Essa técnica consiste no seguinte:
 * 1. Comparar o elemento mais a esquerda, o central e o mais a direita do intervalo.
 * 2. Ordenar os elementos, tal que: A[left] < A[center] < A[right].
 * 3. Adotar o A[center] como pivô.
 * 4. Colocar o pivô na penúltima posição A[right-1].
 * 5. Aplicar o particionamento considerando o vetor menor, de A[left+1] até A[right-1].
 * 6. Aplicar o algoritmo na particao a esquerda e na particao a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends
		AbstractSorting<T> {
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

		if (leftIndex >= rightIndex || array.length == 0 || array.length==1 || rightIndex > array.length || leftIndex < 0 || rightIndex<=0)return;

		if (leftIndex < rightIndex) {
			int index_pivot = selectPivot(array, leftIndex, rightIndex);
			sort(array, leftIndex, index_pivot - 1);
			sort(array, index_pivot + 1, rightIndex);
		}
	}
	
	private int selectPivot(T[] array, int left,int right) {
		int middle = (left + right) / 2;
		int lPivot = left;
		int rPivot = right -2;
		
		if(array[middle].compareTo(array[left])<0) 
			Util.swap(array,middle,left);
		if(array[right].compareTo(array[left])<0)
			Util.swap(array, right, left);
		if(array[right].compareTo(array[middle])<0) 
			Util.swap(array, right, middle);
		
		Util.swap(array, left, right-1);
		
		T pivot = array[right-1];
		
		while(lPivot<=rPivot) {
			if(array[lPivot].compareTo(pivot)<=0)
				lPivot++;
			else if(array[rPivot].compareTo(pivot)>0)
				rPivot--;
			else {
				Util.swap(array, lPivot, rPivot);
				lPivot++;
				rPivot--;
			}
		}
		Util.swap(array, right-1, lPivot);
		return lPivot;
	}
}
