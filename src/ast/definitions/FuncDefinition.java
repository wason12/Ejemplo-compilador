package ast.definitions;


import java.util.List;

import ast.type.Type;
import visitor.Visitor;
import ast.main.ASTNodeDefault;
import ast.sentencias.Statement;

public class FuncDefinition extends ASTNodeDefault implements Definition {

	private List<Statement> sentencias;
	private String name;
	private Type type;
	private int numberOfBytesLocalVariables;

	public FuncDefinition(int line, int column, String name, Type type, List<Statement> sentencias) {
		super(line, column);
		this.sentencias = sentencias;
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<Statement> getSentencias() {
		return sentencias;
	}

	public void setSentencias(List<Statement> sentencias) {
		this.sentencias = sentencias;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("FUNCTION " + name + " " + type.toString() + "{");
		for(Statement sentencia : sentencias) {
			str.append("\n\t");
			str.append(sentencia.toString());
		}
		
		str.append("\n}");
		
		
		return str.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FuncDefinition other = (FuncDefinition) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}

	public int getNumberOfBytesLocalVariables() {
		return numberOfBytesLocalVariables;
	}
	
	public void setNumberOfBytesLocalVariables(int i) {
		numberOfBytesLocalVariables = i;
	}

}
