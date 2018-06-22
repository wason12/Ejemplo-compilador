package ast.expressions;

import visitor.Visitor;

public class FieldAccess extends AbstractExpression {

	private Expression left;
	private String name;

	public FieldAccess(int line, int column, Expression left, String name) {
		super(line, column);
		this.left = left;
		this.name = name;
	}

	public Expression getLeft() {
		return left;
	}

	public void setLeft(Expression left) {
		this.left = left;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return String.format("%s.%s", left.toString(),name);
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
	
}
