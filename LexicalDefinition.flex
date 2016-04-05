/*User Code Section*/
package mercury;

import mercury.arm.assembler.TokenTypes;
import mercury.arm.assembler.Token;
import mercury.arm.assembler.TokenList;
import mercury.LexicalAnalysis;
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
%cup
%cupdebug

//Activate line and column counter
%column
%line
%ignorecase



//Beginning of java code
%{

	private TokenList tokenList;
	private ErrorList errorList;
	private TokenTypes tokenTypes;
	
	private Symbol symbol(int type) {
	    return new Symbol(type, yyline, yycolumn);
	}
        
        private Symbol symbol(int type, String value) {
        return new Symbol(type, yyline, yycolumn, value);
        }

	public ErrorList getErrorList(){
            return errorList;
	}

	public TokenList getTokens(){
	   return tokenList;
	}

	
%}

%init{
        tokenList = new TokenList();
        errorList = new ErrorList();
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
        SETLABEL= ((([a-zA-Z])+([0-9])*)\:)*
        CALLLABEL= ([a-zA-Z])+([0-9])*
	COMMENT= ;(\S|[ \t])*\n
	MNEMONIC=AND|EOR|SUB|RSB|ADD|ADC|SBC|RSC|ORR|BIC|MVN
	CONDITIONAL_SUFFIX=EQ|NE|CS|HS|CC|LO|MI|PL|VS|VC|HI|LS|GE|LT|GT|LE|AL
	MNEMONIC_CONDITIONAL={MNEMONIC}{CONDITIONAL_SUFFIX}
	MNEMONIC_SET={MNEMONIC}S
	MNEMONIC_SET_CONDITIONAL={MNEMONIC_SET}{CONDITIONAL_SUFFIX}
	SHIFT_MNEMONIC=LSL|ASR|ROR
	SHIFT_MNEMONIC_SET={SHIFT_MNEMONIC}S
    SHIFT_MNEMONIC_CONDITIONAL={SHIFT_MNEMONIC}{CONDITIONAL_SUFFIX}
    SHIFT_MNEMONIC_SET_CONDITIONAL={SHIFT_MNEMONIC_SET}{CONDITIONAL_SUFFIX}
	RRX_MNEMONIC=RRX
	RRX_MNEMONIC_SET={RRX_MNEMONIC}S
    RRX_MNEMONIC_CONDITIONAL={RRX_MNEMONIC}{CONDITIONAL_SUFFIX}
    RRX_MNEMONIC_SET_CONDITIONAL={RRX_MNEMONIC_SET}{CONDITIONAL_SUFFIX}
	COMPARE_MNEMONIC=CMP|CMN
	COMPARE_MNEMONIC_CONDITIONAL={COMPARE_MNEMONIC}{CONDITIONAL_SUFFIX}
	MOVE_MNEMONIC=MOV
	MOVE_MNEMONIC_SET={MOVE_MNEMONIC}S
	MOVE_MNEMONIC_CONDITIONAL={MOVE_MNEMONIC}{CONDITIONAL_SUFFIX}
	MOVE_MNEMONIC_SET_CONDITIONAL={MOVE_MNEMONIC_SET}{CONDITIONAL_SUFFIX}
	MUL_MNEMONIC=MUL
	MUL_MNEMONIC_CONDITIONAL={MUL_MNEMONIC}{CONDITIONAL_SUFFIX}
    MUL_MNEMONIC_SET={MUL_MNEMONIC}S
    MUL_MNEMONIC_SET_CONDITIONAL={MUL_MNEMONIC_SET}{CONDITIONAL_SUFFIX}
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
                      tokenList.add(new Token(tokenTypes.NUMERIC_DEC,yytext(),yyline,yycolumn));
                      System.out.println(yytext());
		              return symbol(sym.num, yytext());
		     }

    {NUMERIC_HEX}      { 
                        tokenList.add(new Token(tokenTypes.NUMERIC_HEX,yytext(),yyline,yycolumn));
                        System.out.println(yytext());
                        return symbol(sym.hex, yytext());
                       }
                     
    {REGISTER}      { 
                      tokenList.add(new Token(tokenTypes.REGISTER,yytext(),yyline,yycolumn));
                      System.out.println(yytext());
                      return symbol(sym.register, yytext());
                    }
		      
     {COMMENT}      { 
                      tokenList.add(new Token(tokenTypes.COMMENT,yytext(),yyline,yycolumn));
                      System.out.println(yytext());
                      return symbol(sym.comment, yytext());
                    }

     {SETLABEL}      {
                      tokenList.add(new Token(tokenTypes.SETLABEL,yytext(),yyline,yycolumn));
                      System.out.println(yytext());
                      return symbol(sym.setlabel, yytext());
                     }

    "["              {
                      tokenList.add(new Token(tokenTypes.LEFT_PAREN,yytext(),yyline,yycolumn));
                      System.out.println(yytext());
                      return symbol(sym.leftpar, yytext());
                     }

    "]"              {
                      tokenList.add(new Token(tokenTypes.RIGHT_PAREN,yytext(),yyline,yycolumn));
                      System.out.println(yytext());
                      return symbol(sym.rightpar, yytext());
		     }

    ","              {
                      tokenList.add(new Token(tokenTypes.COMA,yytext(),yyline,yycolumn));
                      System.out.println(yytext());
                      return symbol(sym.comma, yytext());
		     }

    "#"              {
                      tokenList.add(new Token(tokenTypes.SHARP,yytext(),yyline,yycolumn));
                      System.out.println(yytext());
                      return symbol(sym.sharp, yytext());
		     }
    {MNEMONIC}        {
                      tokenList.add(new Token(tokenTypes.MNEMONIC,yytext(),yyline,yycolumn));
                      System.out.println(yytext());
                      return symbol(sym.mnemonic, yytext());
                      }
    
    {MNEMONIC_CONDITIONAL}       {
                     		  tokenList.add(new Token(tokenTypes.MNEMONIC_CONDITIONAL,yytext(),yyline,yycolumn));
                     		  System.out.println(yytext());
                                  return symbol(sym.conditionalmnemonic, yytext());
                   	         }

    {MNEMONIC_SET}     		 {
                     		  tokenList.add(new Token(tokenTypes.MNEMONIC_SET,yytext(),yyline,yycolumn));
                     		  System.out.println(yytext());
                                  return symbol(sym.mnemonicset, yytext());
                   	         }

    {MNEMONIC_SET_CONDITIONAL}   {
                     		  tokenList.add(new Token(tokenTypes.MNEMONIC_SET_CONDITIONAL,yytext(),yyline,yycolumn));
                     		  System.out.println(yytext());
                                  return symbol(sym.mnemonicsetconditional, yytext());
                   	         }

    {COMPARE_MNEMONIC}           {
                     		  tokenList.add(new Token(tokenTypes.COMPARE_MNEMONIC,yytext(),yyline,yycolumn));
                     		  System.out.println(yytext());
                                  return symbol(sym.compare, yytext());
                   	         }

    {COMPARE_MNEMONIC_CONDITIONAL}           {
                     		              tokenList.add(new Token(tokenTypes.COMPARE_MNEMONIC_CONDITIONAL,yytext(),yyline,yycolumn));
                     		              System.out.println(yytext());
                                              return symbol(sym.compareconditional, yytext());
                   	                     }

    {MOVE_MNEMONIC}           {
                               tokenList.add(new Token(tokenTypes.MOVE_MNEMONIC,yytext(),yyline,yycolumn));
                               System.out.println(yytext());
                               return symbol(sym.move, yytext());
                              }

    {MOVE_MNEMONIC_SET}   {
                           tokenList.add(new Token(tokenTypes.MOVE_MNEMONIC_SET,yytext(),yyline,yycolumn));
                           System.out.println(yytext());
                           return symbol(sym.moveset, yytext());
                          }

    {MOVE_MNEMONIC_CONDITIONAL}       {
                                       tokenList.add(new Token(tokenTypes.MOVE_MNEMONIC_CONDITIONAL,yytext(),yyline,yycolumn));
                                       System.out.println(yytext());
                                       return symbol(sym.conditionalmove, yytext());
                                      }

    {MOVE_MNEMONIC_SET_CONDITIONAL}   {
                                       tokenList.add(new Token(tokenTypes.MOVE_MNEMONIC_SET_CONDITIONAL,yytext(),yyline,yycolumn));
                                       System.out.println(yytext());
                                       return symbol(sym.movesetconditional, yytext());
                                      }

     {RRX_MNEMONIC}           {
                                   tokenList.add(new Token(tokenTypes.MOVE_MNEMONIC,yytext(),yyline,yycolumn));
                                   System.out.println(yytext());
                                   return symbol(sym.rrx, yytext());
                                  }

     {RRX_MNEMONIC_SET}   {
                               tokenList.add(new Token(tokenTypes.MOVE_MNEMONIC_SET,yytext(),yyline,yycolumn));
                               System.out.println(yytext());
                               return symbol(sym.rrxset, yytext());
                              }

     {RRX_MNEMONIC_CONDITIONAL}       {
                                           tokenList.add(new Token(tokenTypes.MOVE_MNEMONIC_CONDITIONAL,yytext(),yyline,yycolumn));
                                           System.out.println(yytext());
                                           return symbol(sym.conditionalrrx, yytext());
                                          }

     {RRX_MNEMONIC_SET_CONDITIONAL}   {
                                           tokenList.add(new Token(tokenTypes.MOVE_MNEMONIC_SET_CONDITIONAL,yytext(),yyline,yycolumn));
                                           System.out.println(yytext());
                                           return symbol(sym.rrxsetconditional, yytext());
                                          }

    {MLA_MNEMONIC}          {
                             tokenList.add(new Token(tokenTypes.MLA_MNEMONIC,yytext(),yyline,yycolumn));
                             System.out.println(yytext());
                             return symbol(sym.mla, yytext());
                            }

    {MLA_MNEMONIC_SET}      {
                             tokenList.add(new Token(tokenTypes.MLA_MNEMONIC_SET,yytext(),yyline,yycolumn));
                             System.out.println(yytext());
                             return symbol(sym.mlaset, yytext());
                            }

    {MLA_MNEMONIC_CONDITIONAL}       {
                                      tokenList.add(new Token(tokenTypes.MLA_MNEMONIC_CONDITIONAL,yytext(),yyline,yycolumn));
                                      System.out.println(yytext());
                                      return symbol(sym.mlaconditional, yytext());
                                     }

    {MLA_MNEMONIC_SET_CONDITIONAL}   {
                                      tokenList.add(new Token(tokenTypes.MLA_MNEMONIC_SET_CONDITIONAL,yytext(),yyline,yycolumn));
                                      System.out.println(yytext());
                                      return symbol(sym.mlasetconditional, yytext());
                                     }
    {SHIFT_MNEMONIC}           {
                                       tokenList.add(new Token(tokenTypes.MOVE_MNEMONIC,yytext(),yyline,yycolumn));
                                       System.out.println(yytext());
                                       return symbol(sym.shift, yytext());
                                      }

         {SHIFT_MNEMONIC_SET}   {
                                   tokenList.add(new Token(tokenTypes.MOVE_MNEMONIC_SET,yytext(),yyline,yycolumn));
                                   System.out.println(yytext());
                                   return symbol(sym.shiftset, yytext());
                                  }

         {SHIFT_MNEMONIC_CONDITIONAL}       {
                                               tokenList.add(new Token(tokenTypes.MOVE_MNEMONIC_CONDITIONAL,yytext(),yyline,yycolumn));
                                               System.out.println(yytext());
                                               return symbol(sym.conditionalshift, yytext());
                                              }

         {SHIFT_MNEMONIC_SET_CONDITIONAL}   {
                                               tokenList.add(new Token(tokenTypes.MOVE_MNEMONIC_SET_CONDITIONAL,yytext(),yyline,yycolumn));
                                               System.out.println(yytext());
                                               return symbol(sym.shiftsetconditional, yytext());
                                              }
    {MUL_MNEMONIC}          {
                                 //tokenList.add(new Token(tokenTypes.MLA_MNEMONIC,yytext(),yyline,yycolumn));
                                 System.out.println(yytext());
                                 return symbol(sym.mul, yytext());
                                }

        {MUL_MNEMONIC_SET}      {
                                 //tokenList.add(new Token(tokenTypes.MLA_MNEMONIC_SET,yytext(),yyline,yycolumn));
                                 System.out.println(yytext());
                                 return symbol(sym.mulset, yytext());
                                }

        {MUL_MNEMONIC_CONDITIONAL}       {
                                          //tokenList.add(new Token(tokenTypes.MLA_MNEMONIC_CONDITIONAL,yytext(),yyline,yycolumn));
                                          System.out.println(yytext());
                                          return symbol(sym.mulconditional, yytext());
                                         }

        {MUL_MNEMONIC_SET_CONDITIONAL}   {
                                          //tokenList.add(new Token(tokenTypes.MLA_MNEMONIC_SET_CONDITIONAL,yytext(),yyline,yycolumn));
                                          System.out.println(yytext());
                                          return symbol(sym.mulsetconditional, yytext());
                                         }
    {MEMORY_MNEMONIC}      {
                             tokenList.add(new Token(tokenTypes.MEMORY_MNEMONIC,yytext(),yyline,yycolumn));
                             System.out.println(yytext());
                             return symbol(sym.memorymnemonic, yytext());
                           }

    {MEMORY_MNEMONIC_CONDITIONAL}       {
                                         tokenList.add(new Token(tokenTypes.MEMORY_MNEMONIC_CONDITIONAL,yytext(),yyline,yycolumn));
                                         System.out.println(yytext());
                                         return symbol(sym.memorymnemonicconditional, yytext());
                                        }

    {BRANCH_MNEMONIC}      {
                            tokenList.add(new Token(tokenTypes.BRANCH_MNEMONIC,yytext(),yyline,yycolumn));
                            System.out.println(yytext());
                            return symbol(sym.branch, yytext());
                           }
 
    /* No hace nada si encuentra el espacio en blanco */
    {SPACE}       { /* ignora el espacio */ }

    {NEWLINE} 	  {
	           tokenList.add(new Token(tokenTypes.NEWLINE,yytext(),yyline,yycolumn));
	           System.out.println(yytext());
                   return symbol(sym.newline,yytext());
                  }

    {CALLLABEL}   {
                   tokenList.add(new Token(tokenTypes.CALLED_LABEL,yytext(),yyline,yycolumn));
                   System.out.println(yytext());
                   return symbol(sym.calllabel, yytext());
                  }
}

	 


    [^]         {
                  errorList.add(new ErrorMessage(yyline,yycolumn," This is not a valir token: "+yytext(), ErrorType.LEXICAL_ERROR));
               	}
