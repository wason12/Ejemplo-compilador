//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package parser;



//#line 15 "../../src/parser/parser.y"
/* * Declaraciones de código Java*/
/* * Se sitúan al comienzo del archivo generado*/
/* * El package lo añade yacc si utilizamos la opción -Jpackage*/
import scanner.Scanner;
import java.io.Reader;
import ast.definitions.*;
import ast.expressions.*;
import ast.main.*;
import ast.sentencias.*;
import ast.type.*;
import java.util.List;
import java.util.ArrayList;
//#line 30 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Object dup_yyval(Object val)
{
  return val;
}
//#### end semantic value section ####
public final static short AND=257;
public final static short OR=258;
public final static short LESS_EQUALS=259;
public final static short GREATER_EQUALS=260;
public final static short EQUALS=261;
public final static short DISTINCT=262;
public final static short UNARY=263;
public final static short CAST=264;
public final static short MENOR_QUE_ELSE=265;
public final static short ELSE=266;
public final static short INT_CONSTANT=267;
public final static short ID=268;
public final static short REAL_CONSTANT=269;
public final static short CHAR_CONSTANT=270;
public final static short DEF=271;
public final static short INT=272;
public final static short DOUBLE=273;
public final static short CHAR=274;
public final static short MAIN=275;
public final static short IF=276;
public final static short WRITE=277;
public final static short READ=278;
public final static short STRUCT=279;
public final static short RETURN=280;
public final static short WHILE=281;
public final static short VOID=282;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    1,    1,    3,    3,    4,    6,    6,    8,
    5,   12,    2,    9,    9,   13,   13,   14,   10,   10,
   15,   15,   15,    7,    7,    7,   16,   17,   17,   18,
   18,   19,   11,   11,   20,   20,   21,   21,   22,   22,
   23,   23,   23,   23,   23,   23,   23,   27,   27,   28,
   29,   29,   26,   26,   24,   24,   25,   25,   25,   25,
   25,   25,   25,   25,   25,   25,   25,   25,   25,   25,
   25,   25,   25,   25,   25,   25,   25,   25,   25,   25,
};
final static short yylen[] = {                            2,
    2,    1,    1,    2,    1,    1,    4,    1,    3,    0,
   11,    0,   10,    0,    1,    1,    3,    3,    1,    1,
    1,    1,    1,    1,    1,    4,    4,    0,    1,    1,
    2,    4,    2,    1,    1,    2,    0,    1,    1,    2,
    3,    3,    4,    3,    5,    1,    1,    4,    6,    4,
    1,    3,    0,    1,    1,    3,    1,    1,    1,    1,
    4,    3,    4,    4,    2,    2,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
};
final static short yydefred[] = {                         0,
    8,    0,    0,    0,    2,    3,    5,    6,    0,   10,
   12,    1,    4,    0,    0,    0,    0,    0,   21,   22,
   23,    0,    0,   24,   25,    9,    0,    0,    0,    0,
    7,    0,    0,    0,   16,    0,    0,    0,    0,    0,
   30,    0,    0,    0,    0,   26,    0,   27,   31,   18,
    0,   17,    0,    0,   19,    0,   20,    0,   32,    0,
    0,    0,    0,   57,    0,   58,   59,    0,    0,    0,
    0,    0,   35,    0,    0,   34,    0,   39,    0,   46,
   47,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   13,   36,   33,    0,   40,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   11,    0,    0,   62,    0,
    0,    0,   41,    0,   42,   44,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   67,    0,    0,    0,    0,    0,   51,    0,    0,
   50,   43,   63,   61,   45,    0,    0,   52,   49,
};
final static short yydgoto[] = {                          3,
    4,    5,    6,    7,    8,    9,   23,   16,   33,   56,
   74,   17,   34,   35,   24,   25,   39,   40,   41,   75,
   76,   77,   78,  120,   79,  121,   80,   81,  149,
};
final static short yysindex[] = {                      -258,
    0, -239,    0, -258,    0,    0,    0,    0,  -19,    0,
    0,    0,    0,  -58, -259,  -22,   -5, -211,    0,    0,
    0,  -59,   10,    0,    0,    0, -197,   32,  -11, -180,
    0,   44,   62,   72,    0,   64,  -58,    3,    1, -180,
    0, -188,   69, -197, -154,    0,  -58,    0,    0,    0,
 -168,    0,    6,   71,    0,    8,    0,   -3,    0,   -3,
  -29,  -29,   27,    0,   92,    0,    0,  -29,  -29,  -29,
  -29,  -29,    0,   14,   -3,    0,   12,    0,  370,    0,
    0,   17,   93,  -41,  -41,  110,  397,  -29,  404,   -1,
  577,    4,  430,  454,    0,    0,    0,   92,    0,  -29,
  -29,  -29,  -29,  -29,  -29,  -29,  -29,  -29,  -29,  -29,
  -29,  -29,  -29, -116,  -29,    0,  -29,  -29,    0,  114,
  119,  -33,    0,  -29,    0,    0,  -33,  487,  633,  633,
   33,   33,   33,   33,   33,   33,    7,    7,  -41,  -41,
  -41,    0,  517,  125,  -41,  108,   12,    0,  -98,  577,
    0,    0,    0,    0,    0,   52,  -33,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  134,    0,    0,   53,
    0,    0,    0,  138,    0,    0,    0,    0,    0,   60,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   63,    0,   63,
    0,    0,    0,    0,  165,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   63,    0,   65,    0,    0,    0,
    0,    0,   50,   76,  103,    0,    0,  146,    0,    0,
   18,    0,    0,    0,    0,    0,    0,  524,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  146,    0,    0,  148,
    0,    0,    0,    0,    0,    0,    0,    0,  -38,  -27,
  698,  756,  762,  784,  810,  820,  640,  676,  112,  139,
  282,    0,    0,    0,  344,  551,   63,    0,  -18,   24,
    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  187,  188,  -34,    0,   59,   78,    0,    0,    0,
  133,    0,    0,  150,  -23,    0,    0,    0,  155,    0,
  -73,    0,  -76,   31,  878,   86,    0,    0, -119,
};
final static int YYTABLESIZE=1082;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         62,
   99,   97,   80,   62,  114,   80,   63,  151,   26,    1,
   63,   61,    2,   79,   48,   61,   79,   27,   50,   80,
   80,   48,   80,   73,   15,   73,   48,   57,   10,   62,
   79,   79,   18,   79,   28,   11,   63,  159,   14,   86,
   96,   61,  124,  113,   62,  148,   15,  124,  111,  115,
  148,   63,  114,  112,   80,   29,   61,  123,   55,   62,
   47,   55,  125,   30,   56,   79,   63,   56,   31,  113,
   32,   61,   36,  156,  111,  109,   55,  110,  114,  112,
  148,   37,   56,   19,   20,   21,   60,    1,   38,  147,
   60,   60,   60,   60,   60,   60,   60,  115,   38,   90,
   92,   42,   43,   19,   20,   21,   48,   60,   60,   60,
   60,   60,   65,   55,   46,   44,   65,   65,   65,   65,
   65,   45,   65,  115,   54,   48,   51,   53,   58,   59,
   60,   88,  117,   65,   65,   65,   65,   65,   95,   66,
   60,  116,   60,   66,   66,   66,   66,   66,   68,   66,
  118,  142,   68,   68,   68,   68,   68,  124,   68,  146,
   66,   66,   66,   66,   66,  154,  155,  157,   65,   68,
   68,   68,   68,   68,   14,   71,  158,   28,   15,   71,
   71,   71,   71,   71,   29,   71,   53,   37,   54,   38,
   12,   13,   82,   52,   49,   66,   71,   71,   71,   71,
   71,   60,  144,    0,   68,    0,   60,   60,    8,   60,
   60,   60,    0,   19,   20,   21,    0,    0,   80,   80,
   22,    0,    8,    0,   60,   60,   60,    0,    0,   79,
   79,   71,    0,   64,   98,   66,   67,   64,   83,   66,
   67,    0,   68,   69,   70,    0,   71,   72,   48,   48,
   48,   48,    0,    0,    0,   60,    0,   48,   48,   48,
    0,   48,   48,   64,   65,   66,   67,    0,    0,    0,
    0,    0,   68,   69,   70,    0,   71,   72,   64,   98,
   66,   67,    0,    0,    0,    0,    0,   68,   69,   70,
    0,   71,   72,   64,   83,   66,   67,    0,   19,   20,
   21,    0,    0,    0,    0,    0,   60,   60,   60,   60,
   60,   60,    0,    0,    0,    0,    0,    0,   70,    0,
    0,    0,   70,   70,   70,   70,   70,    0,   70,    0,
    0,    0,   65,   65,   65,   65,   65,   65,    0,   70,
   70,   70,   70,   70,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   66,
   66,   66,   66,   66,   66,    0,    0,    0,   68,   68,
   68,   68,   68,   68,   70,    0,    0,    0,    0,    0,
   64,    0,    0,    0,   64,   64,   64,   64,   64,    0,
   64,    0,    0,    0,    0,   71,   71,   71,   71,   71,
   71,   64,   64,   64,   64,   64,  113,    0,    0,    0,
    0,  111,  109,    0,  110,  114,  112,    0,    0,    0,
    0,   60,   60,   60,   60,   60,   60,    0,    0,  104,
  100,  103,    0,  113,    0,    0,   64,  119,  111,  109,
  113,  110,  114,  112,    0,  111,  109,    0,  110,  114,
  112,    0,    0,    0,    0,    0,  104,    0,  103,    0,
  115,  122,    0,  104,    0,  103,  113,    0,    0,    0,
    0,  111,  109,    0,  110,  114,  112,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  115,  126,  104,
  113,  103,    0,    0,  115,  111,  109,    0,  110,  114,
  112,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  127,    0,  104,    0,  103,    0,    0,    0,    0,
  115,    0,    0,  113,    0,    0,    0,    0,  111,  109,
    0,  110,  114,  112,    0,    0,    0,    0,   70,   70,
   70,   70,   70,   70,  115,  152,  104,    0,  103,    0,
    0,    0,    0,  113,    0,    0,    0,    0,  111,  109,
   60,  110,  114,  112,    0,   60,   60,    0,   60,   60,
   60,    0,    0,    0,    0,    0,  104,  115,  103,    0,
    0,    0,    0,   60,   60,   60,    0,   61,    0,    0,
    0,    0,   61,   61,    0,   61,   61,   61,    0,    0,
   64,   64,   64,   64,   64,   64,    0,  115,    0,  153,
   61,   61,   61,  113,   60,    0,    0,    0,  111,  109,
    0,  110,  114,  112,    0,    0,  101,  102,  105,  106,
  107,  108,    0,    0,    0,    0,  104,    0,  103,    0,
    0,   61,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  101,  102,  105,  106,  107,  108,    0,
  101,  102,  105,  106,  107,  108,    0,  115,    0,  113,
    0,    0,    0,    0,  111,  109,    0,  110,  114,  112,
   69,    0,   69,   69,   69,    0,  101,  102,  105,  106,
  107,  108,  104,    0,  103,    0,    0,   69,   69,   69,
   69,   69,    0,    0,    0,    0,    0,    0,    0,    0,
  101,  102,  105,  106,  107,  108,   72,    0,   72,   72,
   72,    0,    0,  115,    0,    0,    0,    0,    0,    0,
    0,    0,   69,   72,   72,   72,   72,   72,   73,    0,
    0,   73,    0,  101,  102,  105,  106,  107,  108,    0,
    0,    0,    0,    0,    0,   73,   73,   73,   73,   73,
    0,    0,    0,    0,    0,    0,    0,    0,   72,    0,
    0,    0,    0,  101,  102,  105,  106,  107,  108,    0,
   60,   60,   60,   60,   60,   60,    0,    0,    0,    0,
   73,    0,    0,    0,    0,    0,   74,    0,    0,   74,
    0,    0,   75,    0,    0,   75,    0,   61,   61,   61,
   61,   61,   61,   74,   74,   74,   74,   74,    0,   75,
   75,   75,   75,   75,   76,    0,    0,   76,    0,    0,
    0,    0,    0,  101,  102,  105,  106,  107,  108,    0,
    0,   76,   76,   76,   76,   76,    0,    0,   74,    0,
   77,    0,    0,   77,   75,    0,    0,    0,    0,    0,
   78,    0,    0,   78,    0,    0,    0,   77,   77,   77,
   77,   77,    0,    0,    0,    0,   76,   78,   78,   78,
   78,   78,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  105,  106,  107,  108,    0,   69,   69,   69,   69,
   69,   69,   77,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   78,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   72,   72,   72,   72,   72,   72,   84,   85,
   87,    0,    0,    0,    0,   89,   91,   91,   93,   94,
    0,    0,    0,    0,   73,   73,   73,   73,   73,   73,
    0,    0,    0,    0,    0,   91,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  128,  129,  130,
  131,  132,  133,  134,  135,  136,  137,  138,  139,  140,
  141,    0,  143,    0,   91,  145,    0,    0,    0,    0,
    0,  150,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   74,   74,   74,   74,   74,   74,   75,   75,
   75,   75,   75,   75,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   76,   76,   76,   76,   76,   76,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   77,   77,   77,   77,
   77,   77,    0,    0,    0,    0,   78,   78,   78,   78,
   78,   78,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   77,   75,   41,   33,   46,   44,   40,  127,  268,  268,
   40,   45,  271,   41,   33,   45,   44,   40,   42,   58,
   59,   40,   61,   58,   44,   60,   45,   51,  268,   33,
   58,   59,   91,   61,   40,  275,   40,  157,   58,   63,
   75,   45,   44,   37,   33,  122,   44,   44,   42,   91,
  127,   40,   46,   47,   93,  267,   45,   59,   41,   33,
   58,   44,   59,  123,   41,   93,   40,   44,   59,   37,
  268,   45,   41,  147,   42,   43,   59,   45,   46,   47,
  157,   93,   59,  272,  273,  274,   37,  268,   30,  123,
   41,   42,   43,   44,   45,   46,   47,   91,   40,   69,
   70,   58,   41,  272,  273,  274,  125,   58,   59,   60,
   61,   62,   37,  282,   37,   44,   41,   42,   43,   44,
   45,   58,   47,   91,   47,  125,   58,  282,  123,   59,
  123,   40,   40,   58,   59,   60,   61,   62,  125,   37,
   91,  125,   93,   41,   42,   43,   44,   45,   37,   47,
   41,  268,   41,   42,   43,   44,   45,   44,   47,   41,
   58,   59,   60,   61,   62,   41,   59,  266,   93,   58,
   59,   60,   61,   62,   41,   37,  125,  125,   41,   41,
   42,   43,   44,   45,  125,   47,   41,  125,   41,  125,
    4,    4,   60,   44,   40,   93,   58,   59,   60,   61,
   62,   37,  117,   -1,   93,   -1,   42,   43,   44,   45,
   46,   47,   -1,  272,  273,  274,   -1,   -1,  257,  258,
  279,   -1,   58,   -1,   60,   61,   62,   -1,   -1,  257,
  258,   93,   -1,  267,  268,  269,  270,  267,  268,  269,
  270,   -1,  276,  277,  278,   -1,  280,  281,  267,  268,
  269,  270,   -1,   -1,   -1,   91,   -1,  276,  277,  278,
   -1,  280,  281,  267,  268,  269,  270,   -1,   -1,   -1,
   -1,   -1,  276,  277,  278,   -1,  280,  281,  267,  268,
  269,  270,   -1,   -1,   -1,   -1,   -1,  276,  277,  278,
   -1,  280,  281,  267,  268,  269,  270,   -1,  272,  273,
  274,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,   -1,   -1,   -1,   -1,   -1,   -1,   37,   -1,
   -1,   -1,   41,   42,   43,   44,   45,   -1,   47,   -1,
   -1,   -1,  257,  258,  259,  260,  261,  262,   -1,   58,
   59,   60,   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,
  258,  259,  260,  261,  262,   -1,   -1,   -1,  257,  258,
  259,  260,  261,  262,   93,   -1,   -1,   -1,   -1,   -1,
   37,   -1,   -1,   -1,   41,   42,   43,   44,   45,   -1,
   47,   -1,   -1,   -1,   -1,  257,  258,  259,  260,  261,
  262,   58,   59,   60,   61,   62,   37,   -1,   -1,   -1,
   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,
   -1,  257,  258,  259,  260,  261,  262,   -1,   -1,   60,
   61,   62,   -1,   37,   -1,   -1,   93,   41,   42,   43,
   37,   45,   46,   47,   -1,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,   -1,   -1,   60,   -1,   62,   -1,
   91,   58,   -1,   60,   -1,   62,   37,   -1,   -1,   -1,
   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   91,   59,   60,
   37,   62,   -1,   -1,   91,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   58,   -1,   60,   -1,   62,   -1,   -1,   -1,   -1,
   91,   -1,   -1,   37,   -1,   -1,   -1,   -1,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,   -1,  257,  258,
  259,  260,  261,  262,   91,   59,   60,   -1,   62,   -1,
   -1,   -1,   -1,   37,   -1,   -1,   -1,   -1,   42,   43,
   37,   45,   46,   47,   -1,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,   -1,   -1,   60,   91,   62,   -1,
   -1,   -1,   -1,   60,   61,   62,   -1,   37,   -1,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
  257,  258,  259,  260,  261,  262,   -1,   91,   -1,   93,
   60,   61,   62,   37,   91,   -1,   -1,   -1,   42,   43,
   -1,   45,   46,   47,   -1,   -1,  257,  258,  259,  260,
  261,  262,   -1,   -1,   -1,   -1,   60,   -1,   62,   -1,
   -1,   91,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,   -1,
  257,  258,  259,  260,  261,  262,   -1,   91,   -1,   37,
   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,
   41,   -1,   43,   44,   45,   -1,  257,  258,  259,  260,
  261,  262,   60,   -1,   62,   -1,   -1,   58,   59,   60,
   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  257,  258,  259,  260,  261,  262,   41,   -1,   43,   44,
   45,   -1,   -1,   91,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   93,   58,   59,   60,   61,   62,   41,   -1,
   -1,   44,   -1,  257,  258,  259,  260,  261,  262,   -1,
   -1,   -1,   -1,   -1,   -1,   58,   59,   60,   61,   62,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   93,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,   -1,
  257,  258,  259,  260,  261,  262,   -1,   -1,   -1,   -1,
   93,   -1,   -1,   -1,   -1,   -1,   41,   -1,   -1,   44,
   -1,   -1,   41,   -1,   -1,   44,   -1,  257,  258,  259,
  260,  261,  262,   58,   59,   60,   61,   62,   -1,   58,
   59,   60,   61,   62,   41,   -1,   -1,   44,   -1,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,   -1,
   -1,   58,   59,   60,   61,   62,   -1,   -1,   93,   -1,
   41,   -1,   -1,   44,   93,   -1,   -1,   -1,   -1,   -1,
   41,   -1,   -1,   44,   -1,   -1,   -1,   58,   59,   60,
   61,   62,   -1,   -1,   -1,   -1,   93,   58,   59,   60,
   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  259,  260,  261,  262,   -1,  257,  258,  259,  260,
  261,  262,   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  257,  258,  259,  260,  261,  262,   61,   62,
   63,   -1,   -1,   -1,   -1,   68,   69,   70,   71,   72,
   -1,   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,
   -1,   -1,   -1,   -1,   -1,   88,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  100,  101,  102,
  103,  104,  105,  106,  107,  108,  109,  110,  111,  112,
  113,   -1,  115,   -1,  117,  118,   -1,   -1,   -1,   -1,
   -1,  124,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  257,  258,  259,  260,  261,  262,  257,  258,
  259,  260,  261,  262,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  257,  258,  259,  260,  261,  262,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,
};
}
final static short YYFINAL=3;
final static short YYMAXTOKEN=282;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'",
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"AND","OR","LESS_EQUALS",
"GREATER_EQUALS","EQUALS","DISTINCT","UNARY","CAST","MENOR_QUE_ELSE","ELSE",
"INT_CONSTANT","ID","REAL_CONSTANT","CHAR_CONSTANT","DEF","INT","DOUBLE","CHAR",
"MAIN","IF","WRITE","READ","STRUCT","RETURN","WHILE","VOID",
};
final static String yyrule[] = {
"$accept : programa",
"programa : definiciones defMain",
"programa : defMain",
"definiciones : definicion",
"definiciones : definiciones definicion",
"definicion : definicionVariable",
"definicion : definicionFuncion",
"definicionVariable : idsCS ':' tipo ';'",
"idsCS : ID",
"idsCS : idsCS ',' ID",
"$$1 :",
"definicionFuncion : DEF ID $$1 '(' parametrosCSopt ')' ':' tipoRetorno '{' cuerpoFuncion '}'",
"$$2 :",
"defMain : DEF MAIN $$2 '(' ')' ':' VOID '{' cuerpoFuncion '}'",
"parametrosCSopt :",
"parametrosCSopt : parametrosCS",
"parametrosCS : parametro",
"parametrosCS : parametrosCS ',' parametro",
"parametro : ID ':' tipoSimple",
"tipoRetorno : VOID",
"tipoRetorno : tipoSimple",
"tipoSimple : INT",
"tipoSimple : DOUBLE",
"tipoSimple : CHAR",
"tipo : tipoSimple",
"tipo : structura",
"tipo : '[' INT_CONSTANT ']' tipo",
"structura : STRUCT '{' definicionesCamposOpt '}'",
"definicionesCamposOpt :",
"definicionesCamposOpt : definicionesCampos",
"definicionesCampos : definicionCampo",
"definicionesCampos : definicionesCampos definicionCampo",
"definicionCampo : idsCS ':' tipo ';'",
"cuerpoFuncion : definicionesVariables sentenciasOpt",
"cuerpoFuncion : sentenciasOpt",
"definicionesVariables : definicionVariable",
"definicionesVariables : definicionesVariables definicionVariable",
"sentenciasOpt :",
"sentenciasOpt : sentencias",
"sentencias : sentencia",
"sentencias : sentencias sentencia",
"sentencia : WRITE expresionesCS ';'",
"sentencia : READ expresionesCS ';'",
"sentencia : expression '=' expression ';'",
"sentencia : RETURN expression ';'",
"sentencia : ID '(' expresionesCSOpt ')' ';'",
"sentencia : ifElse",
"sentencia : while",
"ifElse : IF expression ':' cuerpoCondicional",
"ifElse : IF expression ':' cuerpoCondicional ELSE cuerpoCondicional",
"while : WHILE expression ':' cuerpoCondicional",
"cuerpoCondicional : sentencia",
"cuerpoCondicional : '{' sentenciasOpt '}'",
"expresionesCSOpt :",
"expresionesCSOpt : expresionesCS",
"expresionesCS : expression",
"expresionesCS : expresionesCS ',' expression",
"expression : INT_CONSTANT",
"expression : REAL_CONSTANT",
"expression : CHAR_CONSTANT",
"expression : ID",
"expression : ID '(' expresionesCSOpt ')'",
"expression : '(' expression ')'",
"expression : expression '[' expression ']'",
"expression : '(' tipoSimple ')' expression",
"expression : '-' expression",
"expression : '!' expression",
"expression : expression '.' ID",
"expression : expression '*' expression",
"expression : expression '+' expression",
"expression : expression '%' expression",
"expression : expression '/' expression",
"expression : expression '-' expression",
"expression : expression '>' expression",
"expression : expression '<' expression",
"expression : expression LESS_EQUALS expression",
"expression : expression GREATER_EQUALS expression",
"expression : expression EQUALS expression",
"expression : expression DISTINCT expression",
"expression : expression OR expression",
"expression : expression AND expression",
};

