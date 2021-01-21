public class NodeWR extends NodeStmt{
	
	private NodeExpr write;
	
	public NodeWR(NodeExpr expr) {
		this.write = expr;
	}
	
	
	//I will also need to add in whatever we do in the read
	public double eval(Environment env) throws EvalException {
		System.out.println(write.eval(env));
		return write.eval(env);
	}
	
	
}
