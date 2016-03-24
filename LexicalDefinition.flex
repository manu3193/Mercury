/*User Code Section*/
package mercury;

import mercury.arm.assembler.TokenTypes;
import mercury.arm.assembler.Token;
import mercury.arm.assembler.TokenList;
import java_cup.runtime.*;


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
	
	private Symbol symbol(int type) {
	    return new Symbol(type, sourceLine, yycolumn);
	}
        
        private Symbol symbol(int type, String value) {
        return new Symbol(type, sourceLine, yycolumn, value);
        }

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
	sourceLine = 0;
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
        SETLABEL= ((([a-zA-Z])+([0-9])*)\:?{NEWLINE})*
        CALLLABEL= ([a-zA-Z])+([0-9])*
	COMMENT=;.*

	MNEMONIC=AND|EOR|SUB|RSB|ADD|ADC|SBC|RSC|ORR|LSL|ASR|RRX|ROR|BIC|MUL
	CONDITIONAL_SUFFIX=EQ|NE|CS|HS|CC|LO|MI|PL|VS|VC|HI|LS|GE|LT|GT|LE|AL
	MNEMONIC_CONDITIONAL={MNEMONIC}{CONDITIONAL_SUFFIX}
	MNEMONIC_SET={MNEMONIC}S
	MNEMONIC_SET_CONDITIONAL={MNEMONIC_SET}{CONDITIONAL_SUFFIX}
	COMPARE_MNEMONIC=CMP|CMN
	COMPARE_MNEMONIC_CONDITIONAL={COMPARE_MNEMONIC}{CONDITIONAL_SUFFIX}
	MOVE_MNEMONIC=MOV|MVN
	MOVE_MNEMONIC_SET={MOVE_MNEMONIC}S
	MOVE_MNEMONIC_CONDITIONAL={MOVE_MNEMONIC}{CONDITIONAL_SUFFIX}
	MOVE_MNEMONIC_SET_CONDITIONAL={MOVE_MNEMONIC_SET}{CONDITIONAL_SUFFIX}
	MLA_MNEMONIC=MLA
        MLA_MNEMONIC_CONDITIONAL={MLA_MNEMONIC}{CONDITIONAL_SUFFIX}
	MLA_MNEMONIC_SET={MLA_MNEMONIC}S
	MLA_MNEMONIC_SET_CONDITIONAL={MLA_MNEMONIC_SET}{CONDITIONAL_SUFFIX}
	MEMORY_MNEMONIC=(STR|LDR)B?
	MEMORY_MNEMONIC_CONDITIONAL={MEMORY_MNEMONIC}{CONDITIONAL_SUFFIX}
	BRANCH_MNEMONIC=B{CONDITIONAL_SUFFIX}?

%%

