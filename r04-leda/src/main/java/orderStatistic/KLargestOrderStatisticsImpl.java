package orderStatistic;

import util.Util;

/**
 * Uma implementacao da interface KLargest que usa estatisticas de ordem para 
 * retornar um array com os k maiores elementos de um conjunto de dados/array.
 * 
 * A k-esima estatistica de ordem de um conjunto de dados eh o k-esimo menor
 * elemento desse conjunto. Por exemplo, considere o array [4,8,6,9,12,1]. 
 * A 3a estatistica de ordem eh 6, a 6a estatistica de ordem eh 12.
 * 
 * Note que, para selecionar os k maiores elementos, pode-se pegar todas as 
 * estatisticas de ordem maiores que k. 
 * 
 * Requisitos do algoritmo:
 * - DEVE ser in-place. Voce pode modificar o array original
 * - Voce DEVE usar alguma ideia de algoritmo de ordenacao visto em sala 
 *   para calcular estatisticas de ordem. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class KLargestOrderStatisticsImpl<T extends Comparable<T>> implements KLargest<T>{

	@Override
	public T[] getKLargest(T[] array, int k) {
		if(k<=0 || array.length == 0 || k>array.length)return null;
		
		T[] arrayMaiores = (T[]) new Comparable[k];
		
		int left = array.length - k;
		int contador = 0;
		for(int i = left; i<array.length;i++) {
			arrayMaiores[contador] = orderStatistics(array, i+1);
			contador++;
		}
		return arrayMaiores;
		//este metodo deve obrigatoriamente usar o orderStatistics abaixo.
	}

	/**
	 * Metodo que retorna a k-esima estatistica de ordem de um array, usando
	 * a ideia de algum algoritmo de ordenacao visto em sala. Caso nao exista 
	 * a k-esima estatistica de ordem, entao retorna null.
	 * 
	 * Obs: o valor de k deve ser entre 1 e N.
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	public T orderStatistics(T[] array, int k){
		if(array.length == 0 || k>array.length) return null;
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
