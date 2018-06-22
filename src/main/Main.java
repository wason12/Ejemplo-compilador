package main;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import ast.main.Program;
import codeGenerator.CodeGenerator;
import codeGenerator.ExecutorVisitor;
import codeGenerator.OffSetVisitor;
import errorhandler.EH;
import scanner.Scanner;
import visitor.IdentificationVisitor;
import visitor.TypeCheckingVisitor;
import visitor.Visitor;
import parser.Parser;

public class Main {

	public static void main(String args[]) throws IOException {
		if (args.length < 1) {
			System.err.println("Pass me the name of the input file.");
			return;
		}

		FileReader fr = null;
		try {
			fr = new FileReader(args[0]);
		} catch (IOException io) {
			System.err.println("The file " + args[0] + " could not be opened.");
			return;
		}

		Scanner scanner = new Scanner(fr);
		Parser parser = new Parser(scanner);
		parser.run();
		
		Visitor IdentificationVisitor = new IdentificationVisitor();
		Visitor typeVisitor = new TypeCheckingVisitor();
		
		Program programa = (Program) parser.getAST();
		

		if(programa != null) {
			IdentificationVisitor.visit(programa, null);
			typeVisitor.visit(programa, null);
			EH.getEH().showErrors(System.out);

			if(!EH.getEH().hasErrors()) {
				
				programa.accept(new OffSetVisitor(), null);
				
//				IntrospectorModel model = new IntrospectorModel("Program", parser.getAST());
//				new IntrospectorTree("Introspector", model);
				
				CodeGenerator codeGenerator = new CodeGenerator(new FileWriter("output.txt"));
				Visitor codeGeneratorVisitor = new ExecutorVisitor(args[0],codeGenerator);
				
				try{
					programa.accept(codeGeneratorVisitor, null);
				}catch(Exception e) {
					throw e;
				}finally {
					codeGenerator.finish();
				}
			}
		}
	}
}