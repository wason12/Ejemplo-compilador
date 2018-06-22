package ast.type;

import visitor.Visitor;

public class VoidType extends AbstractType{

	private static VoidType instancia = new VoidType();

	private VoidType() {
		super(0, 0);
	}

	public static VoidType getInstancia() {
		return instancia;
	}
	
	@Override
	public String toString() {
		return "void";
	}
	
	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
	
	@Override
	public int getNumberOfBytes() {
		return 0;
	}
}
