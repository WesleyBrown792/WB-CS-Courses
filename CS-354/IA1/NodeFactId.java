//this is a subclass of NodeFact and has its subclasses
//take in information from the node class to build a parse tree
//this allows for named ids to be used in the parse tree

public class NodeFactId extends NodeFact {

    private String id;

    public NodeFactId(int pos, String id) {
	this.pos=pos;
	this.id=id;
    }

    public double eval(Environment env) throws EvalException {
	return env.get(pos,id);
    }

}
