package orderStatistic;

import util.Util;

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
	 * elemento). Isso redua a completixade de O(n.log n) para O(n).
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
		if(array.length == 0 || k>array.length || k<=0 ) return null;
		return select(array,0,array.length-1,k);
	}
	private T select(T[]array, int left, int right,int k) {
		int pivot = partition(array,left,right);
		
		if(pivot + 1 == k)return array[pivot];
		
		else if(pivot + 1 <k)
			return select(array,pivot + 1,right,k);
		else
			return select(array,left,pivot -1,k);
		
	}
	private int partition(T[] array, int left,int right) {
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
