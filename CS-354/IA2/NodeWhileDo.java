public class NodeWhileDo extends NodeStmt{
	
	private NodeBoolexpr bool;
	private NodeStmt stmt;
	
	public NodeWhileDo(NodeBoolexpr bool, NodeStmt stmt) {
		this.bool = bool;
		this.stmt = stmt;
	}
	
	//I will also need to add in whatever we do in the read
	public double eval(Environment env) throws EvalException {
		while(bool.eval(env)==1) {
			stmt.eval(env);
		}
		return 0;
	}
	
	
}