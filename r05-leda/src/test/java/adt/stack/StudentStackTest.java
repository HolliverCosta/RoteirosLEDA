package adt.stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class StudentStackTest {

	public Stack<Integer> stack1;
	public Stack<Integer> stack2;
	public Stack<Integer> stack3;
	
	@Before
	public void setUp() throws StackOverflowException {

		getImplementations();

		// Pilha com 3 elementos não cheia.
		stack1.push(1);
		stack1.push(2);
		stack1.push(3);

		// Pilha com 2 elementos de tamanho 2, pilha cheia.
		stack2.push(1);
		stack2.push(2);

	}

	private void getImplementations() {
		stack1 = new StackImpl<>(3);
		stack2 = new StackImpl<>(2);
		stack3 = new StackImpl<>(0);
	}

	// MÉTODOS DE TESTE
	@Test
	public void testTop() {
		assertEquals((Integer) 3, stack1.top());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(stack1.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertTrue(stack1.isFull()); // vai depender do tamanho que a pilha foi
										// iniciada!!!!
	}

	@Test
	public void testPush() {
		try {
			stack1.push((Integer) 5);
		} catch (StackOverflowException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = StackOverflowException.class)
	public void testPushComErro() throws StackOverflowException {
		stack1.push((Integer) 5); // levanta excecao apenas se o tamanhonao
										// permitir outra insercao
	}

	@Test
	public void testPop() {
		try {
			assertEquals((Integer) 3, stack1.pop());
		} catch (StackUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = StackUnderflowException.class)
	public void testPopComErro() throws StackUnderflowException {
		assertEquals((Integer) 3, stack3.pop()); // levanta excecao apenas se
													// stack1 for vazia
	}

	// MEUS PROPRIOS TESTES

	public Stack<Integer> emptyStack;
	public Stack<Integer> fullStack;
	public Stack<Integer> stack;

	@Before
	public void setUp2() throws StackOverflowException {

		populateStacks();

		fullStack.push(1);
		fullStack.push(2);
		fullStack.push(3);

		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);

	}

	private void populateStacks() {
		emptyStack = new StackImpl<>(0);
		fullStack = new StackImpl<>(3);
		stack = new StackImpl<>(5);
	}

	@Test
	public void myTestIsEmpty() {
		assertTrue(emptyStack.isEmpty());
		assertFalse(stack.isEmpty());
	}

	@Test
	public void myTestIsFull() {
		assertTrue(fullStack.isFull());
		assertFalse(stack.isFull());
	}

	@Test
	public void myTestTop() throws StackOverflowException {
		stack.push(5);
		assertEquals(stack.top(), (Integer)5);
	}

	@Test
	public void myTestPush() throws StackOverflowException {
		stack.push(5);
	}

	@Test
	public void myTestPop() throws StackUnderflowException {
		assertEquals(stack.pop(), (Integer)4);
	}
	
	@Test(expected = StackOverflowException.class)
	public void myTestPushWithError() throws StackOverflowException {
		fullStack.push(27);
	}

	@Test(expected = StackUnderflowException.class)
	public void myTestPopWithError() throws StackUnderflowException{
		assertEquals((Integer) 4, emptyStack.pop());
	}
}