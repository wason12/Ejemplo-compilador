package ast.type;

import java.util.List;

import ast.main.ASTNode;

public interface Type extends ASTNode {
	
	public boolean isLogical();
	public Type arithmetic(Type other);
	public Type arithmetic();
	public Type comparision(Type other);
	public Type logical(Type other);
	public Type logical();
	public Type promoteTo(Type other);
	public Type canBeCastTo(Type other);
	public Type dot(String right);
	public Type squareBrackets(Type right);
	public Type parentesis(List<Type> lista);
	public int getNumberOfBytes();
	public char getSuffix();
	public Type superType(Type type);
	

	public boolean printable();
	public String toComment();

}
