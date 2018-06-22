package codeGenerator;

import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.Arithmetic;
import ast.expressions.Cast;
import ast.expressions.CharLiteral;
import ast.expressions.Comparision;
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
import visitor.Visitor;

public abstract class AbstractCGVisitor implements Visitor{
	public Object visit(Program obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(FuncDefinition obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(VarDefinition obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(Arithmetic obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(Cast obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(CharLiteral obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(Comparision obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(FieldAccess obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(Indexing obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(IntLiteral obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(Logical obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(RealLiteral obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(UnaryMinus obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(UnaryNot obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(Variable obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(Assignment obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(IfStatement obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(Invocation obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(Read obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(Return obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(While obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(Write obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(ArrayType obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(CharType obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(FunctionType obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(IntType obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(RealType obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(RecordField obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(RecordType obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(VoidType obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

	public Object visit(ErrorType obj, Object params) {
		throw new IllegalStateException("Plantilla no especificada.");
	}

}
