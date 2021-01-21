public class NodeBE extends NodeStmt{
	private NodeBlock block;
	
	public NodeBE(NodeBlock block) {
		this.block=block;
	}
	
	public double eval(Environment env) throws EvalException{
		return block.eval(env);
	}
}
