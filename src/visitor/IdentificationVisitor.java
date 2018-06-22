package visitor;


import ast.definitions.Definition;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.Variable;
import ast.sentencias.Statement;
import ast.type.ErrorType;
import ast.type.Type;
import symboltable.SymbolTable;

public class IdentificationVisitor extends AbstractVisitor {

	private SymbolTable symbolTabl = new SymbolTable();
	
	@Override
	public Object visit(FuncDefinition obj, Object params) {
		symbolTabl.set();
		obj.getType().accept(this, params);
		for(Statement st : obj.getSentencias())
			st.accept(this, params);
		symbolTabl.reset();
		
		if(!symbolTabl.insert(obj))
			new ErrorType(obj, String.format("La función %s ya está definida.", obj.getName()));
		
		return null;
	}


	@Override
	protected void visitVarDefinitionBody(VarDefinition obj, Object params) {
		if(!symbolTabl.insert(obj))
			new ErrorType(obj, String.format("La variable %s ya está definida.", obj.getName()));
	}

	@Override
	protected void visitVariableBody(Variable obj, Object params) {
		Definition def = symbolTabl.find(obj.getName());
		if(def != null)
			obj.setDefinicion(def);
		else {
			Type error = new ErrorType(obj, String.format("Símbolo %s no definido.", obj.getName()));
			VarDefinition defError = new VarDefinition(obj.getLine(),obj.getColumn(),obj.getName(),error);
			obj.setDefinicion(defError);
		}
	}
}
