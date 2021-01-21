
public class Process implements Comparable <Process> {
	private int TimeRemaining;
	private int ArrivalTime;
	private int Priority;
	private int TimeNotProcessed;
	
	public Process(int Ptime, int Plevel, int MPlevel, int Atime) {
		TimeRemaining=Ptime;
		Priority=Plevel;
		ArrivalTime = Atime;
		TimeNotProcessed = 0;
	}
	

	public int getTimeRemaining() {
		return TimeRemaining;
	}
	
	public void setPriority(int P) {
		Priority=P;
	}
	
	public void resetTNP() {
		TimeNotProcessed = 0;
	}

	public void reduceTimeRemaining() {
		TimeRemaining--;
	}

	public boolean finish() {
		if(TimeRemaining <= 0) {
			return true;
		}else {
			return false;
		}
		
	}

	public int getArrivalTime() {
		return ArrivalTime;
	}

	public void resetTimeNotProcessed() {
		TimeNotProcessed=0;
		
	}
	
	public int getTimeNotProcessed() {
		return TimeNotProcessed;
	}
	
	public void increaseTimeNotProcessed() {
		TimeNotProcessed++;
	}
	
	public int getPriority() {
		return Priority;
	}
	
	public int compareTo(Process P) {
		//if A > B then 1
		//if A == B then 0
		//if A < B then -1
		if(Priority > P.getPriority()) {
			return 1;
		}
		if(Priority == P.getPriority()) {
			if(ArrivalTime == P.getArrivalTime()) {
				return 0;
			}
			if(ArrivalTime > P.getArrivalTime()) {
				return -1;
			}
			if(ArrivalTime < P.getArrivalTime()) {
				return 1;
			}
		}
		if(Priority < P.getPriority()) {
			return -1;
		}
		return 1;
	}

}
