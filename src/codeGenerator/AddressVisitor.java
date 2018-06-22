package codeGenerator;

import ast.definitions.VarDefinition;
import ast.expressions.FieldAccess;
import ast.expressions.Indexing;
import ast.expressions.Variable;
import ast.type.IntType;
import ast.type.RecordType;

public class AddressVisitor extends AbstractCGVisitor {
	
	private CodeGenerator cg;
	private ValueVisitor value;

	public AddressVisitor(CodeGenerator cg) {
		this.cg = cg;
	}
	
	
	
	public Object visit(Variable obj, Object params) {
		VarDefinition definition = (VarDefinition) obj.getDefinicion();
		
		if(definition.getScope() == 0)
			cg.pushA(definition.getOffset());
		else {
			cg.pushBP();
			cg.push(definition.getOffset());
			cg.add('i');
		}
		
		return null;
	}
	
	public Object visit(Indexing obj, Object params) {
		obj.getLeft().accept(this, params);
		obj.getIndice().accept(value, params);
		
		cg.convert(obj.getIndice().getType(), IntType.getInstancia());
		cg.push(obj.getType().getNumberOfBytes());
		cg.mul('i');
		cg.add('i');
		
		return null;
	}
	
	public Object visit(FieldAccess obj, Object params) {
		obj.getLeft().accept(this, params);

		RecordType recordType = (RecordType) obj.getLeft().getType();
		cg.push(recordType.get(obj.getName()).getOffset());		
		cg.add('i');
		
		return null;
	}
	
	
	
	
	

	public ValueVisitor getValue() {
		return value;
	}

	public void setValue(ValueVisitor value) {
		this.value = value;
	}

}
