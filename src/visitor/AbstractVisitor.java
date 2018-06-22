package visitor;

import ast.definitions.Definition;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
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
import ast.main.Program;
import ast.sentencias.Assignment;
import ast.sentencias.IfStatement;
import ast.sentencias.Invocation;
import ast.sentencias.Read;
import ast.sentencias.Return;
import ast.sentencias.Statement;
import ast.sentencias.While;
import ast.sentencias.Write;
import ast.type.ArrayType;
import ast.type.CharType;
import ast.type.ErrorType;
import ast.type.FunctionType;
import ast.type.IntType;
import ast.type.RealType;
import ast.type.RecordField;
import ast.type.RecordType;
import ast.type.VoidType;

public abstract class AbstractVisitor implements Visitor{

	@Override
	public Object visit(Program obj, Object params) {
		for(Definition def : obj.getStatements())
			def.accept(this, params);
		
		visitProgramBody(obj,params);
		
		return null;
	}	

	@Override
	public Object visit(FuncDefinition obj, Object params) {
		obj.getType().accept(this, params);
		for(Statement st : obj.getSentencias())
			st.accept(this, params);
		
		visitFuncDefinitionBody(obj,params);
		
		return null;
	}

	

	@Override
	public Object visit(VarDefinition obj, Object params) {
		obj.getType().accept(this, params);
		
		visitVarDefinitionBody(obj,params);
		
		return null;
	}

	

	@Override
	public Object visit(Arithmetic obj, Object params) {
		obj.getLeft().accept(this, params);
		obj.getRight().accept(this, params);
		
		visitArithmeticBody(obj,params);
		
		return null;
	}

	

	@Override
	public Object visit(Cast obj, Object params) {
		obj.getCastType().accept(this, params);
		obj.getRight().accept(this, params);
		
		visitCastBody(obj,params);
		
		return null;
	}
	
	

	@Override
	public Object visit(CharLiteral obj, Object params) {
		
		visitCharLiteralBody(obj,params);
		
		return null;
	}

	

	@Override
	public Object visit(Comparision obj, Object params) {
		obj.getLeft().accept(this, params);
		obj.getRight().accept(this, params);
		
		visitComparisionBody(obj,params);
		
		return null;
	}

	

	@Override
	public Object visit(FieldAccess obj, Object params) {
		obj.getLeft().accept(this, params);
		
		visitFieldAccessBody(obj,params);
		
		return null;
	}

	

	@Override
	public Object visit(Indexing obj, Object params) {
		obj.getLeft().accept(this, params);
		obj.getIndice().accept(this, params);
		
		visitIndexingBody(obj,params);
		
		return null;
	}
	

	@Override
	public Object visit(IntLiteral obj, Object params) {
		visitIntLiteralBody(obj,params);
		
		return null;
	}

	

	@Override
	public Object visit(Logical obj, Object params) {
		obj.getLeft().accept(this, params);
		obj.getRight().accept(this, params);

		visitLogicalBody(obj,params);
		
		return null;
	}
	

	@Override
	public Object visit(RealLiteral obj, Object params) {
		visitRealLiteralBody(obj,params);
		
		return null;
	}
	

	@Override
	public Object visit(UnaryMinus obj, Object params) {
		obj.getValue().accept(this, params);
		
		visitUnaryMinusBody(obj,params);
		
		return null;
	}
	

	@Override
	public Object visit(UnaryNot obj, Object params) {
		obj.getValue().accept(this, params);
		
		visitUnaryNotBody(obj,params);
		
		return null;
	}
	

	@Override
	public Object visit(Variable obj, Object params) {		
		visitVariableBody(obj,params);
		
		return null;
	}
	

	@Override
	public Object visit(Assignment obj, Object params) {
		obj.getLeft().accept(this, params);
		obj.getRight().accept(this, params);
		
		visitAssignmentBody(obj,params);
		
		return null;
	}

	

	@Override
	public Object visit(IfStatement obj, Object params) {
		obj.getCondition().accept(this, params);
		for(Statement st : obj.getIfBody())
			st.accept(this, params);
		for(Statement st : obj.getElseBody())
			st.accept(this, params);
		
		visitIfStatementBody(obj,params);
		
		return null;
	}

	

	@Override
	public Object visit(Invocation obj, Object params) {
		obj.getFunction().accept(this, params);
		for(Expression ex : obj.getArguments())
			ex.accept(this, params);
		
		visitInvocationBody(obj,params);
		
		return null;
	}
	

	@Override
	public Object visit(Read obj, Object params) {
		obj.getExpression().accept(this, params);
		
		visitReadBody(obj,params);
		
		return null;
	}
	

