//this is a subclass of Node and has its subclasses
//take in information from the node class to build a parse tree

public class NodeBlock extends Node {

    private NodeStmt stmt;
    private NodeBlock block;

    public NodeBlock(NodeStmt stmt) {
	this.stmt=stmt;
	this.block=null;
    }
    
    public NodeBlock(NodeStmt stmt, NodeBlock block) {
    	this.stmt=stmt;
    	this.block=block;
    }

    public double eval(Environment env) throws EvalException {
		double eval = stmt.eval(env);
		
		if(block!=null) {
			return block.eval(env);
		}else {
			return eval;
		}
    }

}

