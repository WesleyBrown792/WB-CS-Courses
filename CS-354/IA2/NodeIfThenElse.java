public class NodeIfThenElse extends NodeStmt{
	
	private NodeBoolexpr bool;
	private NodeStmt stmt;
	private NodeStmt stmt2;
	
	public NodeIfThenElse(NodeBoolexpr bool, NodeStmt stmt, NodeStmt stmt2) {
		this.bool = bool;
		this.stmt = stmt;
		this.stmt2 = stmt2;
	}
	//I will also need to add in whatever we do in the read
	public double eval(Environment env) throws EvalException {
		if(bool.eval(env)==1) {
			return stmt.eval(env);
		}
		if(stmt2==null) {
			return 0;
		}else {
			return stmt2.eval(env);
		}
	}
	
	
}