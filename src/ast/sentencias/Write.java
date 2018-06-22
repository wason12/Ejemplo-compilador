package ast.sentencias;

import ast.expressions.Expression;
import visitor.Visitor;

public class Write extends AbstractStatement {

	private Expression expression;

	public Write(int line, int column, Expression expression) {
		super(line, column);
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public String toString() {
		return "Write " + expression.toString();
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
	
}
