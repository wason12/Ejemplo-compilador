package ast.expressions;

import ast.definitions.Definition;
import visitor.Visitor;

public class Variable extends AbstractExpression {

	private String name;
	private Definition definicion;

	public Variable(int line, int column, String name) {
		super(line, column);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}

	public Definition getDefinicion() {
		return definicion;
	}

	public void setDefinicion(Definition definicion) {
		this.definicion = definicion;
	}
	
}
