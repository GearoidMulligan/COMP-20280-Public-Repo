public class LinkedDeque<E> implements Deque<E> {
	
	private SinglyLinkedList<E> list = new SinglyLinkedList<>( );

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		if (list.size()==0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public E first() {
		return list.first();
	}

	@Override
	public E last() {
		return list.last();
	}

	@Override
	public void addFirst(E e) {
		list.addFirst(e);
	}

	@Override
	public void addLast(E e) {
		list.addLast(e);
	}

	@Override
	public E removeFirst() {
		return list.removeFirst();
	}

	@Override
	public E removeLast() {
		return list.removeLast();
	}
	
	@Override
	public String toString() {
		return list.toString();
	}

}
