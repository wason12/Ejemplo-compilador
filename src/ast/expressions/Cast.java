package ast.expressions;

import ast.type.Type;
import visitor.Visitor;

public class Cast extends AbstractExpression {

	private Type castType;
	private Expression right;

	public Cast(int line, int column, Type castType, Expression right) {
		super(line, column);
		this.castType = castType;
		this.right = right;
	}

	public Type getCastType() {
		return castType;
	}

	public void setCastType(Type castType) {
		this.castType = castType;
	}

	public Expression getRight() {
		return right;
	}

	public void setRight(Expression right) {
		this.right = right;
	}
	
	@Override
	public String toString() {
		return String.format("((%s)%s)", castType.toString(),right.toString());
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
	
private boolean lValue;
	
	@Override
	public boolean getLValue() {
		return lValue;
	}

	@Override
	public void setLValue(boolean bool) {
		lValue = bool;
	}
	
}
