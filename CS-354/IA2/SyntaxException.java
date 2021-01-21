//this is a specialized exception made to allow the parser
//to find any errors in the syntax of the values being parsed

public class SyntaxException extends Exception {

    private int pos;
    private Token expected;
    private Token found;

    public SyntaxException(int pos, Token expected, Token found) {
	this.pos=pos;
	this.expected=expected;
	this.found=found;
    }

    public String toString() {
	return "syntax error"
	    +", pos="+pos
	    +", expected="+expected
	    +", found="+found;
    }

}
