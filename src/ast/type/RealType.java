package ast.type;

import visitor.Visitor;

public class RealType extends AbstractType {

	private static RealType instancia = new RealType();

	private RealType() {
		super(0, 0);
	}

	public static RealType getInstancia() {
		return instancia;
	}

	@Override
	public String toString() {
		return "real";
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	@Override
	public Type arithmetic(Type other) {
		if (other instanceof ErrorType || other instanceof RealType)
			return other;
		if (other instanceof CharType || other instanceof IntType)
			return this;

		return null;
	}

	@Override
	public Type arithmetic() {
		return this;
	}

	@Override
	public Type comparision(Type other) {
		if (other instanceof CharType || other instanceof IntType || other instanceof RealType)
			return IntType.getInstancia();

		return null;
	}

	@Override
	public Type promoteTo(Type other) {
		if (other instanceof ErrorType || other instanceof RealType)
			return other;

		return null;
	}
	
	@Override
	public Type canBeCastTo(Type other) {
		return other;
	}
	
	@Override
	public int getNumberOfBytes() {
		return 4;
	}
	
	@Override
	public char getSuffix() {
		return 'f';
	}
	
	@Override
	public Type superType(Type other) {
		return this;
	}
	
	@Override
	public boolean printable() {
		return true;
	}
	
	@Override
	public String toComment() {
		return this.getClass().getSimpleName();
	}

}
