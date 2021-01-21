
public class PQueue {
	private MaxHeap PQ = new MaxHeap();
	

	public boolean isEmpty() {
		if(PQ.getsize()<=0) {
			return true;
		}else {
			return false;
		}
	}
	//this takes the head into its own variable then removes the old head from the list and decrimenting the count by 1
	public Process dePQueue() {
		return PQ.getMax();
		
	}
	//this puts the newest process onto the back of the que
	public void enPQueue(Process p) {
		PQ.add(p);
			
	}
	//the goal would be to run through the whole que in the method called
	public void update(int timeToIncrementLevel, int maxLevel) {
			PQ.update(timeToIncrementLevel,maxLevel);		
	}

}
