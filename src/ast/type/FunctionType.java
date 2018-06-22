package ast.type;

import java.util.List;

import ast.definitions.VarDefinition;
import visitor.Visitor;

public class FunctionType extends AbstractType{

	private List<VarDefinition> parameters;
	private Type returnType;
	private int numberOfBytesParams;

	public FunctionType(int line, int column, List<VarDefinition> parameters, Type returnType) {
		super(line, column);
		this.parameters = parameters;
		this.returnType = returnType;
	}

	public List<VarDefinition> getParameters() {
		return parameters;
	}

	public void setParameters(List<VarDefinition> parameters) {
		this.parameters = parameters;
	}

	public Type getReturnType() {
		return returnType;
	}

	public void setReturnType(Type returnType) {
		this.returnType = returnType;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append('(');
		int tam = parameters.size();
		for(int i=0 ; i<tam ; i++) {
			str.append(parameters.get(i).toString());
			
			if(i<tam-1)
				str.append(',');
		}
		str.append(") : " + returnType.toString());
		
		return str.toString();
	}
	
	@Override
	public Object accept(Visitor v, Object params) {
		return v.visit(this, params);
	}
	
	@Override
	public Type parentesis(List<Type> lista) {
		if(lista.size() == parameters.size()) {
			for(int i=0 ; i<parameters.size() ; i++) {
				if(lista.get(i).promoteTo(parameters.get(i).getType()) == null)
					return null;
			}
			return returnType;
		}
		
		return null;
	}
	

	public int getNumberOfBytesParams() {
		return numberOfBytesParams;
	}
	
	public void setNumberOfBytesParams(int i) {
		numberOfBytesParams = i;
	}

}
