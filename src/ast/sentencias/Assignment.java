package ast.sentencias;

import ast.expressions.Expression;
import visitor.Visitor;

public class Assignment extends AbstractStatement {

	private Expression left;
	private Expression right;

	public Assignment(int line, int column, Expression left, Expression right) {
		super(line, column);
		this.left = left;
		this.right = right;
	}

	public Expression getLeft() {
		return left;
	}

	public void setLeft(Expression left) {
		this.left = left;
	}

	public Expression getRight() {
		return right;
	}

	public void setRight(Expression right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return left.toString() + "=" + right.toString();
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
	
}
