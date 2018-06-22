package codeGenerator;

import ast.expressions.Arithmetic;
import ast.expressions.Cast;
import ast.expressions.CharLiteral;
import ast.expressions.Comparision;
import ast.expressions.Expression;
import ast.expressions.FieldAccess;
import ast.expressions.Indexing;
import ast.expressions.IntLiteral;
import ast.expressions.Logical;
import ast.expressions.RealLiteral;
import ast.expressions.UnaryMinus;
import ast.expressions.UnaryNot;
import ast.expressions.Variable;
import ast.sentencias.Invocation;
import ast.type.FunctionType;
import ast.type.IntType;
import ast.type.Type;

public class ValueVisitor extends AbstractCGVisitor {

	private CodeGenerator cg;
	private AddressVisitor address;

	public AddressVisitor getAddress() {
		return address;
	}

	public void setAddress(AddressVisitor address) {
		this.address = address;
	}

	public ValueVisitor(CodeGenerator cg) {
		this.cg = cg;
	}
	
	

	@Override
	public Object visit(IntLiteral obj, Object params) {

		cg.push(obj.getValue());

		return null;
	}
	
	@Override
	public Object visit(RealLiteral obj, Object params) {

		cg.push(obj.getValue());

		return null;
	}
	
	@Override
	public Object visit(CharLiteral obj, Object params) {

		cg.push(obj.getValue());

		return null;
	}

	public Object visit(Variable obj, Object params) {
		obj.accept(address, params);
		
		cg.load(obj.getType().getSuffix());
		
		return null;
	}

	public Object visit(Arithmetic obj, Object params) {
		obj.getLeft().accept(this, params);
		cg.convert(obj.getLeft().getType(), obj.getType());
		
		obj.getRight().accept(this, params);
		cg.convert(obj.getRight().getType(), obj.getType());
		
		cg.arithmetic(obj.getType(), obj.getOperation());
		
		return null;
	}

	public Object visit(Comparision obj, Object params) {
		Type superType = obj.getLeft().getType().superType(obj.getRight().getType());
		
		obj.getLeft().accept(this, params);
		cg.convert(obj.getLeft().getType(), superType);
		
		obj.getRight().accept(this, params);
		cg.convert(obj.getRight().getType(), superType);
		
		cg.comparision(superType, obj.getOperation());
		
		return null;
	}
	
	@Override
	public Object visit(Cast obj, Object params) {
		obj.getRight().accept(this, params);
		
		cg.convert(obj.getRight().getType(), obj.getCastType());
		
		return null;
	}
	
	@Override
	public Object visit(Indexing obj, Object params) {
		obj.accept(address, params);
		
		cg.load(obj.getType().getSuffix());
		
		return null;
	}
	
	@Override
	public Object visit(FieldAccess obj, Object params) {
		obj.accept(address, params);
		
		cg.load(obj.getType().getSuffix());
		
		return null;
	}
	
	@Override
	public Object visit(Invocation obj, Object params) {
		int i = 0 ;
		FunctionType functionType = (FunctionType) obj.getFunction().getType();
		for(Expression e : obj.getArguments()) {
			e.accept(this, params);
			cg.convert(e.getType(), functionType.getParameters().get(i++).getType());
		}
		
		cg.call(obj.getFunction().getName());
		
		return null;
	}
	
	@Override
	public Object visit(Logical obj, Object params) {
		obj.getLeft().accept(this, params);
		cg.convert(obj.getLeft().getType(), IntType.getInstancia());
		
		obj.getRight().accept(this, params);
		cg.convert(obj.getRight().getType(), IntType.getInstancia());
		
		cg.logical(obj.getOperation());
		
		return null;
	}
	
	@Override
	public Object visit(UnaryNot obj, Object params) {
		obj.getValue().accept(this, params);
		cg.convert(obj.getValue().getType(), IntType.getInstancia());
		
		cg.write("\tnot");
		
		return null;
	}
	
	@Override
	public Object visit(UnaryMinus obj, Object params) {
		obj.getValue().accept(this, params);
		cg.convert(obj.getValue().getType(), obj.getType());
		
		cg.push(-1);
		cg.convert(IntType.getInstancia(), obj.getType());
		
		cg.arithmetic(obj.getType(), "*");
		
		return null;
	}
	
	
	

}
