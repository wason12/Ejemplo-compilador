package codeGenerator;

import java.io.FileWriter;
import java.io.IOException;

import ast.type.Type;

public class CodeGenerator {
	private FileWriter out;
	private int cont = 0;

	public CodeGenerator(FileWriter out) {
		super();
		this.out = out;
	}


	public void pushA(int address) {
		write("\tpusha " + address);
	}

	public void pushBP() {
		write("\tpush bp");
	}

	public void push(char c) {
		write("\tpushb " + (int) c);
	}

	public void push(int i) {
		write("\tpushi " + i);
	}

	public void push(double f) {
		write("\tpushf " + f);
	}

	public void load(char suffix) {
		write("\tload" + suffix);
	}

	public void write(String str) {
		try {
			out.write(str);
			out.write('\n');
		} catch (IOException e) {
			try {
				out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void halt() {
		write("halt");
	}
	
	public void callMain() {
		write("\n");
		comment("Llamando a la funcion main");
		write("call main");
	}

	public void call(String string) {
		write("\tcall " + string);
	}

	public void etiqueta(String name) {
		write(name + ":");
	}

	public void enter(int i) {
		write("\tenter " + i);
	}

	public void ret(int retorno, int numberOfBytesLocalVariables, int numberOfBytesParams) {
		write(String.format("\tret %d, %d, %d", retorno, numberOfBytesLocalVariables, numberOfBytesParams));
	}

	public void out(Type type) {
		write("\tout" + type.getSuffix());
	}

	public void in(Type type) {
		write("\tin" + type.getSuffix());
	}

	public void store(Type type) {
		write("\tstore" + type.getSuffix());
	}

	public void convert(Type promocionado, Type aPromocionar) {
		char tipoAPromocionar = aPromocionar.getSuffix();
		char tipoPromocionado = promocionado.getSuffix();

		if (tipoPromocionado != tipoAPromocionar) {
			if ((tipoPromocionado == 'b' || tipoPromocionado == 'f')
					&& (tipoAPromocionar == 'b' || tipoAPromocionar == 'f')) {
				write("\t" + tipoPromocionado + "2i");
				write("\ti2" + tipoAPromocionar);

			} else
				write("\t" + tipoPromocionado + "2" + tipoAPromocionar);
		}
	}

	public void arithmetic(Type type, String operation) {
		String op = "ERROR";
		
		switch (operation) {
			case "+":
				op = "add";
				break;
			case "-":
				op = "sub";
				break;
			case "*":
				op = "mul";
				break;
			case "/":
				op = "div";
				break;
			case "%":
				op = "mod";
				break;
		}
		
		write("\t" + op + type.getSuffix());
	}

	public void comparision(Type superType, String operation) {
		String op = "ERROR";
		
		switch (operation) {
			case ">":
				op = "gt";
				break;
			case "<":
				op = "lt";
				break;
			case ">=":
				op = "ge";
				break;
			case "<=":
				op = "le";
				break;
			case "==":
				op = "eq";
				break;
			case "!=":
				op = "ne";
				break;
		}
		
		write("\t" + op + superType.getSuffix());
	}

	public void source(String in) {
		write("#source \"" + in + "\"\n");
	}

	public void comment(String str) {
		write("\t \' * " + str);
	}
	
	public void line(int i) {
		write("\n#line " + i + "\n");
	}


	public void finish() {
		try {
			if(out != null) 
				out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public int getLabels(int i) {
		int aux = cont;
		cont += i;
		return aux;
	}


	public void jz(String string) {
		write("\tjz " + string);
	}


	public void jmp(String string) {
		write("\tjmp " + string);
	}
	
	public void jnz(String string) {
		write("\tjnz " + string);
	}


	public void pop(char suffix) {
		write("\tpop" + suffix);
	}


	public void add(char suffix) {
		write("\tadd" + suffix);
	}


	public void mul(char suffix) {
		write("\tmul" + suffix);
	}


	public void logical(String operation) {
		String op = "ERROR";
		
		switch (operation) {
			case "&&":
				op = "and";
				break;
			case "||":
				op = "or";
				break;
		}
		
		write("\t" + op);
	}

}
