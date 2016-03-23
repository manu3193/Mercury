/*User Code Section*/
package mercury;

import mercury.arm.assembler.TokenTypes;
import mercury.arm.assembler.Token;
import mercury.arm.assembler.TokenList;
import java_cup.runtime.*;



//Class to represent internal parser tokens
class Yytoken {
    Yytoken (String type, String value, int line, int pos){
        //String of the token
        this.value = value;
        //Type of the token
        this.type = type;
        //Position of token in the given line
        this.pos = pos;
    }
    //Atributes
    public String value;
    public String type;
    public int pos;
    public int line;
}



/* JFlex options and declarations */
%% //Beginning of options

//Set the name of the class to LexicalAnalysis
%class LexicalAnalysis


//Unicode support
%unicode
%public



//Activate compatibility with cup sintaX analizer
//%cup

//Activate line and column counter
%column
%line
%ignorecase

//Beginning of java code
%{

	private TokenList tokenList;
	private ErrorList errorList;
	private TokenTypes tokenTypes;
	private int sourceLine;

	public ErrorList getErrors(){
            return errorList;
	}

	public TokenList getTokens(){
	   return tokenList;
	}

	public void setLineNumber(int num){
	   this.sourceLine = num;
	}
	
%}

%init{
        tokenList = new TokenList();
        errorList = new ErrorList();
	sourceLine =0;
%init}

//End of
%eof{
      // System.exit(0);
%eof}

//End of options

//Regular expressions
//Declarations

        ALPHA_EXP=[a-zA-Z]
        NUMERIC_EXP=[0-9]
        ALPHANUMERIC_EXP={ALPHA_EXP}|{NUMERIC_EXP}
        NUMERIC_DEC={NUMERIC_EXP}+
	NUMERIC_HEX=0X({NUMERIC_EXP}|[A-F])+
	REGISTER=[R]([2-9]|[1][0-5]|1|0)
        SPACE=[ \t]
        NEWLINE=\n|\r|\r\n
	DELIMITER={SPACE}|{NEWLINE}
        LABEL={ALPHA_EXP}({ALPHANUMERIC_EXP})*{NEWLINE}
	COMMENT=;.*
	
	
//End of declarations

%%

