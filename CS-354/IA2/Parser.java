// This class is a recursive-descent parser,
// modeled after the programming language's grammar.
// It constructs and has-a Scanner for the program
// being parsed.

import java.util.*;

public class Parser {

    private Scanner scanner;

    private void match(String s) throws SyntaxException {
	scanner.match(new Token(s));
    }

    private Token curr() throws SyntaxException {
	return scanner.curr();
    }

    private int pos() {
	return scanner.pos();
    }
    
    private NodeRelop parseRelop() throws SyntaxException {
    	if (curr().equals(new Token("<"))) {
    	    match("<");
    	    return new NodeRelop(pos(),"<");
    	}
    	if (curr().equals(new Token(">"))) {
    	    match(">");
    	    return new NodeRelop(pos(),">");
    	}
    	if (curr().equals(new Token("<="))) {
    	    match("<=");
    	    return new NodeRelop(pos(),"<=");
    	}
    	if (curr().equals(new Token(">="))) {
    	    match(">=");
    	    return new NodeRelop(pos(),">=");
    	}
    	if (curr().equals(new Token("<>"))) {
    	    match("<>");
    	    return new NodeRelop(pos(),"<>");
    	}
    	if (curr().equals(new Token("=="))) {
    	    match("==");
    	    return new NodeRelop(pos(),"==");
    	}
    	return null;
    }
    
    private NodeMulop parseMulop() throws SyntaxException {
	if (curr().equals(new Token("*"))) {
	    match("*");
	    return new NodeMulop(pos(),"*");
	}
	if (curr().equals(new Token("/"))) {
	    match("/");
	    return new NodeMulop(pos(),"/");
	}
	return null;
    }

    private NodeAddop parseAddop() throws SyntaxException {
	if (curr().equals(new Token("+"))) {
	    match("+");
	    return new NodeAddop(pos(),"+");
	}
	if (curr().equals(new Token("-"))) {
	    match("-");
	    return new NodeAddop(pos(),"-");
	}
	return null;
    }

    private NodeFact parseFact() throws SyntaxException {
	if (curr().equals(new Token("("))) {
	    match("(");
	    NodeExpr expr=parseExpr();
	    match(")");
	    return new NodeFactExpr(expr);
	}
	if (curr().equals(new Token("id"))) {
	    Token id=curr();
	    match("id");
	    return new NodeFactId(pos(),id.lex());
	}
	if(curr().equals(new Token("-"))) {
		match("-");
		NodeFact fact = parseFact();
		return new NodeFactUnaryMinus(fact);
	}
	Token num=curr();
	match("num");
	return new NodeFactNum(num.lex());
    }

    private NodeTerm parseTerm() throws SyntaxException {
	NodeFact fact=parseFact();
	NodeMulop mulop=parseMulop();
	if (mulop==null)
	    return new NodeTerm(fact,null,null);
	NodeTerm term=parseTerm();
	term.append(new NodeTerm(fact,mulop,null));
	return term;
    }

    private NodeExpr parseExpr() throws SyntaxException {
	NodeTerm term=parseTerm();
	NodeAddop addop=parseAddop();
	if (addop==null)
	    return new NodeExpr(term,null,null);
	NodeExpr expr=parseExpr();
	expr.append(new NodeExpr(term,addop,null));
	return expr;
    }
    
    private NodeBoolexpr parseBool() throws SyntaxException{
    	NodeExpr expr1=parseExpr();
    	NodeRelop relop=parseRelop();
    	NodeExpr expr2=parseExpr();
    	return new NodeBoolexpr(expr1, relop, expr2);
    }

    private NodeAssn parseAssn() throws SyntaxException {
	Token id=curr();
	match("id");
	match("=");
	NodeExpr expr=parseExpr();
	NodeAssn assn=new NodeAssn(id.lex(),expr);
	return assn;
    }

    private NodeStmt parseStmt() throws SyntaxException {//this will need to be changed to allow the parser to understand that something is up
    if (curr().equals(new Token("wr"))) {
    	match("wr");
    	NodeExpr expr=parseExpr();
    	return new NodeWR(expr);
    }else {
    if (curr().equals(new Token("rd"))) {
    	match("rd");
    	NodeFactId holder=(NodeFactId) parseFact();
    	return new NodeRD(holder.id);
    }else {
    if (curr().equals(new Token("if"))) {
    	match("if");
    	NodeBoolexpr bool=parseBool();
    	match("then");
    	NodeStmt stmt1=parseStmt();
    	NodeStmt stmt2=null;
    	if(curr().equals(new Token("else"))) {
    		match("else");
    		stmt2=parseStmt();
    		//return new NodeIfThenElse(bool,stmt1,stmt2);
    	//}else {
    	}
    	return new NodeIfThenElse(bool,stmt1,stmt2);
    	//}	
    }else {
    if (curr().equals(new Token("while"))) {
    	match("while");
    	NodeBoolexpr bool=parseBool();
    	match("do");
    	NodeStmt stmt = parseStmt();
    	NodeWhileDo whiledo = new NodeWhileDo(bool, stmt);
    	return whiledo;
    }else {
    if(curr().equals(new Token("begin"))) {
    	match("begin");
    	NodeBlock block=parseBlock();
    	match("end");
    	return new NodeBE(block);
    }else {
    	NodeAssn assn=parseAssn();
    	return assn;
    }
    	
    }}}}
    
    }
    
    private NodeBlock parseBlock() throws SyntaxException {
    NodeStmt stmt=parseStmt();
    if(curr().equals(new Token(";"))) {
    	match(";");
    	NodeBlock block = parseBlock();
    	return new NodeBlock(stmt,block);
    }else {
    	NodeBlock block=new NodeBlock(stmt);
        return block;
    }
    }
    
    private NodeProg parseProg() throws SyntaxException {
    NodeBlock block=parseBlock();
    NodeProg prog=new NodeProg(block);
    return prog;
    }

    public Node parse(String program) throws SyntaxException {
	scanner=new Scanner(program);
	scanner.next();
	NodeProg prog=parseProg();
	match("EOF");
	return prog;
    }

}
