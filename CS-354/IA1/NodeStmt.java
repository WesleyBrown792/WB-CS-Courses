//this is a subclass of Node and has its subclasses
//take in information from the node class to build a parse tree

public class NodeStmt extends Node {

    private NodeAssn assn;

    public NodeStmt(NodeAssn assn) {
	this.assn=assn;
    }

    public double eval(Environment env) throws EvalException {
	return assn.eval(env);
    }

}
