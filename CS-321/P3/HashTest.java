import java.io.FileNotFoundException;
import java.io.IOException;

public class HashTest {
	public static void main(String args[]) throws IOException {
		int input = findinput(args[0]);
		double load = findload(args[1]);
		int debug;
		if(args.length==2) {
			debug=0;
		}else {
			debug=finddebug(args[2]);
		}
		HashTable Test = new HashTable(input,load,debug,0);
		Test.debug(debug);
		
	}

	private static int finddebug(String string) {
		return Integer.parseInt(string);
	}

	private static double findload(String string) {
		return Double.parseDouble(string);
	}

	private static int findinput(String string) {
		return Integer.parseInt(string);
	}
}
