package ast.expressions;

import ast.main.ASTNode;
import ast.type.Type;

public interface Expression extends ASTNode {
	
	public boolean getLValue();
	public void setLValue(boolean bool);
	
	public Type getType();
	public void setType(Type type);	
	
}
