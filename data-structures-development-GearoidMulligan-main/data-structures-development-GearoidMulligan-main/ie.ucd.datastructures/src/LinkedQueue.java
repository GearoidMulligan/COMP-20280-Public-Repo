public class LinkedQueue<E> implements Queue<E> {

	private SinglyLinkedList<E> list = new SinglyLinkedList<>( );
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	public LinkedQueue(){
		
	}
	
	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		if(list.size()==0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void enqueue(E e) {
		list.addLast(e);
	}

	@Override
	public E first() {
		return list.first();
	}

	@Override
	public E dequeue() {
		return list.removeFirst();
	}
	
	@Override
	public String toString() {
		return list.toString();
	}

}
