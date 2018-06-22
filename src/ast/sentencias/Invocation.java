package ast.sentencias;

import java.util.List;

import ast.expressions.AbstractExpression;
import ast.expressions.Expression;
import ast.expressions.Variable;
import visitor.Visitor;

public class Invocation extends AbstractExpression implements Statement, Expression {

	private Variable function;
	private List<Expression> arguments;
	private boolean terminable;

	public Invocation(int line, int column, Variable function, List<Expression> arguments) {
		super(line, column);
		this.function = function;
		this.arguments = arguments;
	}

	public Variable getFunction() {
		return function;
	}

	public void setFunction(Variable function) {
		this.function = function;
	}

	public List<Expression> getArguments() {
		return arguments;
	}

	public void setArguments(List<Expression> arguments) {
		this.arguments = arguments;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(function.toString() + "(");
		int tam = arguments.size();
		for(int i=0 ; i<tam ; i++) {
			str.append(arguments.get(i).toString());
			
			if(i<tam-1)
				str.append(',');
		}
		str.append(")");
		
		return str.toString();
	}
	
	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}

	@Override
	public boolean getTerminable() {
		return terminable;
	}

	@Override
	public void setTerminable(boolean bool) {
		terminable = bool;
	}

}
