package ast.type;

import visitor.Visitor;

public class ArrayType extends AbstractType {

	private Type type;
	private int size;

	public ArrayType(int line, int column, int size, Type type) {
		super(line, column);
		this.type = type;
		this.size = size;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "[" + size +"]" + type.toString();
	}
	
	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + size;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return this.toString().equals(obj.toString());
	}

	@Override
	public Type promoteTo(Type other) {
		if(other instanceof ErrorType)
			return other;
		if(other instanceof ArrayType)
			if(this.equals(other))
				return this;
		
		return null;
	}
	
	@Override
	public Type squareBrackets(Type right) {
		if(right instanceof IntType || right instanceof CharType)
			return type;
		if(right instanceof ErrorType)
			return right;
		
		return null;
	}
	
	@Override
	public int getNumberOfBytes() {
		return type.getNumberOfBytes() * size;
	}
	
	@Override
	public String toComment() {
		String comment = String.format("ArrayType[of:%s],size:%d]", type.toComment(), size);
		
		return comment;
	}
}