/* Lexical Rules Section */
<YYINITIAL> {
   
    /* Si se encuentra un entero, se imprime, se regresa un token numero
        que representa un entero y el valor que se obtuvo de la cadena yytext
        al convertirla a entero. yytext es el token encontrado. */


    {NUMERIC_DEC}      { 
                      tokenList.add(new Token(tokenTypes.NUMERIC_DEC,yytext(),sourceLine,yycolumn));}

    {NUMERIC_HEX}      { 
                      tokenList.add(new Token(tokenTypes.NUMERIC_HEX,yytext(),sourceLine,yycolumn));}
    
    {REGISTER}      { 
                      tokenList.add(new Token(tokenTypes.REGISTER,yytext(),sourceLine,yycolumn));}

     {COMMENT}      { 
                      tokenList.add(new Token(tokenTypes.COMMENT,yytext(),sourceLine,yycolumn));}

     {LABEL}      { 
                      tokenList.add(new Token(tokenTypes.LABEL,yytext(),sourceLine,yycolumn));}


    "["                {
                      tokenList.add(new Token(tokenTypes.LEFT_PAREN,yytext(),sourceLine,yycolumn));
			}

    "]"                {
                      tokenList.add(new Token(tokenTypes.RIGHT_PAREN,yytext(),sourceLine,yycolumn));
			}

    "-"                {
                      tokenList.add(new Token(tokenTypes.MINUS,yytext(),sourceLine,yycolumn));
			}

    ","                {
                      tokenList.add(new Token(tokenTypes.COMA,yytext(),sourceLine,yycolumn));
			}
    "#"                {
                      tokenList.add(new Token(tokenTypes.SHARP,yytext(),sourceLine,yycolumn));
			}
    "AND"                {
                      tokenList.add(new Token(tokenTypes.MNEMONIC,yytext(),sourceLine,yycolumn));
			}
    "EOR"                {
                      tokenList.add(new Token(tokenTypes.MNEMONIC,yytext(),sourceLine,yycolumn));
			}

    "SUB"                {
                      tokenList.add(new Token(tokenTypes.MNEMONIC,yytext(),sourceLine,yycolumn));
			}

    "RSB"                {
                      tokenList.add(new Token(tokenTypes.MNEMONIC,yytext(),sourceLine,yycolumn));
			}
    "ADD"                {
                      tokenList.add(new Token(tokenTypes.MNEMONIC,yytext(),sourceLine,yycolumn));
			}
    "ADC"                {
                      tokenList.add(new Token(tokenTypes.MNEMONIC,yytext(),sourceLine,yycolumn));
			}
    "SBC"                {
                      tokenList.add(new Token(tokenTypes.MNEMONIC,yytext(),sourceLine,yycolumn));
			}

    "RSC"                {
                      tokenList.add(new Token(tokenTypes.MNEMONIC,yytext(),sourceLine,yycolumn));
			}

    "ORR"                {
                      tokenList.add(new Token(tokenTypes.MNEMONIC,yytext(),sourceLine,yycolumn));
			}
    "MOV"                {
                      tokenList.add(new Token(tokenTypes.MNEMONIC,yytext(),sourceLine,yycolumn));
			}
    "LSL"                {
                      tokenList.add(new Token(tokenTypes.MNEMONIC,yytext(),sourceLine,yycolumn));
			}
    "ASR"                {
                      tokenList.add(new Token(tokenTypes.MNEMONIC,yytext(),sourceLine,yycolumn));
			}

    "RRX"                {
                      tokenList.add(new Token(tokenTypes.MNEMONIC,yytext(),sourceLine,yycolumn));
			}

    "ROR"                {
                      tokenList.add(new Token(tokenTypes.MNEMONIC,yytext(),sourceLine,yycolumn));
			}
    "BIC"                {
                      tokenList.add(new Token(tokenTypes.MNEMONIC,yytext(),sourceLine,yycolumn));
			}
    "MVN"                {
                      tokenList.add(new Token(tokenTypes.MNEMONIC,yytext(),sourceLine,yycolumn));
			}
    "CMP"                {
                      tokenList.add(new Token(tokenTypes.MNEMONIC,yytext(),sourceLine,yycolumn));
			}

    "CMN"                {
                      tokenList.add(new Token(tokenTypes.MNEMONIC,yytext(),sourceLine,yycolumn));
			}

    "B"                {
                      tokenList.add(new Token(tokenTypes.MNEMONIC,yytext(),sourceLine,yycolumn));
			}
    "MUL"                {
                      tokenList.add(new Token(tokenTypes.MNEMONIC,yytext(),sourceLine,yycolumn));
			}
    "MLA"                {
                      tokenList.add(new Token(tokenTypes.MNEMONIC,yytext(),sourceLine,yycolumn));
			}
    "STR"                {
                      tokenList.add(new Token(tokenTypes.MNEMONIC,yytext(),sourceLine,yycolumn));
			}

    "LDR"                {
                      tokenList.add(new Token(tokenTypes.MNEMONIC,yytext(),sourceLine,yycolumn));
			}

    "STRB"                {
                      tokenList.add(new Token(tokenTypes.MNEMONIC,yytext(),sourceLine,yycolumn));
			}
    "LDRB"                {
                      tokenList.add(new Token(tokenTypes.MNEMONIC,yytext(),sourceLine,yycolumn));
			}
    "EQ"                {
                      tokenList.add(new Token(tokenTypes.CONDITIONAL_MNEMONIC,yytext(),sourceLine,yycolumn));
			}
    "NE"                {
                      tokenList.add(new Token(tokenTypes.CONDITIONAL_MNEMONIC,yytext(),sourceLine,yycolumn));
			}
    "CS"                {
                      tokenList.add(new Token(tokenTypes.CONDITIONAL_MNEMONIC,yytext(),sourceLine,yycolumn));
			}
    "HS"                {
                      tokenList.add(new Token(tokenTypes.CONDITIONAL_MNEMONIC,yytext(),sourceLine,yycolumn));
			}

    "CC"                {
                      tokenList.add(new Token(tokenTypes.CONDITIONAL_MNEMONIC,yytext(),sourceLine,yycolumn));
			}

    "LO"                {
                      tokenList.add(new Token(tokenTypes.CONDITIONAL_MNEMONIC,yytext(),sourceLine,yycolumn));
			}
    "MI"                {
                      tokenList.add(new Token(tokenTypes.CONDITIONAL_MNEMONIC,yytext(),sourceLine,yycolumn));
			}
    "PL"                {
                      tokenList.add(new Token(tokenTypes.CONDITIONAL_MNEMONIC,yytext(),sourceLine,yycolumn));
			}
    "VS"                {
                      tokenList.add(new Token(tokenTypes.CONDITIONAL_MNEMONIC,yytext(),sourceLine,yycolumn));
			}

    "VC"                {
                      tokenList.add(new Token(tokenTypes.CONDITIONAL_MNEMONIC,yytext(),sourceLine,yycolumn));
			}

    "HI"                {
                      tokenList.add(new Token(tokenTypes.CONDITIONAL_MNEMONIC,yytext(),sourceLine,yycolumn));
			}
    "LS"                {
                      tokenList.add(new Token(tokenTypes.CONDITIONAL_MNEMONIC,yytext(),sourceLine,yycolumn));
			}
    "GE"                {
                      tokenList.add(new Token(tokenTypes.CONDITIONAL_MNEMONIC,yytext(),sourceLine,yycolumn));
			}
    "LT"                {
                      tokenList.add(new Token(tokenTypes.CONDITIONAL_MNEMONIC,yytext(),sourceLine,yycolumn));
			}

    "LE"                {
                      tokenList.add(new Token(tokenTypes.CONDITIONAL_MNEMONIC,yytext(),sourceLine,yycolumn));
			}

    "AL"                {
                      tokenList.add(new Token(tokenTypes.CONDITIONAL_MNEMONIC,yytext(),sourceLine,yycolumn));
			}
    "GT"                {
                      tokenList.add(new Token(tokenTypes.CONDITIONAL_MNEMONIC,yytext(),sourceLine,yycolumn));
			}
    "S"                {
                      tokenList.add(new Token(tokenTypes.SET_FLAGS,yytext(),sourceLine,yycolumn));
			}

    /* No hace nada si encuentra el espacio en blanco */
    {SPACE}       { /* ignora el espacio */ }

    {NEWLINE} 	  {tokenList.add(new Token(tokenTypes.NEWLINE,yytext(),sourceLine,yycolumn));
}
}

	 


[^]                    { errorList.add(new ErrorMessage(sourceLine,yycolumn," Este token no es v'alido: "+yytext())); 

			}
