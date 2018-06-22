package ast.expressions;

import visitor.Visitor;

public class UnaryNot extends AbstractExpression {

	private Expression value;

	public UnaryNot(int line, int column, Expression value) {
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
		return "!" + value.toString();
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
	
}
