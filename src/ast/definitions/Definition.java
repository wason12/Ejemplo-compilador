package ast.definitions;

import ast.main.ASTNode;
import ast.type.Type;

public interface Definition extends ASTNode {

	public String getName();

	public Type getType();

}
