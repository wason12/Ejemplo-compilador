package ast.expressions;

import visitor.Visitor;

public class Indexing extends AbstractExpression {

	private Expression left;
	private Expression indice;

	public Indexing(int line, int column, Expression left, Expression right) {
		super(line, column);
		this.left = left;
		this.indice = right;
	}

	public Expression getLeft() {
		return left;
	}

	public void setLeft(Expression left) {
		this.left = left;
	}

	public Expression getIndice() {
		return indice;
	}

	public void setIndice(Expression right) {
		this.indice = right;
	}

	@Override
	public String toString() {
		return left.toString() + "[" + indice.toString() + "]";
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
}
