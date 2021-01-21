import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class IUSingleLinkedList<T> implements IndexedUnsortedList<T> {

	Node<T> head;
	Node<T> tail;
	int size;
	int modcount;
	
	
	public IUSingleLinkedList() {
		head=tail=null;
		size=0;
		modcount=0;
	}
	
	
	@Override
	public void addToFront(T element) {
		Node<T> addin= new Node<T>(element);
		if(size==0) {
			tail=head=addin;
		}else {
			addin.setNext(head);
			head=addin;
		}
		size++;
		modcount++;
		
	}

	@Override
	public void addToRear(T element) {
		Node<T> addin= new Node<T>(element);
		if(size==0) {
			tail=head=addin;
		}else {
			tail.setNext(addin);
			tail=addin;
		}
		size++;
		modcount++;
		
	}

	@Override
	public void add(T element) {
		addToRear(element);
		
	}

	@Override
	public void addAfter(T element, T target) {
		int hold=indexOf(target);
		if(hold==-1) {
			throw new NoSuchElementException();
		}else {
			add(hold+1,element);
		}
		
		
	}

	@Override
	public void add(int index, T element) {
		//1 2 3 4 5
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> newSLLNode = new Node<T>(element);
		if (index == 0) {
			newSLLNode.setNext(head);
			head = newSLLNode;
			if (size == 0) {
				tail = newSLLNode;
			}
		} else {
			Node<T> current = head;
			for (int i = 0; i < index - 1; i++) {
				current = current.getNext();
			}
			Node<T> Next = current.getNext();
			current.setNext(newSLLNode);
			newSLLNode.setNext(Next);
			current.setNext(newSLLNode);
			if (index == size) {
				tail = newSLLNode;
			}
		}
		size++;
		modcount++;
	}

	@Override
	public T removeFirst() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		
		Node<T> Hold = head;
		//.setNext(null);
		head=head.getNext();
		if(size<=2) {
			tail=head;
		}
		Hold.setNext(null);
		modcount++;
		size--;
		return Hold.getElement();
	}

	@Override
	public T removeLast() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		if(size==1) {
			Node<T> ret = head;
			head=tail=null;
			ret.setNext(null);
			size--;
			modcount++;
			return ret.getElement();
		}else {
			Node<T> ret = head;
			for(int i=0;i<size-2;i++) {
				ret=ret.getNext();
			}
			tail=ret;
			Node<T> holder = ret.getNext();
			ret.setNext(null);
			size--;
			modcount++;
			return holder.getElement();			
		}
	}

	@Override
	public T remove(T element) {
		int i = indexOf(element);
		if (i == -1) {
			throw new NoSuchElementException();
		}
		return remove(i);
	}

	@Override
	public T remove(int index) {
		
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Linked-remove(pos)-invalid index");
		}
		T value;
		if (index == 0) {
			Node<T> current = head;
			Node<T> Next = current.getNext();
			head = Next;
			value = current.getElement();
			current.setNext(null);	//change
			if (index == 1) {
				tail = null;
			}
		} else {
			Node<T> current = head;
			for (int i = 0; i < index-1; i++) {
				current = current.getNext();
			}
			Node<T> Next = current.getNext();
			value = Next.getElement();
			current.setNext(Next.getNext());
			Next.setNext(null);
			if (size == index + 1) {
				tail = current;
			}
		}
		size--;
		modcount++;
		return value;
	}

	@Override
	public void set(int index, T element) {
		if(index>=size||index<0) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> place=head;
		for(int i=0;i<index;i++) {
			place=place.getNext();
		}
		place.setElement(element);
		modcount++;
		
	}

	@Override
	public T get(int index) {
		if(index>=size||index<0) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> place = head;
		for(int i=0;i<index;i++) {
			place=place.getNext();
		}
		return place.getElement();
	}

	@Override
	public int indexOf(T element) {
		int i = 0;
		Node<T> hold = head;
		boolean found = false;
		while (i < size && found != true) {
			if (hold.getElement() == element) {
				found = true;
				;
			} else {
				hold = hold.getNext();
				i++;
			}
		}
		if (found == false) {
			return -1;
		}
		return i;
	}

	@Override
	public T first() {
		if(size<1) {
			throw new NoSuchElementException();
		}
		return head.getElement();
	}

	@Override
	public T last() {
		if(size<1) {
			throw new NoSuchElementException();
		}
		return tail.getElement();
	}

	@Override
	public boolean contains(T target) {
		Node<T> place=head;
		for(int i=0;i<size;i++) {
			if(place.getElement()==target) {
				return true;
			}else {
				place=place.getNext();
			}
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		if(head==null||tail==null||size==0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public int size() {
		return size;
	}
	
	@Override
	public String toString() {
		Node<T> place=head;
		StringBuilder str = new StringBuilder();
		str.append("[");
		for(int i=0;i<size;i++) {
			str.append(place.getElement());
			if(i+1 < size) {
				str.append(", ");
			}
			place=place.getNext();
		}
		str.append("]");
		return str.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new SLLIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * basic Iterator for IUSingleLinkedList*/
	private class SLLIterator implements Iterator<T>{
		private Node <T> nextNode;
		private int ITRmodcount;
		private boolean canremove;
		private Node<T> holder;
		
		public SLLIterator() {
			nextNode=head;
			ITRmodcount=modcount;
			canremove=false;
		}
		
		
		@Override
		public boolean hasNext() {
			if(ITRmodcount!=modcount) {
				throw new ConcurrentModificationException();
			}
			return (nextNode != null);
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			Node<T> holder = nextNode;
			nextNode = nextNode.getNext();
			canremove=true;
			return holder.getElement();
		}
		
		@Override
		public void remove() {
			if(ITRmodcount!=modcount) {
				throw new ConcurrentModificationException();
			}
			if(!canremove) {
				throw new IllegalStateException();
			}
			canremove=false;
			
			if(size==1) {
				head=tail=null;
			}else if(head.getNext()==nextNode) {
				head=nextNode;
			}else if(nextNode==null) {
				Node<T> newTail=head;
				while(newTail.getNext() !=tail) {
					newTail = newTail.getNext();
				}
				newTail.setNext(null);
				tail = newTail;
			}else {
				Node<T> current =head;
				while(current.getNext().getNext() != nextNode) {
					current=current.getNext();
				}
				current.setNext(nextNode);
				
			}
			size--;
			modcount++;
			ITRmodcount++;
		}
		
	}

}
