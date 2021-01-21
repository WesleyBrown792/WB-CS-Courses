
public class HashObject<T> {
	private int duplicate=0;
	private int probe=0;
	private T key=null;
	
	public  HashObject (T thing) {
		duplicate=0;
		probe=0;
		key = thing;
		
	}
	
	public boolean equals(HashObject a) {
		return (key.equals(a.getKey()));
		
	}

	
	public T getKey() {
		return key;
	}
	public void taken() {
		probe++;
	}
	public void dup() {
		duplicate++;
	}
	public int getD() {
		return duplicate;
	}
	public int getP() {
		return probe;
	}

}
