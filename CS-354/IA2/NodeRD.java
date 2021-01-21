import java.util.Scanner;
public class NodeRD extends NodeStmt{
	private Scanner scan;
	private String read;
	
	public NodeRD(String id) {
		this.read = id;
	}
	//I will also need to add in whatever we do in the read
	public double eval(Environment env) throws EvalException {
		scan = new Scanner(System.in);
		return env.put(read, scan.nextDouble());
	}
	
	
}