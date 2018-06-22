%right '='
%left AND OR 
%left '>' '<' LESS_EQUALS GREATER_EQUALS EQUALS DISTINCT
%left '+' '-'
%left '*' '/' '%'
%nonassoc '!'
%nonassoc UNARY
%nonassoc CAST
%left '.'
%nonassoc '[' ']'
%nonassoc '(' ')'
%nonassoc MENOR_QUE_ELSE
%nonassoc ELSE
%{
// * Declaraciones de código Java
// * Se sitúan al comienzo del archivo generado
// * El package lo añade yacc si utilizamos la opción -Jpackage
import scanner.Scanner;
import java.io.Reader;
import ast.definitions.*;
import ast.expressions.*;
import ast.main.*;
import ast.sentencias.*;
import ast.type.*;
import java.util.List;
import java.util.ArrayList;
%}

// * Declaraciones Yacc
%token INT_CONSTANT
%token ID
%token REAL_CONSTANT
%token CHAR_CONSTANT
%token DEF
%token INT
%token DOUBLE
%token CHAR
%token MAIN
%token IF
%token ELSE
%token WRITE
%token READ
%token STRUCT
%token RETURN
%token LESS_EQUALS
%token GREATER_EQUALS
%token WHILE
%token VOID
%token EQUALS
%token DISTINCT
%token OR
%token AND
%%
// * Gramática y acciones Yacc

programa: definiciones defMain																	{ ((List<Definition>)$1).add((Definition)$2); AST = new Program(getLine(),getColumn(),(List<Definition>)$1); }
		| defMain																				{ List<Definition> aux = new ArrayList<Definition>(); aux.add((Definition)$1); AST = new Program(getLine(),getColumn(),aux); }
        ;

definiciones: definicion																		{ $$ = $1; }
	    	| definiciones definicion															{ $$ = $1; ((List<Definition>)$$).addAll((List<Definition>)$2); }
            ;

definicion: definicionVariable																	{ $$ = $1; }
          | definicionFuncion																	{ List<Definition> aux = new ArrayList<Definition>(); aux.add((Definition)$1); $$=aux; }
	  	  ;

definicionVariable: idsCS ':' tipo ';'															{ List<VarDefinition> aux = new ArrayList<VarDefinition>(); for(String st : (List<String>)$1){ aux.add(new VarDefinition(getLine(),getColumn(),st,(Type)$3)); } $$=aux; }
                  ;

idsCS: ID																						{ $$ = new ArrayList<String>(); ((List<String>)$$).add((String)$1); }
     | idsCS ',' ID																				{ $$ = $1; if(((List<String>)$$).contains((String)$3)) new ErrorType(getLine(),getColumn(),"ERROR: La variable " + (String)$3 + " ya está declarada."); ((List<String>)$$).add((String)$3); }
     ;

definicionFuncion: DEF ID { $$ = new int[]{getLine(), getColumn()}; } '(' parametrosCSopt ')' ':' tipoRetorno '{' cuerpoFuncion '}'					{ $$ = new FuncDefinition(((int[])$3)[0],((int[])$3)[1],(String)$2,new FunctionType(getLine(),getColumn(),(List<VarDefinition>)$5,(Type)$8),(List<Statement>)$10); }
		 		 ;

defMain: DEF MAIN { $$ = new int[]{getLine(), getColumn()}; } '(' ')' ':' VOID '{' cuerpoFuncion '}'												{ $$ = new FuncDefinition(((int[])$3)[0],((int[])$3)[1],"main",new FunctionType(getLine(),getColumn(),new ArrayList<VarDefinition>(),VoidType.getInstancia()),(List<Statement>)$9); }								
       ;

parametrosCSopt: 																				{ $$ = new ArrayList<VarDefinition>(); }																
	       	   | parametrosCS																	{ $$ = $1; }
	           ;

parametrosCS: parametro																			{ $$ = new ArrayList<VarDefinition>(); ((List<VarDefinition>)$$).add( (VarDefinition)$1 ); }
	    	| parametrosCS ',' parametro														{ $$ = $1; ((List<VarDefinition>)$$).add( (VarDefinition)$3 ); }
	    	;

parametro: ID ':' tipoSimple																	{ $$ = new VarDefinition(getLine(),getColumn(),(String)$1,(Type)$3); }
	 	 ;

tipoRetorno: VOID																				{ $$ = VoidType.getInstancia(); }
	   	   | tipoSimple																			{ $$ = $1; }
	       ;

tipoSimple: INT																					{ $$ = IntType.getInstancia();   }
	  	  | DOUBLE																				{ $$ = RealType.getInstancia();  }
   	  	  | CHAR																				{ $$ = CharType.getInstancia();  }
	  	  ;

