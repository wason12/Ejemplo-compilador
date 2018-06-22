/* The following code was generated by JFlex 1.4.1 on 15/05/18 19:46 */

// ************  C�digo a incluir ********************

package scanner;
import parser.Parser;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1
 * on 15/05/18 19:46 from the specification file
 * <tt>src/scanner/scanner.jflex</tt>
 */
public class Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\45\1\4\2\0\1\45\22\0\1\45\1\20\1\2\1\3"+
    "\1\0\1\14\1\22\1\11\3\14\1\10\1\14\1\10\1\6\1\14"+
    "\12\1\2\14\1\16\1\15\1\17\2\0\4\5\1\7\25\5\1\14"+
    "\1\12\1\14\1\0\1\5\1\0\1\42\1\13\1\41\1\31\1\32"+
    "\1\33\1\5\1\35\1\23\2\5\1\36\1\44\1\24\1\40\1\25"+
    "\1\5\1\30\1\37\1\27\1\26\1\43\1\34\3\5\1\14\1\21"+
    "\1\14\123\0\1\5\37\0\1\5\uff0e\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\1\2\3\1\4\2\5\1\1"+
    "\4\5\2\1\12\4\1\6\2\0\1\6\2\0\1\7"+
    "\1\10\1\11\1\12\1\13\1\14\1\4\1\15\12\4"+
    "\2\0\1\16\2\0\1\16\1\4\1\17\2\4\1\20"+
    "\7\4\2\0\1\21\1\22\4\4\1\23\2\4\1\24"+
    "\1\25\1\26\2\0\1\27\1\30\2\4\1\31\1\4"+
    "\1\32\1\33\1\34";

  private static int [] zzUnpackAction() {
    int [] result = new int[93];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\46\0\114\0\162\0\230\0\46\0\276\0\344"+
    "\0\46\0\u010a\0\u0130\0\u0156\0\u017c\0\u01a2\0\u01c8\0\u01ee"+
    "\0\u0214\0\u023a\0\u0260\0\u0286\0\u02ac\0\u02d2\0\u02f8\0\u031e"+
    "\0\u0344\0\u036a\0\u0390\0\u03b6\0\u03dc\0\344\0\u0402\0\u0428"+
    "\0\46\0\46\0\46\0\46\0\46\0\46\0\u044e\0\276"+
    "\0\u0474\0\u049a\0\u04c0\0\u04e6\0\u050c\0\u0532\0\u0558\0\u057e"+
    "\0\u05a4\0\u05ca\0\344\0\u05f0\0\46\0\u0616\0\u063c\0\u063c"+
    "\0\u0662\0\276\0\u0688\0\u06ae\0\276\0\u06d4\0\u06fa\0\u0720"+
    "\0\u0746\0\u076c\0\u0792\0\u07b8\0\u07de\0\u0804\0\46\0\46"+
    "\0\u082a\0\u0850\0\u0876\0\u089c\0\276\0\u08c2\0\u08e8\0\276"+
    "\0\276\0\276\0\u090e\0\u0934\0\276\0\276\0\u095a\0\u0980"+
    "\0\276\0\u09a6\0\276\0\276\0\276";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[93];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\7"+
    "\1\11\1\12\1\11\1\7\1\11\1\13\1\14\1\15"+
    "\1\16\1\17\1\20\1\21\1\7\1\22\2\7\1\23"+
    "\1\24\1\25\1\7\1\26\2\7\1\27\1\7\1\30"+
    "\1\7\1\31\1\32\1\6\47\0\1\3\4\0\1\33"+
    "\1\34\22\0\1\34\15\0\1\35\43\0\4\5\1\0"+
    "\41\5\1\0\1\7\3\0\1\7\1\0\1\7\3\0"+
    "\1\7\7\0\22\7\2\0\1\36\44\0\4\37\1\0"+
    "\5\37\1\40\33\37\15\0\1\41\45\0\1\42\45\0"+
    "\1\43\45\0\1\44\51\0\1\45\46\0\1\46\24\0"+
    "\1\7\3\0\1\7\1\0\1\7\3\0\1\7\7\0"+
    "\1\7\1\47\6\7\1\50\11\7\2\0\1\7\3\0"+
    "\1\7\1\0\1\7\3\0\1\7\7\0\5\7\1\51"+
    "\14\7\2\0\1\7\3\0\1\7\1\0\1\7\3\0"+
    "\1\7\7\0\7\7\1\52\12\7\2\0\1\7\3\0"+
    "\1\7\1\0\1\7\3\0\1\7\7\0\7\7\1\53"+
    "\5\7\1\54\4\7\2\0\1\7\3\0\1\7\1\0"+
    "\1\7\3\0\1\7\7\0\13\7\1\55\6\7\2\0"+
    "\1\7\3\0\1\7\1\0\1\7\3\0\1\7\7\0"+
    "\12\7\1\56\7\7\2\0\1\7\3\0\1\7\1\0"+
    "\1\7\3\0\1\7\7\0\4\7\1\57\15\7\2\0"+
    "\1\7\3\0\1\7\1\0\1\7\3\0\1\7\7\0"+
    "\12\7\1\60\7\7\2\0\1\7\3\0\1\7\1\0"+
    "\1\7\3\0\1\7\7\0\15\7\1\61\4\7\2\0"+
    "\1\7\3\0\1\7\1\0\1\7\3\0\1\7\7\0"+
    "\17\7\1\62\2\7\2\0\1\33\5\0\1\34\22\0"+
    "\1\34\14\0\1\36\6\0\1\63\37\0\1\64\54\0"+
    "\1\65\35\0\1\66\1\67\6\0\1\70\2\67\10\0"+
    "\1\67\2\0\2\67\2\0\1\67\13\0\1\7\3\0"+
    "\1\7\1\0\1\7\3\0\1\7\7\0\2\7\1\71"+
    "\1\7\1\72\15\7\2\0\1\7\3\0\1\7\1\0"+
    "\1\7\3\0\1\7\7\0\1\73\21\7\2\0\1\7"+
    "\3\0\1\7\1\0\1\7\3\0\1\7\7\0\4\7"+
    "\1\74\15\7\2\0\1\7\3\0\1\7\1\0\1\7"+
    "\3\0\1\7\7\0\10\7\1\75\11\7\2\0\1\7"+
    "\3\0\1\7\1\0\1\7\3\0\1\7\7\0\3\7"+
    "\1\76\16\7\2\0\1\7\3\0\1\7\1\0\1\7"+
    "\3\0\1\7\7\0\14\7\1\77\5\7\2\0\1\7"+
    "\3\0\1\7\1\0\1\7\3\0\1\7\7\0\1\100"+
    "\21\7\2\0\1\7\3\0\1\7\1\0\1\7\3\0"+
    "\1\7\7\0\5\7\1\101\14\7\2\0\1\7\3\0"+
    "\1\7\1\0\1\7\3\0\1\7\7\0\17\7\1\102"+
    "\2\7\2\0\1\7\3\0\1\7\1\0\1\7\3\0"+
    "\1\7\7\0\1\103\21\7\2\0\1\7\3\0\1\7"+
    "\1\0\1\7\3\0\1\7\7\0\1\104\21\7\1\0"+
    "\2\64\1\105\43\64\1\0\1\106\7\0\1\107\45\0"+
    "\1\110\35\0\1\7\3\0\1\7\1\0\1\7\3\0"+
    "\1\7\7\0\3\7\1\111\16\7\2\0\1\7\3\0"+
    "\1\7\1\0\1\7\3\0\1\7\7\0\1\7\1\112"+
    "\20\7\2\0\1\7\3\0\1\7\1\0\1\7\3\0"+
    "\1\7\7\0\3\7\1\113\16\7\2\0\1\7\3\0"+
    "\1\7\1\0\1\7\3\0\1\114\7\0\22\7\2\0"+
    "\1\7\3\0\1\7\1\0\1\7\3\0\1\7\7\0"+
    "\7\7\1\115\12\7\2\0\1\7\3\0\1\7\1\0"+
    "\1\7\3\0\1\7\7\0\13\7\1\116\6\7\2\0"+
    "\1\7\3\0\1\7\1\0\1\7\3\0\1\7\7\0"+
    "\3\7\1\117\16\7\2\0\1\7\3\0\1\7\1\0"+
    "\1\7\3\0\1\7\7\0\5\7\1\120\14\7\2\0"+
    "\1\7\3\0\1\7\1\0\1\7\3\0\1\7\7\0"+
    "\6\7\1\121\13\7\2\0\1\7\3\0\1\7\1\0"+
    "\1\7\3\0\1\7\7\0\1\7\1\122\20\7\3\0"+
    "\1\123\44\0\1\124\7\0\1\107\35\0\1\7\3\0"+
    "\1\7\1\0\1\7\3\0\1\7\7\0\4\7\1\125"+
    "\15\7\2\0\1\7\3\0\1\7\1\0\1\7\3\0"+
    "\1\7\7\0\4\7\1\126\15\7\2\0\1\7\3\0"+
    "\1\7\1\0\1\7\3\0\1\7\7\0\5\7\1\127"+
    "\14\7\2\0\1\7\3\0\1\7\1\0\1\7\3\0"+
    "\1\7\7\0\13\7\1\130\6\7\2\0\1\7\3\0"+
    "\1\7\1\0\1\7\3\0\1\7\7\0\7\7\1\131"+
    "\12\7\2\0\1\7\3\0\1\7\1\0\1\7\3\0"+
    "\1\7\7\0\16\7\1\132\3\7\3\0\1\6\54\0"+
    "\1\107\35\0\1\7\3\0\1\7\1\0\1\7\3\0"+
    "\1\7\7\0\1\7\1\133\20\7\2\0\1\7\3\0"+
    "\1\7\1\0\1\7\3\0\1\7\7\0\7\7\1\134"+
    "\12\7\2\0\1\7\3\0\1\7\1\0\1\7\3\0"+
    "\1\7\7\0\4\7\1\135\15\7\1\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2508];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\3\1\1\11\2\1\1\11\22\1\2\0"+
    "\1\1\2\0\6\11\14\1\2\0\1\11\2\0\15\1"+
    "\2\0\2\11\12\1\2\0\11\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[93];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
// ************  Atributos y m�todos ********************

// * Para acceder al n�mero de l�nea (yyline es package)
public int getLine() { 
	// * Flex empieza en cero
	return yyline+1;
}

// * Para acceder al n�mero de columna (yycolumn es package)
public int getColumn() { 
	// * Flex empieza en cero
	return yycolumn+1;
}

// * Valor semantico del token
private Object yylval;
public Object getYylval() {
	return this.yylval;
}



  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Scanner(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public Scanner(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 132) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzPushbackPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead < 0) {
      return true;
    }
    else {
      zzEndRead+= numRead;
      return false;
    }
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = zzPushbackPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public int yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = zzLexicalState;


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 13: 
          { this.yylval = "IF";
			return Parser.IF;
          }
        case 29: break;
        case 19: 
          { this.yylval = "ELSE";
			return Parser.ELSE;
          }
        case 30: break;
        case 22: 
          { this.yylval = "MAIN";
			return Parser.MAIN;
          }
        case 31: break;
        case 12: 
          { this.yylval = yytext();
	  return Parser.AND;
          }
        case 32: break;
        case 24: 
          { this.yylval = "WRITE";
			return Parser.WRITE;
          }
        case 33: break;
        case 11: 
          { this.yylval = yytext();
	  return Parser.OR;
          }
        case 34: break;
        case 28: 
          { this.yylval = "STRUCT";
			return Parser.STRUCT;
          }
        case 35: break;
        case 14: 
          { this.yylval = yycharat(1);
							return Parser.CHAR_CONSTANT;
          }
        case 36: break;
        case 26: 
          { this.yylval = "RETURN";
			return Parser.RETURN;
          }
        case 37: break;
        case 23: 
          { this.yylval = "READ";
			return Parser.READ;
          }
        case 38: break;
        case 8: 
          { this.yylval = yytext();
	  return Parser.LESS_EQUALS;
          }
        case 39: break;
        case 27: 
          { this.yylval = "DOUBLE";
			return Parser.DOUBLE;
          }
        case 40: break;
        case 5: 
          { this.yylval = yytext().charAt(0);
							return yycharat(0);
          }
        case 41: break;
        case 15: 
          { this.yylval = "INT";
			return Parser.INT;
          }
        case 42: break;
        case 9: 
          { this.yylval = yytext();
	  return Parser.GREATER_EQUALS;
          }
        case 43: break;
        case 16: 
          { this.yylval = "DEF";
			return Parser.DEF;
          }
        case 44: break;
        case 20: 
          { this.yylval = "CHAR";
			return Parser.CHAR;
          }
        case 45: break;
        case 25: 
          { this.yylval = "WHILE";
			return Parser.WHILE;
          }
        case 46: break;
        case 18: 
          { switch (yytext().charAt(2)) {
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
							return Parser.CHAR_CONSTANT;
          }
        case 47: break;
        case 21: 
          { this.yylval = "VOID";
			return Parser.VOID;
          }
        case 48: break;
        case 2: 
          { this.yylval = new Integer(yytext());
         			  return Parser.INT_CONSTANT;
          }
        case 49: break;
        case 17: 
          { String substring = yytext().substring(2, yytext().length()-1);
							this.yylval = (char) Integer.parseInt(substring);
							return Parser.CHAR_CONSTANT;
          }
        case 50: break;
        case 7: 
          { this.yylval = yytext();
	  return Parser.EQUALS;
          }
        case 51: break;
        case 10: 
          { this.yylval = yytext();
	  return Parser.DISTINCT;
          }
        case 52: break;
        case 6: 
          { this.yylval = Double.valueOf(yytext());
																return Parser.REAL_CONSTANT;
          }
        case 53: break;
        case 4: 
          { this.yylval = yytext();
					return Parser.ID;
          }
        case 54: break;
        case 1: 
          { System.err.println ("Lexical error at line " 	+ this.getLine() + " and column "+getColumn()+":\n\tUnknow character \'"+ yycharat(0)+"\'.");
          }
        case 55: break;
        case 3: 
          { 
          }
        case 56: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return 0; }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
