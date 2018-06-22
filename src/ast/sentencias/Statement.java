package ast.sentencias;

import ast.main.ASTNode;

public interface Statement extends ASTNode {
	
	public boolean getTerminable();
	public void setTerminable(boolean bool);

}
