import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MaxHeap {
	private ArrayList<Process> heap = new ArrayList();
	
	public MaxHeap() {
	}
	
	public void MaxHeapify(int I) {
		int LGlocation = I;
		int Llocation = getleft(I);
		int Rlocation = getright(I);
		if (Llocation<heap.size()&&(heap.get(Llocation)).compareTo(heap.get(I))==1) {
			LGlocation=Llocation;
		}
		if (Rlocation<heap.size()&&(heap.get(Rlocation)).compareTo(heap.get(LGlocation))==1) {
			LGlocation=Rlocation;
		}
		/*if(((Process)LG).compareTo(((Process)P))!=0 && ((Process)L).compareTo(((Process)LG))==0){
			int swap = heap.indexOf(L);
			heap.set(swap, P);
			heap.set(swap/2, L);
		}
		if(((Process)LG).compareTo(((Process)P))!=0 && ((Process)R).compareTo(((Process)LG))==0){
			int swap = heap.indexOf(R);
			heap.set(swap, P);
			heap.set(swap/2, R);
		}*/
		if(LGlocation != I) {
			Process Holder = heap.get(I);
			heap.set(I, heap.get(LGlocation));
			heap.set(LGlocation,Holder);
			MaxHeapify(LGlocation);
		}
		
	}
	
 	public void add(Process process) {
		heap.add(process);
		HeapifyUP(heap.size()-1);
	}


	public Process getMax() {
		if(heap.size()<1) {
			throw new NoSuchElementException();
		}else {
			Process hold = heap.get(0);
			heap.set(0, heap.get(heap.size()-1));
			heap.remove(heap.size()-1);
			if(heap.size()>0) {
				MaxHeapify(0);
			}
			
			return hold;
		}
		
	}


	public void update(int IncrementPriority,int MaxPrio) {
		for(int i=0;i<heap.size();i++) {
			
				Process update = heap.get(i);//this continuously throws a null pointer when using the time Not Processed methods
				update.increaseTimeNotProcessed();
				//System.out.println("currentProcess: "+update.getArrivalTime()+" "+i);
				if(update.getTimeNotProcessed()>=IncrementPriority) {
					update.resetTNP();
					if((update.getPriority() < MaxPrio)) {
						update.setPriority(update.getPriority()+1);
						heap.set(i, update);
						HeapifyUP(i);
					}
				}
			}
	}

	public int getsize() {
		// TODO Auto-generated method stub
		return heap.size();
	}

	public void HeapifyUP(int i) {
		//for some reason this keeps on running out of bounds
		while(i>0 && (heap.get(getparent(i))).compareTo(heap.get(i)) == -1) {
			Process holder = heap.get(getparent(i));
			heap.set(getparent(i), heap.get(i));
			heap.set(i, holder);
			i = getparent(i);
		}
	}
	public int getparent(int i) {
		return Math.round((i-1)/2);
	}
	public int getleft(int i) {
		return (2*i)+1;
	}
	public int getright(int i) {
		return (2*i)+2;
	}

	
}
