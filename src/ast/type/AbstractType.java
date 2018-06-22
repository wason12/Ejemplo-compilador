package ast.type;

import java.util.List;

import ast.main.ASTNodeDefault;

public abstract class AbstractType extends ASTNodeDefault implements Type {

	public AbstractType(int line, int column) {
		super(line, column);
	}

	@Override
	public boolean isLogical() {
		return false;
	}

	@Override
	public Type arithmetic(Type other) {
		return null;
	}

	@Override
	public Type arithmetic() {
		return null;
	}

	@Override
	public Type comparision(Type other) {
		return null;
	}

	@Override
	public Type logical(Type other) {
		if (this.isLogical() && other.isLogical())
			return IntType.getInstancia();
		if (other instanceof ErrorType)
			return other;

		return null;
	}

	@Override
	public Type logical() {
		if (this.isLogical())
			return IntType.getInstancia();

		return null;
	}

	@Override
	public Type promoteTo(Type other) {
		return null;
	}

	@Override
	public Type canBeCastTo(Type other) {
		return null;
	}

	@Override
	public Type dot(String right) {
		return null;
	}

	@Override
	public Type squareBrackets(Type right) {
		return null;
	}

	@Override
	public Type parentesis(List<Type> lista) {
		return null;
	}
	
	@Override
	public int getNumberOfBytes() {
		return 0;
	}
	
	@Override
	public char getSuffix() {
		throw new IllegalStateException("ERROR: Estás invocando un sufijo no definido.");
	}

	@Override
	public Type superType(Type type) {
		return null;
	}
	
	@Override
	public boolean printable() {
		return false;
	}
	
	@Override
	public String toComment() {
		return "ERROR";
	}
}
