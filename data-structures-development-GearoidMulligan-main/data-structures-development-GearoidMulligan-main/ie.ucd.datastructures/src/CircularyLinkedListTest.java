import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


public class CircularyLinkedListTest {
	@Test
	void testSize() {
		CircularlyLinkedList <Integer> ll =new CircularlyLinkedList <Integer>();
		assertEquals(0, ll.size());
		ll.addFirst(0);
		assertEquals(1, ll.size());
	}

	@Test
	void testIsEmpty() {
		CircularlyLinkedList <Integer> ll =new CircularlyLinkedList <Integer>();
		assertEquals(true, ll.isEmpty());
		ll.addFirst(0);
		assertEquals(false, ll.isEmpty());
		ll.removeFirst();
		assertEquals(true, ll.isEmpty());
	}

	@Test
	void testFirst() {
		CircularlyLinkedList <Integer> ll =new CircularlyLinkedList <Integer>();
		ll.addFirst(-1);
		assertEquals(-1, ll.first());
		
		ll.removeFirst();
		assertEquals(null, ll.first());
		
		
	}

	@Test
	void testLast() {
		CircularlyLinkedList <Integer> ll =new CircularlyLinkedList <Integer>();
		ll.addFirst(-1);

		assertEquals(-1, ll.last());

		ll.addFirst(-2);
		assertEquals(-1, ll.last());
		
		ll.addLast(-3);
		assertEquals(-3, ll.last());
	}


	@Test
	void testRemoveFirst() {
		CircularlyLinkedList <Integer> ll =new CircularlyLinkedList <Integer>();
		ll.addFirst(-1);
		ll.addFirst(-2);
		assertEquals(-2, ll.removeFirst());
	}

	@Test
	void testGet() {
		CircularlyLinkedList <Integer> ll =new CircularlyLinkedList <Integer>();
		for(int i = 0; i < 5; ++i) ll.addLast(i);

		assertEquals(1, ll.get(1));
	}

	@Test
	void testRemove() {
		CircularlyLinkedList <Integer> ll =new CircularlyLinkedList <Integer>();
		for(int i = 0; i < 5; ++i) ll.addLast(i);

		ll.remove(1);
		assertEquals("[0, 2, 3, 4]", ll.toString());
	}

	@Test
	void testAdd() {
		CircularlyLinkedList <Integer> ll =new CircularlyLinkedList <Integer>();
		for(int i = 0; i < 5; ++i) ll.addLast(i);

		ll.add(2, -1);
		assertEquals("[0, 1, -1, 2, 3, 4]", ll.toString());
	}

	@Test
	void testToString() {
		CircularlyLinkedList <Integer> ll =new CircularlyLinkedList <Integer>();
		for(int i = 0; i < 5; ++i) ll.addLast(i);

		assertEquals("[0, 1, 2, 3, 4]", ll.toString());
	}

	@Test
	void testIterator() {
		CircularlyLinkedList <Integer> ll =new CircularlyLinkedList <Integer>();
		for(int i = 0; i < 5; ++i) ll.addLast(i);
		
		ArrayList<Integer> buf = new ArrayList<>();
		for(Integer i : ll) {
			buf.add(i);
		}
	}
}
