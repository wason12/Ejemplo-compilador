package ast.expressions;

import visitor.Visitor;

public class Logical extends AbstractExpression {

	private Expression left;
	private Expression right;
	private String operation;

	public Logical(int line, int column, Expression left, String operation, Expression right) {
		super(line, column);
		this.left = left;
		this.right = right;
		this.operation = operation;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
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
		return left.toString() + operation.toString() + right.toString();
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
	
}
