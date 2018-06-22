package ast.expressions;

import visitor.Visitor;

public class UnaryMinus extends AbstractExpression {

	private Expression value;

	public UnaryMinus(int line, int column, Expression value) {
		super(line, column);
		this.value = value;
	}

	public Expression getValue() {
		return value;
	}

	public void setValue(Expression value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "-" + value.toString();
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
	
}
