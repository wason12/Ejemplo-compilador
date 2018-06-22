package visitor;

import java.util.ArrayList;
import java.util.List;

import ast.definitions.FuncDefinition;
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
import ast.sentencias.Assignment;
import ast.sentencias.IfStatement;
import ast.sentencias.Invocation;
import ast.sentencias.Read;
import ast.sentencias.Return;
import ast.sentencias.Statement;
import ast.sentencias.While;
import ast.sentencias.Write;
import ast.type.CharType;
import ast.type.ErrorType;
import ast.type.FunctionType;
import ast.type.IntType;
import ast.type.RealType;
import ast.type.Type;
import ast.type.VoidType;

public class TypeCheckingVisitor extends AbstractVisitor {

	@Override
	protected void visitArithmeticBody(Arithmetic obj, Object params) {
		obj.setLValue(false);

		obj.setType(obj.getLeft().getType().arithmetic(obj.getRight().getType()));
		if (obj.getType() == null) {
			Type error = new ErrorType(obj,
					String.format(" La operacion %s no está definida para los tipos %s y %s", obj.getOperation(),
							obj.getLeft().getType().getClass().getSimpleName(),
							obj.getRight().getType().getClass().getSimpleName()));
			obj.setType(error);
		}
	}

	@Override
	protected void visitCastBody(Cast obj, Object params) {
		obj.setLValue(obj.getRight().getLValue());

		obj.setType(obj.getRight().getType().canBeCastTo(obj.getCastType()));
		if (obj.getType() == null) {
			Type error = new ErrorType(obj, String.format("El tipo %s no puede ser casteado al tipo %s.",
					obj.getRight().getType().getClass().getSimpleName(), obj.getCastType().toString()));
			obj.setType(error);
		}
	}

	@Override
	protected void visitCharLiteralBody(CharLiteral obj, Object params) {
		obj.setLValue(false);

		obj.setType(CharType.getInstancia());
	}

	@Override
	protected void visitIntLiteralBody(IntLiteral obj, Object params) {
		obj.setLValue(false);

		obj.setType(IntType.getInstancia());
	}

	@Override
	protected void visitLogicalBody(Logical obj, Object params) {
		obj.setLValue(false);

		obj.setType(obj.getLeft().getType().logical(obj.getRight().getType()));
		if (obj.getType() == null) {
			Type error = new ErrorType(obj,
					String.format("La operacion %s no está definida para los tipos %s y %s.", obj.getOperation(),
							obj.getLeft().getType().getClass().getSimpleName(),
							obj.getRight().getType().getClass().getSimpleName()));
			obj.setType(error);
		}
	}

	@Override
	protected void visitRealLiteralBody(RealLiteral obj, Object params) {
		obj.setLValue(false);

		obj.setType(RealType.getInstancia());
	}

	@Override
	protected void visitComparisionBody(Comparision obj, Object params) {
		obj.setLValue(false);

		obj.setType(obj.getLeft().getType().comparision(obj.getRight().getType()));
		if (obj.getType() == null) {
			Type error = new ErrorType(obj,
					String.format("La operacion %s no está definida para los tipos %s y %s.", obj.getOperation(),
							obj.getLeft().getType().getClass().getSimpleName(),
							obj.getRight().getType().getClass().getSimpleName()));
			obj.setType(error);
		}
	}

	@Override
	protected void visitFieldAccessBody(FieldAccess obj, Object params) {
		obj.setLValue(true);

		obj.setType(obj.getLeft().getType().dot(obj.getName()));
		if (obj.getType() == null) {
			Type error = new ErrorType(obj,
					String.format("El campo %s no está definido en %s", obj.getName(), obj.getLeft()));
			obj.setType(error);
		}
	}

	@Override
	protected void visitIndexingBody(Indexing obj, Object params) {
		obj.setLValue(true);

		obj.setType(obj.getLeft().getType().squareBrackets(obj.getIndice().getType()));
		if (obj.getType() == null) {
			Type error = new ErrorType(obj,
					String.format("[%s] no válido sobre %s.", obj.getIndice(), obj.getLeft().getType()));
			obj.setType(error);
		}
	}

	@Override
	protected void visitUnaryMinusBody(UnaryMinus obj, Object params) {
		obj.setLValue(false);

		obj.setType(obj.getValue().getType().arithmetic());
		if (obj.getType() == null) {
			Type error = new ErrorType(obj, String.format("La operación '-' no está definida para %s.",
					obj.getValue().getType().getClass().getSimpleName()));
			obj.setType(error);
		}
	}

	@Override
	protected void visitUnaryNotBody(UnaryNot obj, Object params) {
		obj.setLValue(false);

		obj.setType(obj.getValue().getType().logical());
		if (obj.getType() == null) {
			Type error = new ErrorType(obj, String.format("La operación '!' no está definida para %s.",
					obj.getValue().getType().getClass().getSimpleName()));
			obj.setType(error);
		}
	}

	@Override
	protected void visitVariableBody(Variable obj, Object params) {
		obj.setLValue(true);

		obj.setType(obj.getDefinicion().getType());
	}

