package ast.main;

import java.util.List;

import ast.definitions.Definition;
import visitor.Visitor;

public class Program extends ASTNodeDefault {

	private List<Definition> definitions;

	public Program(int line, int column, List<Definition> definitions) {
		super(line, column);
		this.definitions = definitions;
	}

	public List<Definition> getStatements() {
		return definitions;
	}

	public void setStatements(List<Definition> definitions) {
		this.definitions = definitions;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		for (Definition st : definitions) {
			str.append(st.toString());
			str.append("\n");
		}

		return str.toString();
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
	
}