//#line 191 "../../src/parser/parser.y"

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
//#line 605 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 56 "../../src/parser/parser.y"
{ ((List<Definition>)val_peek(1)).add((Definition)val_peek(0)); AST = new Program(getLine(),getColumn(),(List<Definition>)val_peek(1)); }
break;
case 2:
//#line 57 "../../src/parser/parser.y"
{ List<Definition> aux = new ArrayList<Definition>(); aux.add((Definition)val_peek(0)); AST = new Program(getLine(),getColumn(),aux); }
break;
case 3:
//#line 60 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 4:
//#line 61 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List<Definition>)yyval).addAll((List<Definition>)val_peek(0)); }
break;
case 5:
//#line 64 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 6:
//#line 65 "../../src/parser/parser.y"
{ List<Definition> aux = new ArrayList<Definition>(); aux.add((Definition)val_peek(0)); yyval=aux; }
break;
case 7:
//#line 68 "../../src/parser/parser.y"
{ List<VarDefinition> aux = new ArrayList<VarDefinition>(); for(String st : (List<String>)val_peek(3)){ aux.add(new VarDefinition(getLine(),getColumn(),st,(Type)val_peek(1))); } yyval=aux; }
break;
case 8:
//#line 71 "../../src/parser/parser.y"
{ yyval = new ArrayList<String>(); ((List<String>)yyval).add((String)val_peek(0)); }
break;
case 9:
//#line 72 "../../src/parser/parser.y"
{ yyval = val_peek(2); if(((List<String>)yyval).contains((String)val_peek(0))) new ErrorType(getLine(),getColumn(),"ERROR: La variable " + (String)val_peek(0) + " ya está declarada."); ((List<String>)yyval).add((String)val_peek(0)); }
break;
case 10:
//#line 75 "../../src/parser/parser.y"
{ yyval = new int[]{getLine(), getColumn()}; }
break;
case 11:
//#line 75 "../../src/parser/parser.y"
{ yyval = new FuncDefinition(((int[])val_peek(8))[0],((int[])val_peek(8))[1],(String)val_peek(9),new FunctionType(getLine(),getColumn(),(List<VarDefinition>)val_peek(6),(Type)val_peek(3)),(List<Statement>)val_peek(1)); }
break;
case 12:
//#line 78 "../../src/parser/parser.y"
{ yyval = new int[]{getLine(), getColumn()}; }
break;
case 13:
//#line 78 "../../src/parser/parser.y"
{ yyval = new FuncDefinition(((int[])val_peek(7))[0],((int[])val_peek(7))[1],"main",new FunctionType(getLine(),getColumn(),new ArrayList<VarDefinition>(),VoidType.getInstancia()),(List<Statement>)val_peek(1)); }
break;
case 14:
//#line 81 "../../src/parser/parser.y"
{ yyval = new ArrayList<VarDefinition>(); }
break;
case 15:
//#line 82 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 16:
//#line 85 "../../src/parser/parser.y"
{ yyval = new ArrayList<VarDefinition>(); ((List<VarDefinition>)yyval).add( (VarDefinition)val_peek(0) ); }
break;
case 17:
//#line 86 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List<VarDefinition>)yyval).add( (VarDefinition)val_peek(0) ); }
break;
case 18:
//#line 89 "../../src/parser/parser.y"
{ yyval = new VarDefinition(getLine(),getColumn(),(String)val_peek(2),(Type)val_peek(0)); }
break;
case 19:
//#line 92 "../../src/parser/parser.y"
{ yyval = VoidType.getInstancia(); }
break;
case 20:
//#line 93 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 21:
//#line 96 "../../src/parser/parser.y"
{ yyval = IntType.getInstancia();   }
break;
case 22:
//#line 97 "../../src/parser/parser.y"
{ yyval = RealType.getInstancia();  }
break;
case 23:
//#line 98 "../../src/parser/parser.y"
{ yyval = CharType.getInstancia();  }
break;
case 24:
//#line 101 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 25:
//#line 102 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 26:
//#line 103 "../../src/parser/parser.y"
{ yyval = new ArrayType(getLine(),getColumn(),(int)val_peek(2),(Type)val_peek(0)); }
break;
case 27:
//#line 106 "../../src/parser/parser.y"
{ yyval = new RecordType(getLine(),getColumn(),(List<RecordField>)val_peek(1)); }
break;
case 28:
//#line 109 "../../src/parser/parser.y"
{ yyval = new ArrayList<RecordType>(); }
break;
case 29:
//#line 110 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 30:
//#line 113 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 31:
//#line 114 "../../src/parser/parser.y"
{ yyval = val_peek(1); comprobarCamposRepetidos((List<RecordField>)yyval,(List<RecordField>)val_peek(0)); ((List<RecordField>)yyval).addAll((List<RecordField>)val_peek(0)); }
break;
case 32:
//#line 117 "../../src/parser/parser.y"
{ List<RecordField> aux = new ArrayList<RecordField>(); for(String st : (List<String>)val_peek(3)){ aux.add(new RecordField(st,(Type)val_peek(1))); } yyval=aux; }
break;
case 33:
//#line 120 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List<Statement>)yyval).addAll((List<Statement>)val_peek(0)); }
break;
case 34:
//#line 121 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 35:
//#line 124 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 36:
//#line 125 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List<VarDefinition>)yyval).addAll((List<VarDefinition>)val_peek(0)); }
break;
case 37:
//#line 128 "../../src/parser/parser.y"
{ yyval = new ArrayList<Statement>(); }
break;
case 38:
//#line 129 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 39:
//#line 132 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 40:
//#line 133 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List<Statement>)yyval).addAll((List<Statement>)val_peek(0)); }
break;
case 41:
//#line 136 "../../src/parser/parser.y"
{ List<Statement> aux = new ArrayList<Statement>(); for(Expression st : (List<Expression>)val_peek(1)){ aux.add(new Write(getLine(),getColumn(),st)); } yyval=aux; }
break;
case 42:
//#line 137 "../../src/parser/parser.y"
{ List<Statement> aux = new ArrayList<Statement>(); for(Expression st : (List<Expression>)val_peek(1)){ aux.add(new Read(getLine(),getColumn(),st));  } yyval=aux; }
break;
case 43:
//#line 138 "../../src/parser/parser.y"
{ yyval = new ArrayList<Statement>(); ((List<Statement>)yyval).add(new Assignment(getLine(),getColumn(),(Expression)val_peek(3),(Expression)val_peek(1))); }
break;
case 44:
//#line 139 "../../src/parser/parser.y"
{ yyval = new ArrayList<Statement>(); ((List<Statement>)yyval).add(new Return(getLine(),getColumn(),(Expression)val_peek(1))); }
break;
case 45:
//#line 140 "../../src/parser/parser.y"
{ yyval = new ArrayList<Statement>(); Variable var = new Variable(getLine(),getColumn(),(String)val_peek(4)); ((List<Statement>)yyval).add(new Invocation(getLine(),getColumn(),var,(List<Expression>)val_peek(2))); }
break;
case 46:
//#line 141 "../../src/parser/parser.y"
{ yyval = new ArrayList<Statement>(); ((List<Statement>)yyval).add((Statement)val_peek(0)); }
break;
case 47:
//#line 142 "../../src/parser/parser.y"
{ yyval = new ArrayList<Statement>(); ((List<Statement>)yyval).add((Statement)val_peek(0)); }
break;
case 48:
//#line 145 "../../src/parser/parser.y"
{ Expression condicion = (Expression)val_peek(2); yyval = new IfStatement(condicion.getLine(),condicion.getColumn(),condicion,(List<Statement>)val_peek(0),new ArrayList<Statement>()); }
break;
case 49:
//#line 146 "../../src/parser/parser.y"
{ Expression condicion = (Expression)val_peek(4); yyval = new IfStatement(condicion.getLine(),condicion.getColumn(),condicion,(List<Statement>)val_peek(2),(List<Statement>)val_peek(0)); }
break;
case 50:
//#line 149 "../../src/parser/parser.y"
{ Expression condicion = (Expression)val_peek(2); yyval = new While(condicion.getLine(),condicion.getColumn(),condicion,(List<Statement>)val_peek(0)); }
break;
case 51:
//#line 152 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 52:
//#line 153 "../../src/parser/parser.y"
{ yyval = val_peek(1); }
break;
case 53:
//#line 156 "../../src/parser/parser.y"
{ yyval = new ArrayList<Expression>(); }
break;
case 54:
//#line 157 "../../src/parser/parser.y"
{ yyval =val_peek(0); }
break;
case 55:
//#line 160 "../../src/parser/parser.y"
{ yyval = new ArrayList<Expression>(); ((List<Expression>)yyval).add((Expression)val_peek(0)); }
break;
case 56:
//#line 161 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List<Expression>)yyval).add((Expression)val_peek(0)); }
break;
case 57:
//#line 164 "../../src/parser/parser.y"
{ yyval = new IntLiteral(getLine(),getColumn(), (int)val_peek(0));   }
break;
case 58:
//#line 165 "../../src/parser/parser.y"
{ yyval = new RealLiteral(getLine(),getColumn(), (double)val_peek(0)); }
break;
case 59:
//#line 166 "../../src/parser/parser.y"
{ yyval = new CharLiteral(getLine(),getColumn(), (char)val_peek(0)); }
break;
case 60:
//#line 167 "../../src/parser/parser.y"
{ yyval = new Variable(getLine(),getColumn(), (String)val_peek(0));  }
break;
case 61:
//#line 168 "../../src/parser/parser.y"
{ yyval = new Invocation(getLine(),getColumn(),new Variable(getLine(),getColumn(), (String)val_peek(3)),(List<Expression>)val_peek(1)); }
break;
case 62:
//#line 169 "../../src/parser/parser.y"
{ yyval = val_peek(1); }
break;
case 63:
//#line 170 "../../src/parser/parser.y"
{ yyval = new Indexing(getLine(),getColumn(),(Expression)val_peek(3),(Expression)val_peek(1)); }
break;
case 64:
//#line 171 "../../src/parser/parser.y"
{ yyval = new Cast(getLine(),getColumn(),(Type)val_peek(2),(Expression)val_peek(0)); }
break;
case 65:
//#line 172 "../../src/parser/parser.y"
{ yyval = new UnaryMinus(getLine(),getColumn(),(Expression)val_peek(0)); }
break;
case 66:
//#line 173 "../../src/parser/parser.y"
{ yyval = new UnaryNot(getLine(),getColumn(),(Expression)val_peek(0)); }
break;
case 67:
//#line 174 "../../src/parser/parser.y"
{ yyval = new FieldAccess(getLine(),getColumn(),(Expression)val_peek(2),(String)val_peek(0)); }
break;
case 68:
//#line 175 "../../src/parser/parser.y"
{ yyval = new Arithmetic(getLine(),getColumn(),(Expression)val_peek(2),"*",(Expression)val_peek(0)); }
break;
case 69:
//#line 176 "../../src/parser/parser.y"
{ yyval = new Arithmetic(getLine(),getColumn(),(Expression)val_peek(2),"+",(Expression)val_peek(0)); }
break;
case 70:
//#line 177 "../../src/parser/parser.y"
{ yyval = new Arithmetic(getLine(),getColumn(),(Expression)val_peek(2),"%",(Expression)val_peek(0)); }
break;
case 71:
//#line 178 "../../src/parser/parser.y"
{ yyval = new Arithmetic(getLine(),getColumn(),(Expression)val_peek(2),"/",(Expression)val_peek(0)); }
break;
case 72:
//#line 179 "../../src/parser/parser.y"
{ yyval = new Arithmetic(getLine(),getColumn(),(Expression)val_peek(2),"-",(Expression)val_peek(0)); }
break;
case 73:
//#line 180 "../../src/parser/parser.y"
{ yyval = new Comparision(getLine(),getColumn(),(Expression)val_peek(2),">",(Expression)val_peek(0)); }
break;
case 74:
//#line 181 "../../src/parser/parser.y"
{ yyval = new Comparision(getLine(),getColumn(),(Expression)val_peek(2),"<",(Expression)val_peek(0)); }
break;
case 75:
//#line 182 "../../src/parser/parser.y"
{ yyval = new Comparision(getLine(),getColumn(),(Expression)val_peek(2),"<=",(Expression)val_peek(0)); }
break;
case 76:
//#line 183 "../../src/parser/parser.y"
{ yyval = new Comparision(getLine(),getColumn(),(Expression)val_peek(2),">=",(Expression)val_peek(0)); }
break;
case 77:
//#line 184 "../../src/parser/parser.y"
{ yyval = new Comparision(getLine(),getColumn(),(Expression)val_peek(2),"==",(Expression)val_peek(0)); }
break;
case 78:
//#line 185 "../../src/parser/parser.y"
{ yyval = new Comparision(getLine(),getColumn(),(Expression)val_peek(2),"!=",(Expression)val_peek(0)); }
break;
case 79:
//#line 186 "../../src/parser/parser.y"
{ yyval = new Logical(getLine(),getColumn(),(Expression)val_peek(2),"||",(Expression)val_peek(0)); }
break;
case 80:
//#line 187 "../../src/parser/parser.y"
{ yyval = new Logical(getLine(),getColumn(),(Expression)val_peek(2),"&&",(Expression)val_peek(0)); }
break;
//#line 1074 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