	@Override
	public Object visit(Return obj, Object params) {
		obj.getRetorno().accept(this, params);
		
		visitReturnBody(obj,params);
		
		return null;
	}
	

	@Override
	public Object visit(While obj, Object params) {
		obj.getCondition().accept(this, params);
		for(Statement st : obj.getBody())
			st.accept(this, params);
		
		visitWhileBody(obj,params);
		
		return null;
	}
	

	@Override
	public Object visit(Write obj, Object params) {
		obj.getExpression().accept(this, params);
		
		visitWriteBody(obj,params);
		
		return null;
	}

	

	@Override
	public Object visit(ArrayType obj, Object params) {
		obj.getType().accept(this, params);
		
		visitArrayTypeBody(obj,params);
		
		return null;
	}
	

	@Override
	public Object visit(CharType obj, Object params) {
		visitCharTypeBody(obj,params);
		
		return null;
	}
	

	@Override
	public Object visit(FunctionType obj, Object params) {
		for(VarDefinition var : obj.getParameters())
			var.accept(this, params);
		obj.getReturnType().accept(this, params);
		
		visitFunctionTypeBody(obj,params);
		
		return null;
	}
	

	@Override
	public Object visit(IntType obj, Object params) {
		visitIntTypeBody(obj,params);
		
		return null;
	}
	

	@Override
	public Object visit(RealType obj, Object params) {
		visitRealTypeBody(obj,params);
		
		return null;
	}

	

	@Override
	public Object visit(RecordField obj, Object params) {
		obj.getType().accept(this, params);

		visitRecordFieldBody(obj,params);
		
		return null;
	}
	

	@Override
	public Object visit(RecordType obj, Object params) {
		for(RecordField rf : obj.getCampos())
			rf.accept(this, params);
		
		visitRecordTypeBody(obj,params);
		
		return null;
	}
	

	@Override
	public Object visit(VoidType obj, Object params) {
		visitVoidTypeBody(obj,params);
		
		return null;
	}
	

	@Override
	public Object visit(ErrorType obj, Object params) {
		visitErrorTypeBody(obj,params);
		
		return null;
	}
	
	//Programa
	protected void visitProgramBody(Program obj, Object params) {}
	
	
	//Definiciones
	protected void visitFuncDefinitionBody(FuncDefinition obj, Object params) {}	
	protected void visitVarDefinitionBody(VarDefinition obj, Object params) {}
	
	
	//Expresiones
	protected void visitArithmeticBody(Arithmetic obj, Object params) {}	
	protected void visitCastBody(Cast obj, Object params) {}	
	protected void visitCharLiteralBody(CharLiteral obj, Object params) {}	
	protected void visitIntLiteralBody(IntLiteral obj, Object params) {}
	protected void visitLogicalBody(Logical obj, Object params) {}
	protected void visitRealLiteralBody(RealLiteral obj, Object params) {}	
	protected void visitComparisionBody(Comparision obj, Object params) {}
	protected void visitFieldAccessBody(FieldAccess obj, Object params) {}
	protected void visitIndexingBody(Indexing obj, Object params) {}
	protected void visitUnaryMinusBody(UnaryMinus obj, Object params) {}
	protected void visitUnaryNotBody(UnaryNot obj, Object params) {}
	protected void visitVariableBody(Variable obj, Object params) {}
	
	
	protected void visitInvocationBody(Invocation obj, Object params) {} 
	//Sentencias	
	protected void visitAssignmentBody(Assignment obj, Object params) {}
	protected void visitReadBody(Read obj, Object params) {}
	protected void visitWriteBody(Write obj, Object params) {}
	protected void visitIfStatementBody(IfStatement obj, Object params) {}	
	protected void visitReturnBody(Return obj, Object params) {}
	protected void visitWhileBody(While obj, Object params) {}
	
	
	//Tipos
	protected void visitArrayTypeBody(ArrayType obj, Object params) {}
	protected void visitCharTypeBody(CharType obj, Object params) {}
	protected void visitFunctionTypeBody(FunctionType obj, Object params) {}
	protected void visitIntTypeBody(IntType obj, Object params) {}
	protected void visitRealTypeBody(RealType obj, Object params) {}
	protected void visitRecordFieldBody(RecordField obj, Object params) {}
	protected void visitRecordTypeBody(RecordType obj, Object params) {}
	protected void visitVoidTypeBody(VoidType obj, Object params) {}	
	protected void visitErrorTypeBody(ErrorType obj, Object params) {}

}
