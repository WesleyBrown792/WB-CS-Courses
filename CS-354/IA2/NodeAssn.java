//this is a subclass of Node and has its subclasses
//take in information from the node class to build a parse tree

public class NodeAssn extends NodeStmt {

    private String id;
    private NodeExpr expr;

    public NodeAssn(String id, NodeExpr expr) {
	this.id=id;
	this.expr=expr;
    }

    public double eval(Environment env) throws EvalException {
	return env.put(id,expr.eval(env));
    }

}
