package ast.type;

import visitor.Visitor;

public class IntType extends AbstractType {

	private static IntType instancia = new IntType();

	private IntType() {
		super(0, 0);
	}

	public static IntType getInstancia() {
		return instancia;
	}

	@Override
	public String toString() {
		return "int";
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
		if (other instanceof ErrorType || other instanceof IntType || other instanceof RealType)
			return other;
		if (other instanceof CharType)
			return this;

		return null;
	}

	@Override
	public Type arithmetic() {
		return this;
	}

	@Override
	public boolean isLogical() {
		return true;
	}

	@Override
	public Type comparision(Type other) {
		if (other instanceof CharType || other instanceof IntType || other instanceof RealType)
			return IntType.getInstancia();

		return null;
	}

	@Override
	public Type promoteTo(Type other) {
		if (other instanceof ErrorType || other instanceof IntType || other instanceof RealType)
			return other;

		return null;
	}
	
	@Override
	public Type canBeCastTo(Type other) {
		return other;
	}
	
	@Override
	public int getNumberOfBytes() {
		return 2;
	}
	
	@Override
	public char getSuffix() {
		return 'i';
	}
	
	@Override
	public Type superType(Type other) {
		if(other instanceof RealType)
			return other;
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