/* Lexical Rules Section */
<YYINITIAL> {
   
    /* Si se encuentra un entero, se imprime, se regresa un token numero
        que representa un entero y el valor que se obtuvo de la cadena yytext
        al convertirla a entero. yytext es el token encontrado. */


    {NUMERIC_DEC}    { 
                      tokenList.add(new Token(tokenTypes.NUMERIC_DEC,yytext(),sourceLine,yycolumn));
		      //return symbol(sym.NUMERIC_DEC, yytext());  
		     }

    {NUMERIC_HEX}      { 
                        tokenList.add(new Token(tokenTypes.NUMERIC_HEX,yytext(),sourceLine,yycolumn));
                        //return symbol(sym.NUMERIC_HEX, yytext());  
                       }
                     
    {REGISTER}      { 
                      tokenList.add(new Token(tokenTypes.REGISTER,yytext(),sourceLine,yycolumn));
                      //return symbol(sym.REGISTER, yytext());  
                    }
		      
     {COMMENT}      { 
                      tokenList.add(new Token(tokenTypes.COMMENT,yytext(),sourceLine,yycolumn));
                      //return symbol(sym.COMMENT, yytext());  
                    }

     {SETLABEL}      {
                      tokenList.add(new Token(tokenTypes.SETLABEL,yytext(),sourceLine,yycolumn));
                      //return symbol(sym.SETLABEL, yytext());  
                     }

    "["              {
                      tokenList.add(new Token(tokenTypes.LEFT_PAREN,yytext(),sourceLine,yycolumn));
                      //return symbol(sym.LEFT_PAREN, yytext());  
                     }

    "]"              {
                      tokenList.add(new Token(tokenTypes.RIGHT_PAREN,yytext(),sourceLine,yycolumn));
                      //return symbol(sym.RIGHT_PAREN, yytext());  
		     }

    "-"              {
                      tokenList.add(new Token(tokenTypes.MINUS,yytext(),sourceLine,yycolumn));
                      //return symbol(sym.MINUS, yytext());  
		     }

    ","              {
                      tokenList.add(new Token(tokenTypes.COMA,yytext(),sourceLine,yycolumn));
                      //return symbol(sym.COMA, yytext()); 
		     }

    "#"              {
                      tokenList.add(new Token(tokenTypes.SHARP,yytext(),sourceLine,yycolumn));
                      //return symbol(sym.SHARP, yytext()); 
		     }

    ":"              {
                      tokenList.add(new Token(tokenTypes.COLON,yytext(),sourceLine,yycolumn));
                      //return symbol(sym.COLON, yytext()); 
		     }
    

    {MNEMONIC}        {
                      tokenList.add(new Token(tokenTypes.MNEMONIC,yytext(),sourceLine,yycolumn));
                      //return symbol(sym.MNEMONIC, yytext()); 
                      }
    
    {MNEMONIC_CONDITIONAL}       {
                     		  tokenList.add(new Token(tokenTypes.MNEMONIC_CONDITIONAL,yytext(),sourceLine,yycolumn));
                                  //return symbol(sym.MNEMONIC_CONDITIONAL, yytext()); 
                   	         }

    {MNEMONIC_SET}     		 {
                     		  tokenList.add(new Token(tokenTypes.MNEMONIC_SET,yytext(),sourceLine,yycolumn));
                                  //return symbol(sym.MNEMONIC_SET, yytext()); 
                   	         }

    {MNEMONIC_SET_CONDITIONAL}   {
                     		  tokenList.add(new Token(tokenTypes.MNEMONIC_SET_CONDITIONAL,yytext(),sourceLine,yycolumn));
                                  //return symbol(sym.MNEMONIC_SET_CONDITIONAL, yytext()); 
                   	         }

    {COMPARE_MNEMONIC}           {
                     		  tokenList.add(new Token(tokenTypes.COMPARE_MNEMONIC,yytext(),sourceLine,yycolumn));
                                  //return symbol(sym.COMPARE_MNEMONIC, yytext()); 
                   	         }

    {COMPARE_MNEMONIC_CONDITIONAL}           {
                     		              tokenList.add(new Token(tokenTypes.COMPARE_MNEMONIC_CONDITIONAL,yytext(),sourceLine,yycolumn));
                                              //return symbol(sym.COMPARE_MNEMONIC_CONDITIONAL, yytext()); 
                   	                     }

    {MOVE_MNEMONIC}           {
                               tokenList.add(new Token(tokenTypes.MOVE_MNEMONIC,yytext(),sourceLine,yycolumn));
                               //return symbol(sym.MOVE_MNEMONIC, yytext()); 
                              }

    {MOVE_MNEMONIC_SET}   {
                           tokenList.add(new Token(tokenTypes.MOVE_MNEMONIC_SET,yytext(),sourceLine,yycolumn));
                           //return symbol(sym.MOVE_MNEMONIC_SET, yytext()); 
                          }

    {MOVE_MNEMONIC_CONDITIONAL}       {
                                       tokenList.add(new Token(tokenTypes.MOVE_MNEMONIC_CONDITIONAL,yytext(),sourceLine,yycolumn));
                                       //return symbol(sym.MOVE_MNEMONIC_CONDITIONAL, yytext()); 
                                      }

    {MOVE_MNEMONIC_SET_CONDITIONAL}   {
                                       tokenList.add(new Token(tokenTypes.MOVE_MNEMONIC_SET_CONDITIONAL,yytext(),sourceLine,yycolumn));
                                       //return symbol(sym.MOVE_MNEMONIC_SET_CONDITIONAL, yytext()); 
                                      }

    {MLA_MNEMONIC}          {
                             tokenList.add(new Token(tokenTypes.MLA_MNEMONIC,yytext(),sourceLine,yycolumn));
                             //return symbol(sym.MLA_MNEMONIC, yytext()); 
                            }

    {MLA_MNEMONIC_SET}      {
                             tokenList.add(new Token(tokenTypes.MLA_MNEMONIC_SET,yytext(),sourceLine,yycolumn));
                             //return symbol(sym.MLA_MNEMONIC_SET, yytext()); 
                            }

    {MLA_MNEMONIC_CONDITIONAL}       {
                                      tokenList.add(new Token(tokenTypes.MLA_MNEMONIC_CONDITIONAL,yytext(),sourceLine,yycolumn));
                                      //return symbol(sym.MLA_MNEMONIC_CONDITIONAL, yytext()); 
                                     }

    {MLA_MNEMONIC_SET_CONDITIONAL}   {
                                      tokenList.add(new Token(tokenTypes.MLA_MNEMONIC_SET_CONDITIONAL,yytext(),sourceLine,yycolumn));
                                      //return symbol(sym.MLA_MNEMONIC_SET_CONDITIONAL, yytext()); 
                                     }

    {MEMORY_MNEMONIC}      {
                             tokenList.add(new Token(tokenTypes.MEMORY_MNEMONIC,yytext(),sourceLine,yycolumn));
                             //return symbol(sym.MEMORY_MNEMONIC, yytext()); 
                           }

    {MEMORY_MNEMONIC_CONDITIONAL}       {
                                         tokenList.add(new Token(tokenTypes.MEMORY_MNEMONIC_CONDITIONAL,yytext(),sourceLine,yycolumn)); 
                                         //return symbol(sym.MEMORY_MNEMONIC_CONDITIONAL, yytext()); 
                                        }

    {BRANCH_MNEMONIC}      {
                            tokenList.add(new Token(tokenTypes.BRANCH_MNEMONIC,yytext(),sourceLine,yycolumn));
                            //return symbol(sym.BRANCH_MNEMONIC, yytext()); 
                           }
 
    /* No hace nada si encuentra el espacio en blanco */
    {SPACE}       { /* ignora el espacio */ }

    {NEWLINE} 	  {
	           tokenList.add(new Token(tokenTypes.NEWLINE,yytext(),sourceLine,yycolumn));
                   //return symbol(sym.NEWLINE,yytext()); 
                  }

    {CALLLABEL}   {
                   tokenList.add(new Token(tokenTypes.CALLED_LABEL,yytext(),sourceLine,yycolumn));
                   //return symbol(sym.CALLED_LABEL, yytext()); 
                  }
}

	 


    [^]         {
                  errorList.add(new ErrorMessage(sourceLine,yycolumn," Este token no es v'alido: "+yytext()));
               	}
