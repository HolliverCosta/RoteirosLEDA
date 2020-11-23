package adt.heap.extended;

import adt.heap.ComparatorMaxHeap;
import adt.heap.ComparatorMinHeap;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class FloorCeilHeapImplTest {
    FloorCeilHeapImpl heap;

    @Before
    public void setUp() {
        // TODO Instancie seu comparator para fazer sua estrutura funcionar como
        // uma min heap aqui. Use instanciacao anonima da interface
        // Comparator!!!!
//        Comparator<Integer> comparator = new ComparatorMaxHeap<Integer>();
        Comparator<Integer> comparator = new ComparatorMinHeap<Integer>();
        heap = new FloorCeilHeapImpl(comparator);
    }

    @Test
    public void testFloor() {
        Integer[] array = new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 };

        assertNull(this.heap.floor(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 },-11));
        assertNull(this.heap.floor(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 },4));
        assertNull(this.heap.floor(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 },0));
        assertNull(this.heap.floor(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 },4.9));


        assertEquals(new Integer(5), this.heap.floor(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 },5));
        assertEquals(new Integer(5), this.heap.floor(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 },5.1));
        assertEquals(new Integer(5), this.heap.floor(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 8 },6));
        assertEquals(new Integer(20), this.heap.floor(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 },21));
        assertEquals(new Integer(92), this.heap.floor(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 },500));
        assertEquals(new Integer(92), this.heap.floor(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 },93));
        assertEquals(new Integer(92), this.heap.floor(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 },92));
        assertEquals(new Integer(12), this.heap.floor(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 12 },12));

    }

    @Test
    public void testFloor2() {
        Integer[] array = new Integer[] { 8,8,8,8,5,8,8,8 };

        assertNull(this.heap.floor(array,-11));
        assertNull(this.heap.floor(array,4));
        assertNull(this.heap.floor(array,0));
        assertNull(this.heap.floor(array,4.9));


        assertEquals(new Integer(5), this.heap.floor(array,5));
        assertEquals(new Integer(5), this.heap.floor(array,5.1));
        assertEquals(new Integer(5), this.heap.floor(array,6));
        assertEquals(new Integer(5), this.heap.floor(array,7));
        assertEquals(new Integer(8), this.heap.floor(array,8));
        assertEquals(new Integer(8), this.heap.floor(array,93));
        assertEquals(new Integer(8), this.heap.floor(array,9));

    }

    @Test
    public void testCeil() {
        Integer[] array = new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 };

        assertNull(this.heap.ceil(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 },93));
        assertNull(this.heap.ceil(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 },10000));
        assertNull(this.heap.ceil(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 },100));
        assertNull(this.heap.ceil(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 },95));


        assertEquals(new Integer(5), this.heap.ceil(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 },5));
        assertEquals(new Integer(6), this.heap.ceil(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 },5.1));
        assertEquals(new Integer(8), this.heap.ceil(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 8 },6));
        assertEquals(new Integer(34), this.heap.ceil(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 },21));
        assertEquals(new Integer(5), this.heap.ceil(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 },0));
        assertEquals(new Integer(5), this.heap.ceil(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 },-12));
        assertEquals(new Integer(92), this.heap.ceil(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 },92));
        assertEquals(new Integer(12), this.heap.ceil(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 12 },12));

    }

    @Test
    public void testeCeil2() {
        Integer[] array = new Integer[] { 8,8,8,8,5,8,8,8 };

        assertNull(this.heap.ceil(array,9));
        assertNull(this.heap.ceil(array,10));
        assertNull(this.heap.ceil(array,11));
        assertNull(this.heap.ceil(array,12));
        assertNull(this.heap.ceil(array,50));


        assertEquals(new Integer(5), this.heap.ceil(array,5));
        assertEquals(new Integer(5), this.heap.ceil(array,4.9));
        assertEquals(new Integer(8), this.heap.ceil(array,6));
        assertEquals(new Integer(8), this.heap.ceil(array,7));
        assertEquals(new Integer(8), this.heap.ceil(array,8));
        assertEquals(new Integer(5), this.heap.ceil(array,0));
        assertEquals(new Integer(5), this.heap.ceil(array,-10));
    }
}
