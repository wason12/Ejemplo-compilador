// ************  Código a incluir ********************

package scanner;
import parser.Parser;

%%
// ************  Opciones ********************
// % debug // * Opción para depurar
%byaccj
%class Scanner
%public
%unicode
%line
%column

%{
// ************  Atributos y métodos ********************

// * Para acceder al número de línea (yyline es package)
public int getLine() { 
	// * Flex empieza en cero
	return yyline+1;
}

// * Para acceder al número de columna (yycolumn es package)
public int getColumn() { 
	// * Flex empieza en cero
	return yycolumn+1;
}

// * Valor semantico del token
private Object yylval;
public Object getYylval() {
	return this.yylval;
}

%}

// ************  Patrones (macros) ********************
ConstanteEntera = [0-9]+
ComentarioMultilinea = \"\"\"~\"\"\"
ComentarioUnilinea = #.*
Identificador = [a-zA-Z_ñÑ][a-zA-Z0-9_ñÑ]*

ConstanteReal = [0-9]*\.[0-9]*
ConstanteRealExponente = [0-9]+\.?[0-9]*[Ee][+-]?[0-9]+

ConstanteCaracterSimple = '.'
ConstanteCaracterAscii = '\\[0-9]{1,3}'
ConstanteCaracterEspecial = '\\[btnfr\"\'\\]'

%%
// ************  Acciones ********************

// Un caracter
[+\-*/;\()=\!><%,:\\\{\}\[\]\.]	{ this.yylval = yytext().charAt(0);
							return yycharat(0); }

// Dos caracteres
==	{ this.yylval = yytext();
	  return Parser.EQUALS;  }

"<=" { this.yylval = yytext();
	  return Parser.LESS_EQUALS; }

>=	{ this.yylval = yytext();
	  return Parser.GREATER_EQUALS; }
	
"!=" { this.yylval = yytext();
	  return Parser.DISTINCT; }
	  
"||" { this.yylval = yytext();
	  return Parser.OR; }
	  
&&	{ this.yylval = yytext();
	  return Parser.AND; }

// Palabras reservadas
input 	{ this.yylval = "READ";
			return Parser.READ; }
			
print  	{ this.yylval = "WRITE";
			return Parser.WRITE; }
			
def 	{ this.yylval = "DEF";
			return Parser.DEF; }
			
while 	{ this.yylval = "WHILE";
			return Parser.WHILE; }
			
if 		{ this.yylval = "IF";
			return Parser.IF; }
					
else 	{ this.yylval = "ELSE";
			return Parser.ELSE; }
			
int 	{ this.yylval = "INT";
			return Parser.INT; }
			
double 	{ this.yylval = "DOUBLE";
			return Parser.DOUBLE; }
			
char 	{ this.yylval = "CHAR";
			return Parser.CHAR; }
			
struct 	{ this.yylval = "STRUCT";
			return Parser.STRUCT; }
			
return 	{ this.yylval = "RETURN";
			return Parser.RETURN; }
			
void 	{ this.yylval = "VOID";
			return Parser.VOID; }
			
main 	{ this.yylval = "MAIN";
			return Parser.MAIN; }

// Patrones
{ConstanteEntera}	{ this.yylval = new Integer(yytext());
         			  return Parser.INT_CONSTANT;  }
         			  
{Identificador} 	{ this.yylval = yytext();
					return Parser.ID; }
					
{ConstanteCaracterSimple} { this.yylval = yycharat(1);
							return Parser.CHAR_CONSTANT; }
						
{ConstanteCaracterAscii} { String substring = yytext().substring(2, yytext().length()-1);
							this.yylval = (char) Integer.parseInt(substring);
							return Parser.CHAR_CONSTANT; }						

{ConstanteCaracterEspecial} { switch (yytext().charAt(2)) {
								case 'b':
									this.yylval = '\b';
									break;
						
								case 't':
									this.yylval = '\t';
									break;
						
								case 'n':
									this.yylval = '\n';
									break;
						
								case 'f':
									this.yylval = '\f';
									break;
						
								case 'r':
									this.yylval = '\r';
									break;
						
								case '\"':
									this.yylval = '\"';
									break;
						
								case '\'':
									this.yylval = '\'';
									break;
						
								case '\\':
									this.yylval = '\\';
									break;
								}
							return Parser.CHAR_CONSTANT; }
						
{ConstanteReal}|{ConstanteRealExponente}   { this.yylval = Double.valueOf(yytext());
																return Parser.REAL_CONSTANT; }

{ComentarioUnilinea}|{ComentarioMultilinea}	{ }
						
[ \n\r\t] { }
		  
// * Cualquier otro carácter
.			{ System.err.println ("Lexical error at line " 	+ this.getLine() + " and column "+getColumn()+":\n\tUnknow character \'"+ yycharat(0)+"\'."); }		
				
			
			