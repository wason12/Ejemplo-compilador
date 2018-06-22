package codeGenerator;

import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.type.FunctionType;
import ast.type.RecordField;
import ast.type.RecordType;
import visitor.AbstractVisitor;

public class OffSetVisitor extends AbstractVisitor {
	
	int numeroTotalBytesGlobales = 0;
	int numeroTotalBytesLocales = 0;
	

	protected void visitVarDefinitionBody(VarDefinition obj, Object params) {
		if(obj.getScope() == 0) {
			obj.setOffset(numeroTotalBytesGlobales);
			numeroTotalBytesGlobales += obj.getType().getNumberOfBytes();
		}else {
			numeroTotalBytesLocales -= obj.getType().getNumberOfBytes();
			obj.setOffset(numeroTotalBytesLocales);
		}
	}
	
	@Override
	protected void visitFuncDefinitionBody(FuncDefinition obj, Object params) {
		obj.setNumberOfBytesLocalVariables(numeroTotalBytesLocales * -1);
		numeroTotalBytesLocales = 0;
	}
	
	@Override
	public Object visit(FunctionType obj, Object params) {
		int numeroTotalBytesParametros = 4;
		
		for(int i = obj.getParameters().size()-1 ; i >= 0 ; i--) {
			obj.getParameters().get(i).setOffset(numeroTotalBytesParametros);
			numeroTotalBytesParametros += obj.getParameters().get(i).getType().getNumberOfBytes();
		}
		
		obj.setNumberOfBytesParams(numeroTotalBytesParametros - 4);
		
		return null;
	}
	
	@Override
	protected void visitRecordTypeBody(RecordType obj, Object params) {
		int numeroTotalBytesCampos = 0;
		
		for(RecordField rf : obj.getCampos()) {
			rf.setOffset(numeroTotalBytesCampos);
			numeroTotalBytesCampos += rf.getType().getNumberOfBytes();
		}
	}
	
}
