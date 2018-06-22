package ast.type;

import java.util.List;

import ast.main.ASTNode;
import errorhandler.EH;
import visitor.Visitor;

public class ErrorType extends AbstractType{

	private String message;
	
	private ErrorType(int line, int column) {
		super(line, column);
		EH.getInstancia().addError(this);
	}	

	public ErrorType(int line, int column, String message) {
		this(line, column);
		this.setMessage(message);
	}
	
	public ErrorType(ASTNode nodoErroneo, String message) {
		this(nodoErroneo.getLine(), nodoErroneo.getColumn(), message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = String.format("ERROR en linea %d, columna %d: %s", getLine(), getColumn(), message);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorType other = (ErrorType) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
	
	@Override
	public String toString() {
		return "error";
	}
	
	@Override
	public Type arithmetic(Type other) {
		return this;
	}

	@Override
	public Type arithmetic() {
		return this;
	}

	@Override
	public Type comparision(Type other) {
		return this;
	}
	
	@Override
	public Type logical(Type other) {
		return this;
	}

	@Override
	public Type logical() {
		return this;
	}

	@Override
	public Type promoteTo(Type other) {
		return this;
	}

	@Override
	public Type canBeCastTo(Type other) {
		return this;
	}

	@Override
	public Type dot(String right) {
		return this;
	}

	@Override
	public Type squareBrackets(Type right) {
		return this;
	}

	@Override
	public Type parentesis(List<Type> lista) {
		return this;
	}
	
	@Override
	public boolean printable() {
		return true;
	}
	
}
