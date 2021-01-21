//this is a subclass of Node and has its subclasses
//take in information from the node class to build a parse tree

public class NodeProg extends Node {

    private NodeBlock block;

    public NodeProg(NodeBlock block) {
	this.block=block;
    }

    public double eval(Environment env) throws EvalException {
	return block.eval(env);
    }

}