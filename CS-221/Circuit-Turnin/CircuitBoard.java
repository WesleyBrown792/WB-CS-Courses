import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represents a 2D circuit board as read from an input file.
 *  
 * @author mvail
 */
public class CircuitBoard {
	/** current contents of the board */
	private char[][] board;
	/** location of row,col for '1' */
	private Point startingPoint;
	/** location of row,col for '2' */
	private Point endingPoint;

	//constants you may find useful
	private final int ROWS; //initialized in constructor
	private final int COLS; //initialized in constructor
	private final char OPEN = 'O'; //capital 'o'
	private final char CLOSED = 'X';
	private final char TRACE = 'T';
	private final char START = '1';
	private final char END = '2';
	private final String ALLOWED_CHARS = "OXT12";

	/** Construct a CircuitBoard from a given board input file, where the first
	 * line contains the number of rows and columns as ints and each subsequent
	 * line is one row of characters representing the contents of that position.
	 * Valid characters are as follows:
	 *  'O' an open position
	 *  'X' an occupied, unavailable position
	 *  '1' first of two components needing to be connected
	 *  '2' second of two components needing to be connected
	 *  'T' is not expected in input files - represents part of the trace
	 *   connecting components 1 and 2 in the solution
	 * 
	 * @param filename
	 * 		file containing a grid of characters
	 * @throws FileNotFoundException if Scanner cannot read the file
	 * @throws InvalidFileFormatException for any other format or content issue that prevents reading a valid input file
	 */
	public CircuitBoard(String filename) throws FileNotFoundException, InvalidFileFormatException {
		Scanner fileScan = new Scanner(new File(filename));
		String linereader = fileScan.nextLine();
		Scanner lineScan = new Scanner(linereader);
		int count=0;
		int myrow=0;
		int mycol=0;
		while(lineScan.hasNext()) {
			if(!lineScan.hasNextInt()) {
				throw new InvalidFileFormatException("Had a value which was not an int");
			}else {
				if(count==0) {
					myrow=lineScan.nextInt();
				}else if(count==1) {
					mycol=lineScan.nextInt();
				}else if(count>=2){
					throw new InvalidFileFormatException("More than 2 values");
				}
			}
			count++;
		}
		lineScan.close();
		ROWS=myrow;
		COLS = mycol;
		if(ROWS==0||COLS==0) {
			throw new InvalidFileFormatException("Cannot Have A Size Value of 0");
		}
		
		board = new char[ROWS][COLS];
		int currow=0;
		for(int j=0;j<ROWS;j++) {
			if(fileScan.hasNextLine()) {
				linereader=fileScan.nextLine();
			}else {
				throw new InvalidFileFormatException("Too Few Rows");
			}
			Scanner Scan = new Scanner(linereader);
			for(int i=0;i<COLS;i++) {
				String val;
				if(Scan.hasNext()) {
					val = Scan.next();
				}else {
					throw new InvalidFileFormatException("Too Few Cols");
				}
				
				if(ALLOWED_CHARS.contains(val)) {
					board[j][i] = val.charAt(0);
					if(board[j][i] == '1') {
						if(startingPoint!=null) {
							throw new InvalidFileFormatException("Too Many Starting Points");
						}
						startingPoint= new Point(j,i);
					}
					if(board[j][i]=='2') {
						if(endingPoint!=null) {
							throw new InvalidFileFormatException("Too Many Ending Points");
						}
						endingPoint = new Point(j,i);
					}
				}else {
					throw new InvalidFileFormatException("Char Used Which Should Not Be Used In File");
				}
			}
			if(Scan.hasNext()) {
				throw new InvalidFileFormatException("Too many cols");
			}
			Scan.close();
		}
		if(fileScan.hasNextLine()) {
			throw new InvalidFileFormatException("Too many rows");
		}
		if(startingPoint==null) {
			throw new InvalidFileFormatException("No Start Point");
		}
		if(endingPoint==null) {
			throw new InvalidFileFormatException("No End Point");
		}
		fileScan.close();
	}
	
	/** Copy constructor - duplicates original board
	 * 
	 * @param original board to copy
	 */
	public CircuitBoard(CircuitBoard original) {
		board = original.getBoard();
		startingPoint = new Point(original.startingPoint);
		endingPoint = new Point(original.endingPoint);
		ROWS = original.numRows();
		COLS = original.numCols();
	}

	/** utility method for copy constructor
	 * @return copy of board array */
	private char[][] getBoard() {
		char[][] copy = new char[board.length][board[0].length];
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				copy[row][col] = board[row][col];
			}
		}
		return copy;
	}
	
	/** Return the char at board position x,y
	 * @param row row coordinate
	 * @param col col coordinate
	 * @return char at row, col
	 */
	public char charAt(int row, int col) {
		return board[row][col];
	}
	
	/** Return whether given board position is open
	 * @param row
	 * @param col
	 * @return true if position at (row, col) is open 
	 */
	public boolean isOpen(int row, int col) {
		if (row < 0 || row >= board.length || col < 0 || col >= board[row].length) {
			return false;
		}
		return board[row][col] == OPEN;
	}
	
	/** Set given position to be a 'T'
	 * @param row
	 * @param col
	 * @throws OccupiedPositionException if given position is not open
	 */
	public void makeTrace(int row, int col) {
		if (isOpen(row, col)) {
			board[row][col] = TRACE;
		} else {
			throw new OccupiedPositionException("row " + row + ", col " + col + "contains '" + board[row][col] + "'");
		}
	}
	
	/** @return starting Point(row,col) */
	public Point getStartingPoint() {
		return new Point(startingPoint);
	}
	
	/** @return ending Point(row,col) */
	public Point getEndingPoint() {
		return new Point(endingPoint);
	}
	
	/** @return number of rows in this CircuitBoard */
	public int numRows() {
		return ROWS;
	}
	
	/** @return number of columns in this CircuitBoard */
	public int numCols() {
		return COLS;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				str.append(board[row][col] + " ");
			}
			str.append("\n");
		}
		return str.toString();
	}
	
}// class CircuitBoard
