import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Cache<T> {
	public int NH1;
	public int NH2;
	public int NH;
	public int NR1;
	public int NR2;
	public int NR;
	private LinkedList <T> C1;
	private LinkedList<T> C2;
	private int csize1;
	private int csize2;
	private File file;
	public int cnum;
	

	
	public Cache(int numberofcaches, int cachesize, String textfile) {
		C1 = new LinkedList<T>();
		cnum=numberofcaches;
		csize1=cachesize;
		file= new File(textfile);
		
	}
	public Cache(int numberofcaches, int cachesize1, int cachesize2, String textfile) {
		C1 = new LinkedList<T>();
		C2 = new LinkedList<T>();
		cnum=numberofcaches;
		csize1=cachesize1;
		csize2=cachesize2;
		file= new File(textfile);
	}
	
	public T getObject(T search) {
		if(C1.contains(search)) {
			NR1++;
			NH1++;
			C1.remove(search);
			C1.addFirst(search);
		}else {
			if(C2.contains(search)) {
				NR2++;
				NH2++;
				C2.remove(search);
				C2.addFirst(search);
			}
		}
		if(C1.contains(search)==false) {
			NR1++;
			C1.addFirst(search);
			if(C1.size()>csize1) {
				C1.removeLast();
			}
		}
		if(C2.contains(search)==false&&cnum==2) {
			NR2++;
			C2.addFirst(search);
			if(C2.size()>csize2) {
				C2.removeLast();
			}
		}
		return search;
		
	}
	public void search() throws FileNotFoundException {
		Scanner s = new Scanner(file);
		if(cnum==1) {
			while(s.hasNext()) {
				getObject((T) s.next());
			}
		}
		if(cnum==2) {
			while(s.hasNext()) {
				String sa = s.next();
				getObject((T) sa);			
			}
		}
	}
	public int getNH(int cnum){
		if(cnum==1) {
			NH = NH1;
			return NH;
		}else {
			NH = NH1+NH2;
			return NH;
		}
	}
	public int getNR(int cnum) {
		NR = NR1;
		return NR;
	}
	public double getHR1() {
		return (double)NH1/(double)NR1;
	}
	public double getHR2() {
		return (double)NH2/(double)NR2;
	}
	public void addObject(T search) {
		if(C1.contains(search)==true) {
		}else {
			if(C1.size()>csize1) {
				C1.removeLast();
			}
			C1.addFirst(search);
		}
		if(C2.contains(search)==true&&cnum==2) {
		}else {
			if(C2.size()>csize2) {
				C2.removeLast();
			}
			C2.addFirst(search);
		}
	}
	public void removeObject(T search) {
		if(C1.contains(search)) {
			C1.remove(search);
		}
		if(C2.contains(search)) {
			C2.remove(search);
		}
	}
	public void clearCache(int cnum) {
		if(cnum==2) {
			for(int i=0;i<C2.size();i++) {
				C2.removeFirst();
			}
		}
		if(cnum==1) {
			for(int i=0;i<C1.size();i++) {
				C1.removeFirst();
			}
		}
	}
	public void clearCache() {
		for(int i=0;i<C1.size();i++) {
			C1.removeFirst();
		}
		for(int i=0;i<C2.size();i++) {
			C2.removeFirst();
		}
	}
}
