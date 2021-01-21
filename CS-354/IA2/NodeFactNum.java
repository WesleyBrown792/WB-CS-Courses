//this is a subclass of NodeFact and has its subclasses
//take in information from the node class to build a parse tree
//this specific subclass allows for the usage of numbers in the parse tree

public class NodeFactNum extends NodeFact {

    private String num;

    public NodeFactNum(String num) {
	this.num=num;
    }

    public double eval(Environment env) throws EvalException {
	return Double.parseDouble(num);
    }

}
