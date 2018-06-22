package ast.expressions;

import visitor.Visitor;

public class IntLiteral extends AbstractExpression {

	private int value;

	public IntLiteral(int line, int column, int value) {
		super(line, column);
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
	
}
