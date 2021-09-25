public class ArrayQueue<E> implements Queue<E> {
	
	public static int MAXSIZE=999;
	private E[] data;
	private int front =0;
	private int currSize = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public ArrayQueue() {
		this(MAXSIZE);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayQueue(int capacity){
		data=(E[])new Object[capacity];
	}
	
	@Override
	public int size() {
		return currSize;
	}

	@Override
	public boolean isEmpty() {
		if(currSize==0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void enqueue(E e) throws IllegalStateException{
			if (currSize == data.length) {
				throw new IllegalStateException("Queue is frontull");
			
			}
			int newQ = (front + currSize) % data.length; // use modular arithmetic
			data[newQ] = e;
			currSize++;
		
	}

	@Override
	public E first() {
		if(currSize==0) {
			return null;
		}
		else {
			return data[front];
		}
	}

	@Override
	public E dequeue() {
		if(currSize==0) {
		return null;
		}
		else {}
		E first=data[front];
		data[front]=null;
		front=(front+1)%data.length;
		currSize--;
		return first;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
        sb.append("[");
       for(int i=0;i<currSize;i++) {
            sb.append(data[i]);
            sb.append(", ");
       }
        
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        return sb.toString();
		
	}
	
	

}
