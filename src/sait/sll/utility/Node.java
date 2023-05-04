package sait.sll.utility;

import java.io.Serializable;

/**
 * Node class for creating node objects and getting the next node or setting nodes,
 * as well as getting the data in the nodes or setting the data to different values.
 * 
 * @author Mohummad Bhatti
 * @version Apr 9, 2022
 */
public class Node implements Serializable {

	private static final long serialVersionUID = -1689954886203227883L;
	private Object data;
	private Node next;

	/**
	 * Create new Node object.
	 * 
	 * @param data Data from the node
	 * @param next refers to the next node in the list.
	 */
	public Node(Object data, Node next) {
		this.data = data;
		this.next = next;
	}

	/**
	 * Gets the data from the node.
	 * 
	 * @return data from the node.
	 */
	public Object getData() {
		return data;
	}

	/**
	 * Sets the data in the node
	 * 
	 * @param data Data from the node.
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * Gets the next node from the list
	 * 
	 * @return next node.
	 */
	public Node getNext() {
		return next;
	}

	/**
	 * Sets the next node in the list.
	 * 
	 * @param next refers to the next node in the list.
	 */
	public void setNext(Node next) {
		this.next = next;
	}

}
