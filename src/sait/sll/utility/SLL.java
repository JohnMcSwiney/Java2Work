package sait.sll.utility;

import java.util.NoSuchElementException;

/**
 * Singly-Linked List class that implements LinkedListADT interface.
 * 
 * @author Mohummad Bhatti
 * @author John McSwiney
 * @author Jack Eyre
 * @version Apr 9, 2022
 *
 */
public class SLL implements LinkedListADT {

	private static final long serialVersionUID = 1780455871994021750L;

	private Node head;
	private Node tail;
	private int size = 0;

	/**
	 * Checks if the list is empty.
	 * 
	 * @return True if it is empty.
	 */
	@Override
	public boolean isEmpty() {
		return (head == null);
	}

	/**
	 * Clears the list.
	 */
	@Override
	public void clear() {
		head = null;
	}

	/**
	 * Adds to the end of the list.
	 * 
	 * @param data Data to append.
	 */
	@Override
	public void append(Object data) {
		Node node = new Node(data, null);
		if (isEmpty()) {
			head = node;
		} else {
			tail.setNext(node);
		}
		tail = node;
		size++;
	}

	/**
	 * Prepends (adds to beginning) data to the list.
	 * 
	 * @param data Data to store inside element.
	 */
	@Override
	public void prepend(Object data) {
		Node node = new Node(data, head);
		node.setNext(head);
		head = node;
		size++;
		if (tail == null) {
			tail = head;
		}
	}

	/**
	 * Adds a new element at a specific position.
	 * 
	 * @param data  Data that element is to contain.
	 * @param index Index to add new element at.
	 * @exception IndexOutOfBoundsException Thrown if index is negative or past the
	 *                                      size of the list.
	 */
	@Override
	public void insert(Object data, int index) throws IndexOutOfBoundsException {

		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}

		Node node = new Node(data, head);
		node.setData(data);
		node.setNext(null);

		Node temp = head;

		if (index == 0) {
			prepend(data);
		}

		else if (index >= size) {
			append(data);
		} else {
			for (int i = 0; i < index - 1; i++) {
				temp = temp.getNext();
			}
			node.setNext(temp.getNext());
			temp.setNext(node);
			size++;
		}

	}

	/**
	 * Replaces the data at index.
	 * 
	 * @param data  Data to replace.
	 * @param index Index of element to replace.
	 * @exception IndexOutOfBoundsException Thrown if index is negative or larger
	 *                                      than size - 1 of list.
	 */
	@Override
	public void replace(Object data, int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		Node current = head;
		boolean updated = false;
		for (int i = 0; i < size; i++) {
			if (i == index) {
				current.setData(data);
				updated = true;
			} else {
				current = current.getNext();
			}
			updated = false;

		}

	}

	/**
	 * Gets the number of elements in the list.
	 * 
	 * @return Size of list (0 meaning empty)
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Removes element at index from list, reducing the size.
	 * 
	 * @param index Index of element to remove.
	 * @exception IndexOutOfBoundsException Thrown if index is negative or past the
	 *                                      size - 1.
	 */
	@Override
	public void delete(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		if (index == 0) {
			head = head.getNext();
		} else {
			Node temp1 = head;
			Node temp2 = null;
			for (int i = 0; i < index - 1; i++) {
				temp1 = temp1.getNext();
			}
			temp2 = temp1.getNext();
			temp1.setNext(temp2.getNext());
			size--;
		}
	}

	/**
	 * Gets the data at the specified index.
	 * 
	 * @param index Index of element to get.
	 * @return Data in element or null if it was not found.
	 * @exception IndexOutOfBoundsException Thrown if index is negative or larger
	 *                                      than size - 1 of the list.
	 */
	@Override
	public Object retrieve(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}

		Node current = head;
		if (isEmpty()) {
			return null;
		}
		if (index >= size) {
			index = size - 1;
		}

		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}

		return current.getData();

	}

	/**
	 * Gets the first index of element containing data.
	 * 
	 * @param data Data object to find the first index of.
	 * @return First of index of element with matching data or -1 if not found.
	 */
	@Override
	public int indexOf(Object data) {
		Node current = head;

		for (int i = 0; i < size; i++) {
			if (current.getData().equals(data)) {
				return i;
			} else {
				current = current.getNext();
			}
		}
		return -1;
	}

	/**
	 * Go through elements and check if we have one with data.
	 * 
	 * @param data Data object to search for.
	 * @return True if element exists with value.
	 */
	@Override
	public boolean contains(Object data) {
		Node current = head;
		if (head != null) {
			while (current != null) {
				if (current.getData().equals(data)) {
					return true;
				} else {
					current = current.getNext();
				}
			}
		}
		return false;

	}

	/**
	 * Remove the first node in the list
	 * 
	 * @return the first element in the list
	 * @exception NoSuchElementException Thrown if there are no elements in the
	 *                                   list.
	 */
	@Override
	public Object removeFirst() throws NoSuchElementException {
		if (head == null) {
			throw new NoSuchElementException();
		}

		Node tempNode = head;
		head = head.getNext();
		Object tempData = tempNode.getData();
		tempNode.setNext(null);
		return tempData;
	}

	/**
	 * Remove the last node in the list
	 * 
	 * @return the last element in the list
	 * @exception NoSuchElementException Thrown if there are no elements in the
	 *                                   list.
	 */
	@Override
	public Object removeLast() throws NoSuchElementException {
		if (head == null) {
			throw new NoSuchElementException();
		}
		Node last = head;
		Node temp = null;

		while (last.getNext() != null) {
			temp = last;
			last = last.getNext();
		}
		temp.setNext(null);
		return last.getData();
	}

}
