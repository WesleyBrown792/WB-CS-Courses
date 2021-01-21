import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Array based implementation of the IList ADT 
 * @author Wesley Brown
 *
 * 
 */
public class FormatChecker {
	/**
	 * Creates a for each loop to run through all of the arguments
	 * @param args
	 */
	public static void main(String[] args) {
		//this for each loop is what allows the program to run through each of the files that are passed on
		for(String filename : args) {
			try {
				checkFormat(filename);
				System.out.println("VALID");
			} catch (FileNotFoundException fnfe) {
				System.out.println(fnfe);
				System.out.println("INVALID");
			}catch(InputMismatchException IME) {
				System.out.println(IME);
				System.out.println("INVALID");
			}catch(NumberFormatException NFE) {
				System.out.println(NFE);
				System.out.println("INVALID");
			}catch(DimensionMismatchException DME) {
				System.out.println(DME);
				System.out.println("INVALID");
			}
		}
		
	}
		/**
		 * This method takes in the filename for the given argument and will fill out and check an array based on the data given in the file
		 * @param filename
		 * @throws FileNotFoundException
		 * @throws InputMismatchException
		 * @throws NumberFormatException
		 * @throws DimensionMismatchException
		 */
		public static void checkFormat(String filename)throws FileNotFoundException,InputMismatchException,NumberFormatException,DimensionMismatchException {
			File file = new File(filename);
			//creates the file and scanners to run through the files
			Scanner scan = new Scanner(file);
			String LineReader = scan.nextLine();
			Scanner lineScanner = new Scanner(LineReader);
			
			//takes in the first line and checks to see if the first line has correct dimensions
			int dimensionCount=2;
			int collum=0;
			int row=0;
			int i=0;//i holds the current spot on the array bellow
			int [] holdcolrow = new int [2];
			//this while loop will run through the lineScanner and check the first line of the file for a DimensionMismatchException
			while(lineScanner.hasNext()) {
				if(!lineScanner.hasNextInt()) {
					throw new InputMismatchException();
				}
				if(i>=2) {
					throw new DimensionMismatchException();
				}
				holdcolrow[i]=lineScanner.nextInt();
				i++;
			}
			//finished finding any errors in the first line then will fill in the newly created array.
			collum = holdcolrow[0];
			row = holdcolrow[1];
			double [][] checkme = new double [collum][row];
		    //these create the variables that I use to walk through the array made above and fill it out.
			int curCollum = 0;
			int curRow = 0;
			
			//the above two ints hold the current row and collum that is being filled in
			//the while loops below will both fill in the array as well as check to see if there is any overflow error
			while(scan.hasNext()) {
				//this first if checks for any time when there are more collums in the file than originally stated
				if(curCollum >= collum) {
					throw new DimensionMismatchException();
				}
				LineReader = scan.nextLine();
				Scanner NScan = new Scanner(LineReader);//this scanner will read through each line as the line scanner used at the beginning was having issues.
				while(NScan.hasNext()) {
					//this if throws errors just like the if in the first while loop only this time it is based on the rows not the collums
					if(curRow >= row) {
						throw new DimensionMismatchException();
					}
					//this set of if/else statements will check to see if I will add and int, double, or throw an error
					if(NScan.hasNextInt()) {
						checkme[curCollum][curRow] = NScan.nextInt();
						
					}else {
						if(NScan.hasNextDouble()) {
							checkme[curCollum][curRow] = NScan.nextDouble();
							
						}else {
							throw new InputMismatchException();
						}
					}
					curRow++;
				}
				curRow=0;
				//this will be a secondary check for the first if statement 
				if(lineScanner.hasNext()&&curCollum>=collum) {
					throw new DimensionMismatchException();
				}
				curCollum++;
			}
		}
}
	
