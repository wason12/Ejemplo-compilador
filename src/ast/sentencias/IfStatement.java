package ast.sentencias;

import java.util.List;

import ast.expressions.Expression;
import visitor.Visitor;

public class IfStatement extends AbstractStatement {

	private Expression condition;
	private List<Statement> ifBody;
	private List<Statement> elseBody;

	public IfStatement(int line, int column, Expression condition, List<Statement> ifBody, List<Statement> elseBody) {
		super(line, column);
		this.condition = condition;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
	}

	public Expression getCondition() {
		return condition;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}

	public List<Statement> getIfBody() {
		return ifBody;
	}

	public void setIfBody(List<Statement> ifBody) {
		this.ifBody = ifBody;
	}

	public List<Statement> getElseBody() {
		return elseBody;
	}

	public void setElseBody(List<Statement> elseBody) {
		this.elseBody = elseBody;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(String.format("IF %s :{",condition.toString()));
		for(Statement st : ifBody)
			str.append("\n\t\t" + st.toString());
		str.append("\n\t}");
		if(!elseBody.isEmpty()) {
			str.append("ELSE{");
			for(Statement st : elseBody)
				str.append("\n\t\t" + st.toString());
			str.append("\n\t}\n");
		}
		
		return str.toString();
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
}