	@Override
	protected void visitInvocationBody(Invocation obj, Object params) {
		obj.setLValue(false);

		List<Type> lista = new ArrayList<Type>();
		obj.getArguments().forEach(arg -> lista.add(arg.getType()));
		obj.setType(obj.getFunction().getType().parentesis(lista));
		if (obj.getType() == null) {
			Type error = new ErrorType(obj,
					String.format("Los argumentos de %s no encajan en los parametros %s%s.", obj.toString(),
							obj.getFunction().getDefinicion().getName(),
							obj.getFunction().getDefinicion().getType().toString()));
			obj.setType(error);
		}

		obj.setTerminable(false);
	}

	@Override
	protected void visitAssignmentBody(Assignment obj, Object params) {
		if (!obj.getLeft().getLValue())
			new ErrorType(obj.getLeft(), "La expresión de la izquierda debe ser LValuable.");

		if (obj.getRight().getType().promoteTo(obj.getLeft().getType()) == null)
			new ErrorType(obj.getLeft(),
					String.format("El tipo %s no promociona al tipo %s.",
							obj.getRight().getType().getClass().getSimpleName(),
							obj.getLeft().getType().getClass().getSimpleName()));

		obj.setTerminable(false);

	}

	@Override
	protected void visitReadBody(Read obj, Object params) {
		if (!obj.getExpression().getLValue())
			new ErrorType(obj.getExpression(), "La expresión de la derecha debe ser LValuable.");

		obj.setTerminable(false);

	}

	@Override
	protected void visitWhileBody(While obj, Object params) {
		if (!obj.getCondition().getType().isLogical())
			new ErrorType(obj.getCondition(), String.format("El tipo %s de la condición no es lógico.",
					obj.getCondition().getType().getClass().getSimpleName()));

		isATerminableList(obj.getBody()); //Para detectar código inalcanzable
		obj.setTerminable(false);
	}

	@Override
	protected void visitIfStatementBody(IfStatement obj, Object params) {
		if (!obj.getCondition().getType().isLogical())
			new ErrorType(obj.getCondition(), String.format("El tipo %s de la condición no es lógico.",
					obj.getCondition().getType().getClass().getSimpleName()));

		boolean ifTerminable = isATerminableList(obj.getIfBody());
		boolean elseTerminable = isATerminableList(obj.getElseBody());

		obj.setTerminable(ifTerminable && elseTerminable);
	}

	@Override
	protected void visitReturnBody(Return obj, Object params) {
		Type tipoRetorno = ((FunctionType) ((FuncDefinition) params).getType()).getReturnType();

		if (obj.getRetorno().getType().promoteTo(tipoRetorno) == null) {
			new ErrorType(obj, String.format("El tipo %s no coincide con el tipo de retorno %s.",
					obj.getRetorno().getType().getClass().getSimpleName(), tipoRetorno.getClass().getSimpleName()));
		}

		obj.setTerminable(true);
	}

	@Override
	protected void visitWriteBody(Write obj, Object params) {
		if (!obj.getExpression().getType().printable()) {
			new ErrorType(obj, String.format("La operación print no está definida para %s.",
					obj.getExpression().getType().getClass().getSimpleName()));
		}

		obj.setTerminable(false);
	}

	@Override
	public Object visit(FuncDefinition obj, Object params) {
		obj.getType().accept(this, params);
		for (Statement st : obj.getSentencias())
			st.accept(this, obj);

		Type returnType = ((FunctionType) obj.getType()).getReturnType();

		// Si la función retorna un valor, comprobamos que tenga un return y no haya
		// código muerto. Comprobamos que la última sentencia y sólo la última sea "terminable"
		if ( !(returnType instanceof VoidType) ) 
			if ( !isATerminableList(obj.getSentencias()) )
				new ErrorType(obj,
						String.format("La función debe devolver un %s.", returnType.getClass().getSimpleName()));

		return null;
	}

	
	
	
	
	private boolean isATerminableList(List<Statement> lista) {
		int tam = lista.size();

		for (int i = 0; i < tam; i++) {
			Statement sentenciaActual = lista.get(i);
			// Para detectar código inalcanzable
			if (i < tam - 1) {
				if (sentenciaActual.getTerminable())
					new ErrorType(lista.get(i + 1), "Código inalcanzable.");
			} else
				return sentenciaActual.getTerminable();
		}

		return false;
	}

	
//	Estaba pensando que en el caso del if y while que tuviesen como condición una constante verdadera
//	y la última función fuesen terminables, marcarlas como terminables. Pero no he llegado a terminar de implementarlo
//	por lo que no considero los whiles terminables ni los if sin else.
//	private boolean isCondicionConstanteYVerdadera(Expression condition) {
//		if (condition instanceof IntLiteral && ((IntLiteral) condition).getValue() != 0)
//			return true;
//		if (condition instanceof CharLiteral && ((CharLiteral) condition).getValue() != 0)
//			return true;
//
//		return false;
//	}
}
