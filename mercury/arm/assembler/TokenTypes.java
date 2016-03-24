package mercury.arm.assembler;

/**
 * Created by manzumbado on 21/03/16.
 */
public final class TokenTypes {

    public static final TokenTypes COMMENT = new TokenTypes("COMMENT");
    public static final TokenTypes REGISTER = new TokenTypes("REGISTER");
    public static final TokenTypes SETLABEL = new TokenTypes("SETLABEL");
    public static final TokenTypes CALLED_LABEL = new TokenTypes("CALLED_LABEL");
    public static final TokenTypes COMA = new TokenTypes("COMA");
    public static final TokenTypes RIGHT_PAREN = new TokenTypes("RIGHT_PAREN");
    public static final TokenTypes LEFT_PAREN = new TokenTypes("LEFT_PAREN");
    public static final TokenTypes MINUS = new TokenTypes("MINUS");
    public static final TokenTypes ERROR = new TokenTypes("ERROR");
    public static final TokenTypes NUMERIC_DEC = new TokenTypes("NUMERIC_DEC");
    public static final TokenTypes NUMERIC_HEX = new TokenTypes("NUMERIC_HEX");
    public static final TokenTypes SHARP = new TokenTypes("SHARP");
    public static final TokenTypes MNEMONIC = new TokenTypes("MNEMONIC");
    public static final TokenTypes MNEMONIC_CONDITIONAL = new TokenTypes("MNEMONIC_CONDITIONAL");
    public static final TokenTypes MNEMONIC_SET = new TokenTypes("MNEMONIC_SET");
    public static final TokenTypes MNEMONIC_SET_CONDITIONAL = new TokenTypes("MNEMONIC_SET_CONDITIONAL");
    public static final TokenTypes COMPARE_MNEMONIC = new TokenTypes("COMPARE_MNEMONIC");
    public static final TokenTypes COMPARE_MNEMONIC_CONDITIONAL = new TokenTypes("COMPARE_MNEMONIC_CONDITIONAL");
    public static final TokenTypes MOVE_MNEMONIC = new TokenTypes("MOVE_MNEMONIC");
    public static final TokenTypes MOVE_MNEMONIC_SET = new TokenTypes("MOVE_MNEMONIC_SET");
    public static final TokenTypes MOVE_MNEMONIC_CONDITIONAL = new TokenTypes("MOVE_MNEMONIC_CONDITIONAL");
    public static final TokenTypes MOVE_MNEMONIC_SET_CONDITIONAL = new TokenTypes("MOVE_MNEMONIC_SET_CONDITIONAL");
    public static final TokenTypes MLA_MNEMONIC = new TokenTypes("MLA_MNEMONIC");
    public static final TokenTypes MLA_MNEMONIC_SET = new TokenTypes("MLA_MNEMONIC_SET");
    public static final TokenTypes MLA_MNEMONIC_CONDITIONAL = new TokenTypes("MLA_MNEMONIC_CONDITIONAL");
    public static final TokenTypes MLA_MNEMONIC_SET_CONDITIONAL = new TokenTypes("MLA_MNEMONIC_SET_CONDITIONAL");
    public static final TokenTypes MEMORY_MNEMONIC = new TokenTypes("MEMORY_MNEMONIC");
    public static final TokenTypes MEMORY_MNEMONIC_CONDITIONAL = new TokenTypes("MEMORY_MNEMONIC_CONDITIONAL");
    public static final TokenTypes BRANCH_MNEMONIC = new TokenTypes("BRANCH_MNEMONIC");
    public static final TokenTypes CONDITIONAL_SUFIX = new TokenTypes("CONDITIONAL_MNEMONIC");
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
