public class ArrayStack<E> implements Stack<E> {
	
	public static int CAPACITY =999;
	private E[] data;
	private int top =-1;
	
	public ArrayStack() {
		this(CAPACITY);
	}
	
	public ArrayStack(int capacity) {
		data = (E[ ]) new Object[capacity];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public int size() {
		return top+1;
	}

	@Override
	public boolean isEmpty() {
		if(top==-1) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void push(E e) {
		if(size()==data.length) {
			throw new IllegalStateException("Stack is full");
		}
		data[++top] = e;
	}

	@Override
	public E top() {
		if(top==-1) {
			return null;
		}
		else {
			return data[top];
		}
	}

	@Override
	public E pop() {
		if(top==-1) {
			return null;
		}
		E answer = data[top];
		data[top] = null; 
		top--;
		return answer;
	}

}
