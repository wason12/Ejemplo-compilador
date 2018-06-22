package visitor;

import ast.main.Program;
import ast.expressions.*;
import ast.sentencias.*;
import ast.type.*;
import ast.definitions.*;

public interface Visitor {
	public Object visit(Program obj, Object params);

	public Object visit(FuncDefinition obj, Object params);

	public Object visit(VarDefinition obj, Object params);

	public Object visit(Arithmetic obj, Object params);

	public Object visit(Cast obj, Object params);

	public Object visit(CharLiteral obj, Object params);

	public Object visit(Comparision obj, Object params);

	public Object visit(FieldAccess obj, Object params);

	public Object visit(Indexing obj, Object params);

	public Object visit(IntLiteral obj, Object params);

	public Object visit(Logical obj, Object params);

	public Object visit(RealLiteral obj, Object params);

	public Object visit(UnaryMinus obj, Object params);

	public Object visit(UnaryNot obj, Object params);

	public Object visit(Variable obj, Object params);

	public Object visit(Assignment obj, Object params);

	public Object visit(IfStatement obj, Object params);

	public Object visit(Invocation obj, Object params);

	public Object visit(Read obj, Object params);

	public Object visit(Return obj, Object params);

	public Object visit(While obj, Object params);

	public Object visit(Write obj, Object params);

	public Object visit(ArrayType obj, Object params);

	public Object visit(CharType obj, Object params);

	public Object visit(FunctionType obj, Object params);

	public Object visit(IntType obj, Object params);

	public Object visit(RealType obj, Object params);

	public Object visit(RecordField obj, Object params);

	public Object visit(RecordType obj, Object params);

	public Object visit(VoidType obj, Object params);

	public Object visit(ErrorType obj, Object params);
}
