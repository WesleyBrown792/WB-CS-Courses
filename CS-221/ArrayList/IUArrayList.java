import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Array-based implementation of IndexedUnsortedList.
 * An Iterator with working remove() method is implemented, but
 * ListIterator is unsupported. 
 * 
 * @author Wesley Brown
 *
 * @param <T> type to store
 */
public class IUArrayList<T> implements IndexedUnsortedList<T>, Iterable<T> {
	private static final int DEFAULT_CAPACITY = 10;
	private static final int NOT_FOUND = -1;
	
	private T[] array;
	private int rear;
	private int modCount;
	
	/** Creates an empty list with default initial capacity */
	public IUArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	/** 
	 * Creates an empty list with the given initial capacity
	 * @param initialCapacity
	 */
	@SuppressWarnings("unchecked")
	public IUArrayList(int initialCapacity) {
		array = (T[])(new Object[initialCapacity]);
		rear = 0;
		modCount = 0;
	}
	
	/** Double the capacity of array */
	private void expandCapacity() {
		array = Arrays.copyOf(array, array.length*2);
	}

	@Override
	public void addToFront(T element) {
		if(rear+1 >= array.length) {
			 expandCapacity();
		 }
		 if(rear>=1) {
			for(int i=rear-1;i>=0;i--) {
				array[i+1]=array[i];
			}
			array[0]=element;
			rear++;
			modCount++;
		 }else {
			 array[0]=element;
			 rear++;
			 modCount++;
		 }
		
	}

	@Override
	public void addToRear(T element) {
		if(rear+1 > array.length) {
			expandCapacity();
		}
		array[rear]=element;
		rear++;
		modCount++;
	}

	@Override
	public void add(T element) {
		addToRear(element);
		
	}

	@Override
	public void addAfter(T element, T target) {
		int addto = indexOf(target);
		if(addto==-1) {
			throw new NoSuchElementException();
		}
		addto++;
		add(addto,element);
		
	}

	@Override
	public void add(int index, T element) {
		if(index<0 || index>rear) {
			throw new IndexOutOfBoundsException();
		}
		if(rear+1 > array.length) {
			expandCapacity();
		}
		for(int i=rear+1;i>index;i--) {
			array[i]=array[i-1];
		}
		array[index]=element;
		rear++;
		modCount++;
		
		
		
	}

	@Override
	public T removeFirst() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		T holder=array[0];
		if(rear==1) {
			array[rear-1]=null;
		}else {
			for(int i=0;i<rear;i++) {
				array[i]=array[i+1];
			}
			array[rear-1]=null;
		}
		
		
		rear--;
		modCount++;
		return holder;
	}

	@Override
	public T removeLast() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		T retVal = array[rear-1];
		array[rear-1]=null;
		rear--;
		modCount++;
		return retVal;
	}

	@Override
	public T remove(T element) {
		int index = indexOf(element);
		if (index == NOT_FOUND) {
			throw new NoSuchElementException();
		}
		
		T retVal = array[index];
		
		rear--;
		//shift elements
		for (int i = index; i < rear; i++) {
			array[i] = array[i+1];
		}
		array[rear] = null;
		modCount++;
		
		return retVal;
	}

	@Override
	public T remove(int index) {
		if(index<0 || index>=rear) {
			throw new IndexOutOfBoundsException();
		}
		T holder = array[index];
		for(int i=index;i<rear;i++) {
			array[i]=array[i+1];
		}
		array[rear]=null;
		rear--;
		modCount++;
		return holder;
	}

	@Override
	public void set(int index, T element) {
		if(index<0||index>=rear) {
			throw new IndexOutOfBoundsException();
		}
		array[index]=element;
		modCount++;		
	}

	@Override
	public T get(int index) {
		if(index<0||index>=rear) {
			throw new IndexOutOfBoundsException();
		}
		return array[index];
	}

	@Override
	public int indexOf(T element) {
		int index = NOT_FOUND;
		
		if (!isEmpty()) {
			int i = 0;
			while (index == NOT_FOUND && i < rear) {
				if (element.equals(array[i])) {
					index = i;
				} else {
					i++;
				}
			}
		}		
		return index;
	}

	@Override
	public T first() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}else {
			return get(0);
		}
		
	}

	@Override
	public T last() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}else {
			return get(rear-1);
		}
	}

	@Override
	public boolean contains(T target) {
		for(int i=0;i<rear;i++) {
			if(array[i]==target) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		if(rear==0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public int size() {
		return rear;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[");
		for(int i=0;i<rear;i++) {
			str.append(get(i));
			if(i+1 < rear) {
				str.append(", ");
			}
		}
		str.append("]");
		return str.toString();
	}
	
	@Override
	public Iterator<T> iterator() {
		return new ALIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		throw new UnsupportedOperationException();
	}

	/** Iterator for IUArrayList */
	private class ALIterator implements Iterator<T> {
		private int nextIndex;
		private int itrModCount;
		private boolean canRemove;
		
		public ALIterator() {
			nextIndex = 0;
			itrModCount = modCount;
			canRemove = false;
		}

		@Override
		public boolean hasNext() {
			if(itrModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			return (nextIndex<rear);
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			nextIndex++;
			canRemove = true;
			return get(nextIndex-1);
		}
		
		@Override
		public void remove() {
			if(itrModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if(!canRemove) {
				throw new IllegalStateException();
			}
			canRemove = false;
			for(int i=nextIndex-1;i<rear;i++) {
				array[i]=array[i+1];
			}
			array[rear-1]=null;
			rear--;
			modCount++;
			itrModCount++;			
		}
	}
}