tipo: tipoSimple																				{ $$ = $1; } 
    | structura																					{ $$ = $1; }
    | '[' INT_CONSTANT ']' tipo																	{ $$ = new ArrayType(getLine(),getColumn(),(int)$2,(Type)$4); }
    ;

structura: STRUCT '{' definicionesCamposOpt '}'													{ $$ = new RecordType(getLine(),getColumn(),(List<RecordField>)$3); }
         ;

definicionesCamposOpt: 																			{ $$ = new ArrayList<RecordType>(); }
		     		 | definicionesCampos														{ $$ = $1; }
		     		 ;

definicionesCampos: definicionCampo																{ $$ = $1; }
		  		  | definicionesCampos definicionCampo											{ $$ = $1; comprobarCamposRepetidos((List<RecordField>)$$,(List<RecordField>)$2); ((List<RecordField>)$$).addAll((List<RecordField>)$2); }
		  		  ;

definicionCampo: idsCS ':' tipo ';'																{ List<RecordField> aux = new ArrayList<RecordField>(); for(String st : (List<String>)$1){ aux.add(new RecordField(st,(Type)$3)); } $$=aux; }
	       	   ;

cuerpoFuncion: definicionesVariables  sentenciasOpt												{ $$ = $1; ((List<Statement>)$$).addAll((List<Statement>)$2); }
	     	 | sentenciasOpt																	{ $$ = $1; }
             ;

definicionesVariables: definicionVariable														{ $$ = $1; }
		     		 | definicionesVariables definicionVariable									{ $$ = $1; ((List<VarDefinition>)$$).addAll((List<VarDefinition>)$2); }
		     		 ;

sentenciasOpt:																					{ $$ = new ArrayList<Statement>(); }
	     	 | sentencias																		{ $$ = $1; }
	     	 ;

sentencias: sentencia																			{ $$ = $1; }
	  	  | sentencias sentencia																{ $$ = $1; ((List<Statement>)$$).addAll((List<Statement>)$2); }
	  	  ;

sentencia: WRITE expresionesCS ';'																{ List<Statement> aux = new ArrayList<Statement>(); for(Expression st : (List<Expression>)$2){ aux.add(new Write(getLine(),getColumn(),st)); } $$=aux; }
	 	 | READ expresionesCS ';'																{ List<Statement> aux = new ArrayList<Statement>(); for(Expression st : (List<Expression>)$2){ aux.add(new Read(getLine(),getColumn(),st));  } $$=aux; }
	 	 | expression '=' expression ';'														{ $$ = new ArrayList<Statement>(); ((List<Statement>)$$).add(new Assignment(getLine(),getColumn(),(Expression)$1,(Expression)$3)); }
	 	 | RETURN expression ';'																{ $$ = new ArrayList<Statement>(); ((List<Statement>)$$).add(new Return(getLine(),getColumn(),(Expression)$2)); }
		 | ID '(' expresionesCSOpt ')' ';'	  													{ $$ = new ArrayList<Statement>(); Variable var = new Variable(getLine(),getColumn(),(String)$1); ((List<Statement>)$$).add(new Invocation(getLine(),getColumn(),var,(List<Expression>)$3)); }
	 	 | ifElse																				{ $$ = new ArrayList<Statement>(); ((List<Statement>)$$).add((Statement)$1); }
	 	 | while																				{ $$ = new ArrayList<Statement>(); ((List<Statement>)$$).add((Statement)$1); }
	 	 ;

ifElse: IF expression ':' cuerpoCondicional				%prec MENOR_QUE_ELSE					{ Expression condicion = (Expression)$2; $$ = new IfStatement(condicion.getLine(),condicion.getColumn(),condicion,(List<Statement>)$4,new ArrayList<Statement>()); }
      | IF expression ':' cuerpoCondicional ELSE cuerpoCondicional	%prec ELSE					{ Expression condicion = (Expression)$2; $$ = new IfStatement(condicion.getLine(),condicion.getColumn(),condicion,(List<Statement>)$4,(List<Statement>)$6); }
      ;

while: WHILE expression ':' cuerpoCondicional													{ Expression condicion = (Expression)$2; $$ = new While(condicion.getLine(),condicion.getColumn(),condicion,(List<Statement>)$4); }		
     ;

cuerpoCondicional: sentencia																	{ $$ = $1; }
		 		 | '{' sentenciasOpt '}'														{ $$ = $2; }
		 		 ;

expresionesCSOpt:																				{ $$ = new ArrayList<Expression>(); }
				| expresionesCS																	{ $$ =$1; }
				;

expresionesCS: expression																		{ $$ = new ArrayList<Expression>(); ((List<Expression>)$$).add((Expression)$1); }
	     	 | expresionesCS ',' expression														{ $$ = $1; ((List<Expression>)$$).add((Expression)$3); }
	     	 ;

