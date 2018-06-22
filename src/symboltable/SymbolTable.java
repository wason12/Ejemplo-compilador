package symboltable;

import java.util.*;
import ast.definitions.*;

public class SymbolTable {
	
	private int scope=0;
	private List<Map<String,Definition>> table;
	
	public SymbolTable()  {
		table = new ArrayList<Map<String,Definition>>();
		table.add(new HashMap<String,Definition>());
	}

	public void set() {
		table.add(new HashMap<String,Definition>());
		scope++;
	}
	
	public void reset() {
		table.remove(scope);
		scope--;
	}
	
	public boolean insert(Definition definition) {
		if(definition instanceof VarDefinition)
			((VarDefinition)definition).setScope(scope);
		
		if(table.get(scope).get(definition.getName()) == null) {
			table.get(scope).put(definition.getName(), definition);
			return true;
		}
		
		return false;
	}
	
	public Definition find(String id) {
		for(int i=scope ; i>=0 ; i--) {
			Map<String, Definition> ambito = table.get(i);
			Definition def = ambito.get(id);
			if(def != null)
				return def;
		}
		return null;
	}

	public Definition findInCurrentScope(String id) {
		return table.get(scope).get(id);
	}
}
