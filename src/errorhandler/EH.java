package errorhandler;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import ast.type.ErrorType;

public class EH {
	
	private List<ErrorType> errores;
	private static EH instancia = new EH();
	
	private EH() {
		errores = new ArrayList<ErrorType>();
	}
	
	public static EH getInstancia() {
		return instancia;
	}	
	
	public boolean hasErrors() {
		return !errores.isEmpty();
	}
	
	public void addError(ErrorType error) {
		errores.add(error);
	}
	
	public void showErrors(PrintStream print) {
		for(ErrorType error : errores)
			print.println(error.getMessage());
		print.close();
	}
	
	public static EH getEH() {
		return getInstancia();
	}

}
