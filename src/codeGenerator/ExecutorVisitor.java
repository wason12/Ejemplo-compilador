package codeGenerator;

import ast.definitions.Definition;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.main.Program;
import ast.sentencias.Assignment;
import ast.sentencias.IfStatement;
import ast.sentencias.Invocation;
import ast.sentencias.Read;
import ast.sentencias.Return;
import ast.sentencias.Statement;
import ast.sentencias.While;
import ast.sentencias.Write;
import ast.type.FunctionType;
import ast.type.IntType;
import ast.type.Type;
import ast.type.VoidType;

public class ExecutorVisitor extends AbstractCGVisitor {
	
	private CodeGenerator cg;
	private ValueVisitor value;
	private AddressVisitor address;
	private String in;
	
	
	public ExecutorVisitor(String in, CodeGenerator codeGenerator) {
		super();
		this.cg = codeGenerator;
		value = new ValueVisitor(cg);
		address = new AddressVisitor(cg);
		
		value.setAddress(address);
		address.setValue(value);
		
		this.in = in;
	}
	

	
	@Override
	public Object visit(Program obj, Object params) {
		cg.source(in);
		
		for(Definition def : obj.getStatements())
			if(def instanceof VarDefinition)
				def.accept(this, params);
		
		cg.callMain();
		cg.halt();
		
		for(Definition def : obj.getStatements())
			if(def instanceof FuncDefinition)
				def.accept(this, params);
		
		return null;
	}
	
	
	
	@Override
	public Object visit(FuncDefinition obj, Object params) {
		cg.line(obj.getLine());
		cg.etiqueta(obj.getName());
		
		cg.comment("--Parametros--");
		for(Statement def : ((FunctionType) obj.getType()).getParameters())
				def.accept(this, params);
		
		cg.comment("--Variables Locales--");
		for(Statement def : obj.getSentencias())
			if(def instanceof VarDefinition)
				def.accept(this, params);
		
		cg.enter(obj.getNumberOfBytesLocalVariables());
		
		for(Statement def : obj.getSentencias())
			if(!(def instanceof VarDefinition))
				def.accept(this, obj);
		
		FunctionType functionType = ((FunctionType)obj.getType());
		if(functionType.getReturnType() instanceof VoidType)
			cg.ret(0,obj.getNumberOfBytesLocalVariables(), functionType.getNumberOfBytesParams());
		
		return null;
	}
	
	
	
	@Override
	public Object visit(VarDefinition obj, Object params) {
		String className = obj.getType().toComment();
		cg.comment(String.format("%s %s (offset %d)", className, obj.getName(),obj.getOffset()));
		
		return null;
	}
	
	@Override
	public Object visit(Write obj, Object params) {
		cg.line(obj.getLine());
		
		obj.getExpression().accept(value, params);	
		
		cg.out(obj.getExpression().getType());
		
		return null;
	}
	
	@Override
	public Object visit(Read obj, Object params) {
		cg.line(obj.getLine());
		
		obj.getExpression().accept(address, params);
		
		cg.in(obj.getExpression().getType());
		cg.store(obj.getExpression().getType());
		
		return null;
	}
	
	@Override
	public Object visit(Assignment obj, Object params) {
		cg.line(obj.getLine());
		
		obj.getLeft().accept(address, params);
		obj.getRight().accept(value, params);
		
		cg.convert(obj.getRight().getType(), obj.getLeft().getType());
		cg.store(obj.getLeft().getType());
		
		return null;
	}
	
	@Override
	public Object visit(While obj, Object params) {
		cg.line(obj.getLine());
		
		int cont = cg.getLabels(2);		
		cg.etiqueta("principioWhile" + cont);
		
		obj.getCondition().accept(value, params);
		cg.convert(obj.getCondition().getType(), IntType.getInstancia());
		
		cg.jz("finWhile" + (cont + 1));
		
		for(Statement st : obj.getBody())
			st.accept(this, params);
		
		cg.jmp("principioWhile" + cont);
		cg.etiqueta("finWhile" + (cont + 1));
		
		return null;
	}
	
	@Override
	public Object visit(IfStatement obj, Object params) {
		cg.line(obj.getLine());
		
		int cont = cg.getLabels(2);
		boolean ifTerminable = obj.getIfBody().get(obj.getIfBody().size()-1).getTerminable();
		
		obj.getCondition().accept(value, params);
		cg.convert(obj.getCondition().getType(), IntType.getInstancia());
		
		cg.jz("elseBody" + cont);
		
		for(Statement st : obj.getIfBody())
			st.accept(this, params);
		
		if(!ifTerminable)
			cg.jmp("finIf" + (cont + 1));
		
		cg.etiqueta("elseBody" + cont);
		
		for(Statement st : obj.getElseBody())
			st.accept(this, params);
		
		if(!ifTerminable)
			cg.etiqueta("finIf" + (cont + 1));
		
		return null;
	}
	
	@Override
	public Object visit(Invocation obj, Object params) {
		cg.line(obj.getLine());
		
		obj.accept(value, params);
		Type returnType = ((FunctionType) obj.getFunction().getType()).getReturnType();
		
		if(!(returnType instanceof VoidType))
			cg.pop(returnType.getSuffix());

		return null;
	}
	
	@Override
	public Object visit(Return obj, Object params) {
		cg.line(obj.getLine());
		
		FuncDefinition function = (FuncDefinition) params;
		FunctionType functionType = ((FunctionType) function.getType());
		
		obj.getRetorno().accept(value, params);
		cg.convert(obj.getRetorno().getType(), functionType.getReturnType());
		
		int returnBytes = functionType.getReturnType().getNumberOfBytes();
		int localsBytes = function.getNumberOfBytesLocalVariables();
		int paramsBytes = functionType.getNumberOfBytesParams();
		
		cg.ret(returnBytes, localsBytes, paramsBytes);
		
		return null;
	}

}
