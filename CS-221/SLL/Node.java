/**
 * Node represents a node in a linked list.
 *
 * @author Java Foundations, mvail
 * @version 4.0
 */
public class Node<T> {
	private Node<T> next;
	private Node<T> last;
	private T element;

	/**
  	 * Creates an empty node.
  	 */
	public Node() {
		last=null;
		next = null;
		element = null;
	}

	/**
  	 * Creates a node storing the specified element.
 	 *
  	 * @param elem
  	 *            the element to be stored within the new node
  	 */
	public Node(T elem) {
		last=null;
		next = null;
		element = elem;
	}

	/**
 	 * Returns the node that follows this one.
  	 *
  	 * @return the node that follows the current one
  	 */
	public Node<T> getNext() {
		return next;
	}
	
	/**
	 * Returns the node that came before this one.
	 * 
	 * @return the node before the this node
	 */
	public Node<T> getLast(){
		return last;
	}

	/**
 	 * Sets the node that follows this one.
 	 *
 	 * @param node
 	 *            the node to be set to follow the current one
 	 */
	public void setNext(Node<T> node) {
		next = node;
	}
	
	/**
	 * Sets the last node before this one.
	 * 
	 * @param node
	 */
	public void setLast(Node<T> node) {
		last = node;
	}

	/**
 	 * Returns the element stored in this node.
 	 *
 	 * @return the element stored in this node
 	 */
	public T getElement() {
		return element;
	}

	/**
 	 * Sets the element stored in this node.
  	 *
  	 * @param elem
  	 *            the element to be stored in this node
  	 */
	public void setElement(T elem) {
		element = elem;
	}

	@Override
	public String toString() {
		return "Element: " + element.toString() + " Has next: " + (next != null);
	}
}
