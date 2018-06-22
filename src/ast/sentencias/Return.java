package ast.sentencias;

import ast.expressions.Expression;
import visitor.Visitor;

public class Return extends AbstractStatement {

	private Expression retorno;

	public Return(int line, int column, Expression retorno) {
		super(line, column);
		this.retorno = retorno;
	}

	public Expression getRetorno() {
		return retorno;
	}

	public void setRetorno(Expression retorno) {
		this.retorno = retorno;
	}

	@Override
	public String toString() {
		return "RETURN  " + retorno.toString();
	}
	
	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}

}
