
public class ProcessGenerator {

	public double rangePT;
	public double rangeML;
	public double odds;
	public ProcessGenerator(double probability) {
		odds = 1-probability;
	}

	public boolean query() {
		if(Math.random()>odds) {
			return true;
		}else {
			return false;
		}
	}

	public Process getNewProcess(int currentTime, int maxProcessTime, int maxLevel) {
		rangePT = maxProcessTime;
		rangeML = maxLevel;
		int pProcessTime = (int) ((Math.random() * rangePT) + 1);
		int pLevel = (int) ((Math.random() * rangeML) + 1);
		Process N = new Process(pProcessTime, pLevel, maxLevel, currentTime);
		return N;
	}

}
