//this is a subclass of NodeFact and has its subclasses
//take in information from the node class to build a parse tree

public class NodeFactExpr extends NodeFact {

    private NodeExpr expr;

    public NodeFactExpr(NodeExpr expr) {
	this.expr=expr;
    }

    public double eval(Environment env) throws EvalException {
	return expr.eval(env);
    }

}
