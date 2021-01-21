//this is a subclass of Node and has its subclasses
//take in information from the node class to build a parse tree
//this class allows for the use of specific relops within the parse tree

public class NodeRelop extends Node {

    private String relop;

    public NodeRelop(int pos, String relop) {
	this.pos=pos;
	this.relop=relop;
    }

    public double op(double o1, double o2) throws EvalException {
	if (relop.equals("<")) {
		if(o1<o2) {
			return 1.0;
		}else {
			return 0.0;
		}
	}	
	if (relop.equals(">")) {
		if(o1>o2) {
			return 1.0;
		}else {
			return 0.0;
		}
	}
	if (relop.equals("<>")) {
		if(o1!=o2) {
			return 1.0;
		}else {
			return 0.0;
		}
	}	
	if (relop.equals("==")) {
		if(o1==o2) {
			return 1.0;
		}else {
			return 0.0;
		}
	}
	if (relop.equals("<=")) {
		if(o1<=o2) {
			return 1.0;
		}else {
			return 0.0;
		}
	}	
	if (relop.equals(">=")) {
		if(o1>=o2) {
			return 1.0;
		}else {
			return 0.0;
		}
	}
	throw new EvalException(pos,"bogus relop: "+relop);
    }

}