expression: INT_CONSTANT																		{ $$ = new IntLiteral(getLine(),getColumn(), (int)$1);   }
		  | REAL_CONSTANT																		{ $$ = new RealLiteral(getLine(),getColumn(), (double)$1); }
		  | CHAR_CONSTANT																		{ $$ = new CharLiteral(getLine(),getColumn(), (char)$1); }
		  | ID																					{ $$ = new Variable(getLine(),getColumn(), (String)$1);  }
		  | ID '(' expresionesCSOpt ')'															{ $$ = new Invocation(getLine(),getColumn(),new Variable(getLine(),getColumn(), (String)$1),(List<Expression>)$3); }
          | '(' expression ')'																	{ $$ = $2; }	
          | expression '[' expression ']'														{ $$ = new Indexing(getLine(),getColumn(),(Expression)$1,(Expression)$3); }
          | '(' tipoSimple ')' expression %prec CAST											{ $$ = new Cast(getLine(),getColumn(),(Type)$2,(Expression)$4); }
          | '-' expression 				  %prec UNARY											{ $$ = new UnaryMinus(getLine(),getColumn(),(Expression)$2); }
          | '!' expression     																	{ $$ = new UnaryNot(getLine(),getColumn(),(Expression)$2); }
          | expression '.' ID  																	{ $$ = new FieldAccess(getLine(),getColumn(),(Expression)$1,(String)$3); }   	
          | expression '*' expression															{ $$ = new Arithmetic(getLine(),getColumn(),(Expression)$1,"*",(Expression)$3); }
          | expression '+' expression															{ $$ = new Arithmetic(getLine(),getColumn(),(Expression)$1,"+",(Expression)$3); }
          | expression '%' expression															{ $$ = new Arithmetic(getLine(),getColumn(),(Expression)$1,"%",(Expression)$3); }	
          | expression '/' expression															{ $$ = new Arithmetic(getLine(),getColumn(),(Expression)$1,"/",(Expression)$3); }
          | expression '-' expression															{ $$ = new Arithmetic(getLine(),getColumn(),(Expression)$1,"-",(Expression)$3); }
          | expression '>' expression															{ $$ = new Comparision(getLine(),getColumn(),(Expression)$1,">",(Expression)$3); }
          | expression '<' expression															{ $$ = new Comparision(getLine(),getColumn(),(Expression)$1,"<",(Expression)$3); }
          | expression LESS_EQUALS expression													{ $$ = new Comparision(getLine(),getColumn(),(Expression)$1,"<=",(Expression)$3); }
          | expression GREATER_EQUALS expression												{ $$ = new Comparision(getLine(),getColumn(),(Expression)$1,">=",(Expression)$3); }
          | expression EQUALS expression														{ $$ = new Comparision(getLine(),getColumn(),(Expression)$1,"==",(Expression)$3); }
          | expression DISTINCT expression														{ $$ = new Comparision(getLine(),getColumn(),(Expression)$1,"!=",(Expression)$3); }
          | expression OR expression															{ $$ = new Logical(getLine(),getColumn(),(Expression)$1,"||",(Expression)$3); }
          | expression AND expression															{ $$ = new Logical(getLine(),getColumn(),(Expression)$1,"&&",(Expression)$3); }
          ;
	
%%

// * Código Java
// * Se crea una clase "Parser", lo que aquí ubiquemos será:
//	- Atributos, si son variables
//	- Métodos, si son funciones
//   de la clase "Parser"

// * Estamos obligados a implementar:
//	int yylex()
//	void yyerror(String)

// * Referencia al analizador léxico
private Scanner scanner;

// * Llamada al analizador léxico
private int yylex () {
    int token=0;
    try { 
		token=scanner.yylex(); 	
		this.yylval = scanner.getYylval();
    } catch(Throwable e) {
	    System.err.println ("Lexical error at line " + scanner.getLine() + " and column "+scanner.getColumn()+":\n\t"+e); 
    }
    return token;
}

// * Manejo de Errores Sintácticos
public void yyerror (String error) {
    System.err.println ("Syntactical error at line " + scanner.getLine() + " and column "+scanner.getColumn()+":\n\t"+error);
}

// * Constructor del Sintáctico
public Parser(Scanner scanner) {
	this.scanner = scanner;
}

private ASTNode AST;

public ASTNode getAST(){
	return AST;
}

public int getLine(){
	return scanner.getLine();
}

public int getColumn(){
	return scanner.getColumn();
}

private void comprobarCamposRepetidos(List<RecordField> list1, List<RecordField> list2){
		for(RecordField re : list2)
			if(list1.contains(re))
				new ErrorType(getLine(),getColumn(),String.format("ERROR en linea: %d, columna %d: El campo %s ya está declarado. ", getLine(), getColumn(), re.getName()));
}
