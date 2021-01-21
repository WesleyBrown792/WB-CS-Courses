//The EvalException appears when an eval of the
//pos comes back as not being correct, the item in pos
//is either not supposed to be there or it was of the wrong type

public class EvalException extends Exception {

    private int pos;
    private String msg;

    public EvalException(int pos, String msg) {
	this.pos=pos;
	this.msg=msg;
    }

    public String toString() {
	return "eval error"
	    +", pos="+pos
	    +", "+msg;
    }

}
