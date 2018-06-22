package ast;

import introspector.view.IntrospectorTree;

import java.util.ArrayList;
import java.util.List;

import ast.expressions.*;
import ast.main.ASTNode;
import ast.sentencias.*;
import introspector.model.IntrospectorModel;

public class ASTTest {

	/**
	 * The input program is:
	 * 
	 * input x,y; z = (-x + 5) / y * (2 � x); print z;
	 */
	private static ASTNode createAST() {
		List<Statement> statements = new ArrayList<Statement>();

		// * First line
		statements.add(new Read(1, 7, new Variable(1, 7, "x")));
		statements.add(new Read(1, 9, new Variable(1, 9, "y")));

		// * Second line
		Statement statement = new Assignment(2, 3, new Variable(2, 1, "z"),
				new Arithmetic(2, 18,
						new Arithmetic(2, 18,
								new Arithmetic(2, 18, new UnaryMinus(2, 19, new Variable(2, 20, "x")), "+",
										new IntLiteral(2, 24, 5)),
								"/", new Variable(2, 29, "y")),
						"*", new Arithmetic(2, 20, new IntLiteral(2, 21, 2), "-", new Variable(2, 25, "x"))));
		statements.add(statement);

		// * Third line
		statements.add(new Write(3, 1, new Variable(3, 7, "z")));

		// * We build and return the AST
		//return new Program(1, 1, statements);
		return null;

	}

	public static void main(String[] args) {
		IntrospectorModel modelo = new IntrospectorModel("Program", createAST());
		new IntrospectorTree("Introspector", modelo);
	}
}
