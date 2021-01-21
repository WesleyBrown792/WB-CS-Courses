import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Search for shortest paths between start and end points on a circuit board
 * as read from an input file using either a stack or queue as the underlying
 * search state storage structure and displaying output to the console or to
 * a GUI according to options specified via command-line arguments.
 * 
 * @author mvail
 */
public class CircuitTracer {

	/** launch the program
	 * @param args three required arguments:
	 *  first arg: -s for stack or -q for queue
	 *  second arg: -c for console output or -g for GUI output
	 *  third arg: input file name 
	 */
	public static void main(String[] args) {
		if (args.length != 3) {
			printUsage();
			System.exit(1);
		}
		try {
			new CircuitTracer(args); //create this with args
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/** Print instructions for running CircuitTracer from the command line. */
	private static void printUsage() {
		System.out.println("To use this Circuit Tracer enter in your commandline arguments as follows:");
		System.out.println("java CircuitTracer -s/-q -c <name of input file>");
		System.out.println("If you follow the above order of command the program will work as intended also the -g command does not work yet and the proram will exit out if it is used.");
	}
	
	/** 
	 * Set up the CircuitBoard and all other components based on command
	 * line arguments.
	 * 
	 * @param args command line arguments passed through from main()
	 * @throws InvalidFileFormatException 
	 * @throws FileNotFoundException 
	 */
	private CircuitTracer(String[] args) throws FileNotFoundException, InvalidFileFormatException {
		if(args[1].equals("-g")) {
			printUsage();
			System.exit(1);
		}
		int dataType=0;
		if(args[0].equals("-s")) {
			dataType=1;
		}else if(args[0].contentEquals("-q")) {
			dataType=2;
		}else {
			printUsage();
			throw new InvalidFileFormatException("Incorrect First Argument");
			
		}
		CircuitBoard board = new CircuitBoard(args[2]);
		
		ArrayList<TraceState >bestPaths = new ArrayList<TraceState>();
		
		bestPaths = search(dataType,board);
			for(int i=0;i<bestPaths.size();i++) {
				if(bestPaths.size()>=2) {
					if(bestPaths.get(i).equals(bestPaths.get(1))&&i!=1) {
						continue;
					}
				}
				TraceState val = bestPaths.get(i);
				System.out.println(val.toString());
			}
	}
	
	/**
	 * The Search method is used to find every possible path through the circuit from 1->2 and will return an ArrayList of TraceStates
	 * detailing the best possible paths.
	 * @param dataType
	 * @param board
	 * @return An ArrayList of bestPaths
	 */
	private ArrayList<TraceState> search(int dataType, CircuitBoard board) {
		Storage<TraceState> data=null;
		ArrayList<TraceState >bestPaths = new ArrayList<TraceState>();
		if(dataType==1) {
			data=new Storage<TraceState>(Storage.DataStructure.stack);
		}
		if(dataType==2) {
			data=new Storage<TraceState>(Storage.DataStructure.queue);
		}
		
		if(board.isOpen(board.getStartingPoint().x+1, board.getStartingPoint().y)) {
			data.store(new TraceState(board,board.getStartingPoint().x+1,board.getStartingPoint().y));
		}
		if(board.isOpen(board.getStartingPoint().x-1, board.getStartingPoint().y)) {
			data.store(new TraceState(board,board.getStartingPoint().x-1,board.getStartingPoint().y));
		}
		if(board.isOpen(board.getStartingPoint().x, board.getStartingPoint().y+1)) {
			data.store(new TraceState(board,board.getStartingPoint().x,board.getStartingPoint().y+1));
		}
		if(board.isOpen(board.getStartingPoint().x, board.getStartingPoint().y-1)) {
			data.store(new TraceState(board,board.getStartingPoint().x,board.getStartingPoint().y-1));
		}
		
		while(!data.isEmpty()) {
			
			TraceState state = data.retrieve();
			
			if(state.isComplete()) {
				if(bestPaths.size()==0) {
					bestPaths.add(state);
				}
				if(state.pathLength()==bestPaths.get(0).pathLength()||bestPaths.size()==0) {
					bestPaths.add(state);
				}
				if(state.pathLength()<bestPaths.get(0).pathLength()||bestPaths.size()==0) {
					bestPaths.clear();
					bestPaths.add(state);
				}
			}else {
				
				if(state.isOpen(state.getRow()+1,state.getCol())) {
					data.store(new TraceState(state,state.getRow()+1,state.getCol()));
					
				}
				
				if(state.isOpen(state.getRow()-1, state.getCol())) {
					data.store(new TraceState(state,state.getRow()-1,state.getCol()));
					
				}
				
				if(state.isOpen(state.getRow(), state.getCol()+1)) {
					data.store(new TraceState(state,state.getRow(),state.getCol()+1));
					
				}
				
				if(state.isOpen(state.getRow(),state.getCol()-1)) {
					data.store(new TraceState(state,state.getRow(),state.getCol()-1));
					
				}
			}
			
			
		}
		return bestPaths;
		
	}
	
} // class CircuitTracer
