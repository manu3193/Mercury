package mercury.arm.assembler;

/**
 * Created by manzumbado on 21/03/16.
 */
public final class TokenTypes {

    public static final TokenTypes COMMENT = new TokenTypes("COMMENT");
    public static final TokenTypes OPERATOR = new TokenTypes("OPERATOR");
    public static final TokenTypes REGISTER = new TokenTypes("REGISTER");
    public static final TokenTypes SETLABEL = new TokenTypes("SETLABEL");
    public static final TokenTypes CALLLABEL = new TokenTypes("CALLLABEL");
    public static final TokenTypes COMA = new TokenTypes("COMA");
    public static final TokenTypes RIGHT_PAREN = new TokenTypes("RIGHT_PAREN");
    public static final TokenTypes LEFT_PAREN = new TokenTypes("LEFT_PAREN");
    public static final TokenTypes MINUS = new TokenTypes("MINUS");
    public static final TokenTypes ERROR = new TokenTypes("ERROR");
    public static final TokenTypes NUMERIC_DEC = new TokenTypes("NUMERIC_DEC");
    public static final TokenTypes NUMERIC_HEX = new TokenTypes("NUMERIC_HEX");
    public static final TokenTypes SHARP = new TokenTypes("SHARP");
    public static final TokenTypes MNEMONIC = new TokenTypes("MNEMONIC");
    public static final TokenTypes CONDITIONAL_MNEMONIC = new TokenTypes("CONDITIONAL_MNEMONIC");
    public static final TokenTypes SET_FLAGS = new TokenTypes("SET_FLAGS");
    public static final TokenTypes NEWLINE = new TokenTypes("NEWLINE");
    public static final TokenTypes COLON = new TokenTypes("COLON");


    private String descriptor;

    private TokenTypes() {
        descriptor = "generic";
    }

    private TokenTypes(String name) {
        descriptor = name;
    }


    public String toString() {
        return this.descriptor;
    }
}
