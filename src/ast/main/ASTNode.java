package ast.main;

import visitor.Visitor;

public interface ASTNode {

	public int getLine();

	public int getColumn();
	
	public Object accept(Visitor v, Object params);

}
