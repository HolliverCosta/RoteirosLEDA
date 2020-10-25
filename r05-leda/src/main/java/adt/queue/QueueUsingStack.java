package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull())
			throw new QueueOverflowException();
		if (element != null) {

			while (!stack1.isEmpty()) {
				try {
					stack2.push(stack1.pop());
				} catch (StackOverflowException e) {

					e.printStackTrace();
				} catch (StackUnderflowException e) {

					e.printStackTrace();
				}
			}
			try {
				stack1.push(element);
			} catch (StackOverflowException e) {

				e.printStackTrace();
			}
			while (!stack2.isEmpty()) {
				try {
					stack1.push(stack2.pop());
				} catch (StackOverflowException e) {

					e.printStackTrace();
				} catch (StackUnderflowException e) {

					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.stack1.isEmpty())
			throw new QueueUnderflowException();
		T x = stack1.top();
		try {
			stack1.pop();
		} catch (StackUnderflowException e) {
			e.printStackTrace();
		}
		return x;
	}

	@Override
	public T head() {
		if (isEmpty())
			return null;
		return this.stack1.top();
	}

	@Override
	public boolean isEmpty() {
		return this.stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.stack1.isFull();
	}

}
