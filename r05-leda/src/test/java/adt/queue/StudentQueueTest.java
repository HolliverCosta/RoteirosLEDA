package adt.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class StudentQueueTest {

	public Queue<Integer> queue1;
	public Queue<Integer> queue2;
	public Queue<Integer> queue3;

	@Before
	public void setUp() throws QueueOverflowException {

		getImplementations();

		// Fila com 3 elementos não cheia.
		queue1.enqueue(1);
		queue1.enqueue(2);
		queue1.enqueue(3);

		// Fila com 2 elementos de tamanho 2. Fila cheia.
		queue2.enqueue(1);
		queue2.enqueue(2);

	}

	private void getImplementations() {
		queue1 = new QueueImpl<>(4);
		queue2 = new QueueImpl<>(2);
		queue3 = new QueueImpl<>(0);
	}

	// MÉTODOS DE TESTE
	@Test
	public void testHead() {
		assertEquals((Integer) 1, queue1.head());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(queue1.isEmpty());
		assertTrue(queue3.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertTrue(queue2.isFull());
	}

	@Test
	public void testEnqueue() {
		try {
			queue1.enqueue((Integer) 5);
		} catch (QueueOverflowException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = QueueOverflowException.class)
	public void testEnqueueComErro() throws QueueOverflowException {
		queue2.enqueue((Integer) 5); // vai depender do tamanho que a fila
										// foi iniciada!!!
	}

	@Test
	public void testDequeue() {
		try {
			assertEquals((Integer) 1, queue1.dequeue());
		} catch (QueueUnderflowException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = QueueUnderflowException.class)
	public void testDequeueComErro() throws QueueUnderflowException {
		queue2.dequeue();
		queue2.dequeue();
		assertEquals((Integer) 1, queue2.dequeue()); // vai depender do
														// tamanho que a fial
														// foi iniciada!!!
	}

	// MEUS PROPRIOS TESTES

	public Queue<Integer> emptyQueue;
	public Queue<Integer> fullQueue;
	public Queue<Integer> queue;
	
	@Before
	public void setUp2() throws QueueOverflowException {

		populateQueues();

		fullQueue.enqueue(1);
		fullQueue.enqueue(2);
		fullQueue.enqueue(3);
		fullQueue.enqueue(4);

		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);

	}

	private void populateQueues() {
		emptyQueue = new QueueImpl<>(0);
		fullQueue = new QueueImpl<>(4);
		queue = new QueueImpl<>(5);
	}

	@Test
	public void myTestIsEmpty() {
		assertTrue(emptyQueue.isEmpty());
		assertFalse(queue.isEmpty());
	}

	@Test
	public void myTestIsFull() {
		assertTrue(fullQueue.isFull());
		assertFalse(queue.isFull());
	}

	@Test
	public void myTestHead() {
		assertEquals((Integer)2, queue.head());
		assertEquals((Integer)1, fullQueue.head());
	}

	@Test
	public void myTestEnqueue() throws QueueOverflowException {
		queue.enqueue(5);
	}

	@Test
	public void myTestDequeue() throws QueueUnderflowException {
		assertEquals((Integer)2, queue.dequeue());
		assertEquals((Integer)1, fullQueue.dequeue());
	}

	@Test(expected = QueueOverflowException.class)
	public void myTestEnqueueComErro() throws QueueOverflowException {
		fullQueue.enqueue(5);
	}

	@Test(expected = QueueUnderflowException.class)
	public void myTestDequeueComErro() throws QueueUnderflowException {
		emptyQueue.dequeue();
	}
}