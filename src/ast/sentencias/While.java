package ast.sentencias;

import java.util.List;

import ast.expressions.Expression;
import visitor.Visitor;

public class While extends AbstractStatement {

	private Expression condition;
	private List<Statement> body;

	public While(int line, int column, Expression condition, List<Statement> body) {
		super(line, column);
		this.body = body;
		this.condition = condition;
	}

	public List<Statement> getBody() {
		return body;
	}

	public void setBody(List<Statement> body) {
		this.body = body;
	}

	public Expression getCondition() {
		return condition;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(String.format("WHILE (%s){",condition.toString()));
		for(Statement st : body)
			str.append("\n\t\t" + st.toString());
		str.append("\n\t}");
		
		return str.toString();
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
	
}
