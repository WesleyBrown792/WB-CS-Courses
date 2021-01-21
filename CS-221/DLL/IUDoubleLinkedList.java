import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T> {
	Node<T> head;
	Node<T> tail;
	int size;
	int modcount;
	
	
	/**
	 * Initialize a new empty list.
	 */
	public IUDoubleLinkedList() {
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
			head.setLast(addin);
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
			addin.setLast(tail);
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
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> newNode = new Node<T>(element);
		if (size==0) {
			head = tail = newNode;
		}else if(size==1){
			newNode.setNext(head);
			if (index == 0) {
				head.setLast(newNode);
				newNode.setNext(head);
				tail=head;
				head= newNode;
			}else {
				tail.setNext(newNode);
				newNode.setLast(tail);
				tail=newNode;
			}		
		} else {
			if(index==0) {
				newNode.setNext(head);
				head.setLast(newNode);
				head=newNode;
			}else {
				Node<T> current = head;
				for (int i = 0; i < index-1; i++) {
					current = current.getNext();
				}
				newNode.setNext(current.getNext());
				newNode.setLast(current);
				if (index == size) {
					newNode.getLast().setNext(newNode);
					tail = newNode;
				}else {
					newNode.getLast().setNext(newNode);
					newNode.getNext().setLast(newNode);
				}	
			}
			
		}
		size++;
		modcount++;
		
		
	}

	@Override
	public T removeFirst() {
		if(head==null) {
			throw new NoSuchElementException();
		}else {
			
			T element =head.getElement();
			if(size==1) {
				head=tail=null;
			}else {
				Node <T>holder=head;
				head=head.getNext();
				head.setLast(null);
				holder.setNext(null);
			}
			size--;
			modcount++;
			return element;
		}
	}

	@Override
	public T removeLast() {
		if(tail==null) {
			throw new NoSuchElementException();
		}
		
		T element =tail.getElement();
		if(size==1) {
			head=tail=null;
		}else {
			Node<T> holder =tail;
			tail=tail.getLast();
			holder.setLast(null);
			tail.setNext(null);
		}
		size--;
		modcount++;
		return element;
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
			throw new IndexOutOfBoundsException();
		}
		T value;
		if(size==1) {
			value=head.getElement();
			head=tail=null;
		}else {
			Node<T> holder = head;
			for(int i =0;i<index;i++) {
				holder = holder.getNext();
			}
			value=holder.getElement();
			
			if(index==0) {
				head=head.getNext();
				holder.setNext(null);
				head.setLast(null);
			}else
			if(index==size-1) {
				tail=tail.getLast();
				holder.setLast(null);
				tail.setNext(null);
			}else {
				holder.getLast().setNext(holder.getNext());
				holder.getNext().setLast(holder.getLast());
				holder.setNext(null);
				holder.setLast(null);
			}
			
			
		}
		size--;
		modcount++;
		return value;
	}

	@Override
	public void set(int index, T element) {
		if(index >= size || index<0) {
			throw new IndexOutOfBoundsException();
		}
		
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		current.setElement(element);
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
		// TODO Auto-generated method stub
		return new DLLIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return new DLLIterator();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		// TODO Auto-generated method stub
		return new DLLIterator(startingIndex);
	}



	private class DLLIterator implements ListIterator<T> {
		private Node<T> nextNode;
		private int nextIndex;
		private int iterModCount;
		private Node<T> lastReturned;
		private boolean canremove = false;
		
		/**
		 * Initialize iterator in front of the first node
		 */
		public DLLIterator() {
			this(0);
		}
		
		/**
		 * Initialize iterator in front of startingIndex
		 * @param statringIndex index of the element that would be next
		 */
		public DLLIterator(int startingIndex) {
			if(startingIndex<0 || startingIndex>size) {
				throw new IndexOutOfBoundsException();
			}
			iterModCount = modcount;
			nextNode = head;
			for(int i =0;i<startingIndex;i++) {
				nextNode = nextNode.getNext();
			}
			nextIndex=startingIndex;
			lastReturned=null;
		}

		@Override
		public boolean hasNext() {
			if(iterModCount != modcount) {
				throw new ConcurrentModificationException();
			}
			return (nextNode != null);
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			T retval = nextNode.getElement();
			lastReturned = nextNode;
			nextNode = nextNode.getNext();
			nextIndex++;
			canremove=true;
			return retval;
		}

		@Override
		public boolean hasPrevious() {
			if(iterModCount != modcount) {
				throw new ConcurrentModificationException();
			}
			return (nextNode != head);
		}

		@Override
		public T previous() {
			if(!hasPrevious()) {
				throw new NoSuchElementException();
			}
			if(nextNode==null) {
				nextNode = tail;
			}else {
				nextNode = nextNode.getLast();
			}
			lastReturned = nextNode;
			nextIndex--;
			canremove=true;
			return nextNode.getElement();
		}

		@Override
		public int nextIndex() {
			if(iterModCount != modcount) {
				throw new ConcurrentModificationException();
			}
			return nextIndex;
		}

		@Override
		public int previousIndex() {
			if(iterModCount != modcount) {
				throw new ConcurrentModificationException();
			}
			return nextIndex-1;
		}

		@Override
		public void remove() {
			if(iterModCount != modcount) {
				throw new ConcurrentModificationException();
			}
			if(lastReturned == null||canremove==false) {
				throw new IllegalStateException();
			}
			if(head==tail) {
				head=tail=null;
			}else {
			if(lastReturned == tail) {
				nextNode=null;
				tail = tail.getLast();
			}else {
				lastReturned.getNext().setLast(lastReturned.getLast());
			}
			if(lastReturned == head) {
				head = head.getNext();
			}else {
				lastReturned.getLast().setNext(nextNode);
			}
			if(lastReturned != nextNode) {
				nextIndex--;
			}else {
				nextNode = nextNode.getNext();
			}	
			}
			size--;
			modcount++;
			iterModCount++;
			canremove = false;
			lastReturned = null;
			
			
		}

		@Override
		public void set(T e) {
			if(iterModCount != modcount) {
				throw new ConcurrentModificationException();
			}
			if(lastReturned != null) {
				lastReturned.setElement(e);
				
			}else {
				throw new IllegalStateException();
			}
			
			modcount++;
			iterModCount++;
		}

		@Override
		public void add(T e) {
			if(iterModCount != modcount) {
				throw new ConcurrentModificationException();
			}
			Node<T> newNode = new Node<T>(e);
			newNode.setNext(nextNode);
			if(nextNode==null) {
				newNode.setLast(tail);
				tail=newNode;
			}else {
				nextNode.setLast(newNode);
				newNode.setLast(nextNode.getLast());
			}
			if(nextNode==head) {
				head=newNode;
			}else {
				if(newNode.getLast() != null) {
					newNode.getLast().setNext(newNode);
				}
			}
			size++;
			modcount++;
			iterModCount++;
			
		}
	}
}


















