package ast.expressions;

import visitor.Visitor;

public class CharLiteral extends AbstractExpression {

	private char value;

	public CharLiteral(int line, int column, char value) {
		super(line, column);
		this.value = value;
	}

	public char getValue() {
		return value;
	}

	public void setValue(char value) {
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

