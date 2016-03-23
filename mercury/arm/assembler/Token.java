package mercury.arm.assembler;

/**
 * Created by manzumbado on 22/03/16.
 */
public class Token {

    private TokenTypes type;
    private String value;
    private int sourceLine;
    private int sourcePosition;


    public Token(TokenTypes type, String value, int sourceLine, int sourcePosition) {
        this.type = type;
        this.value = value;
        this.sourceLine = sourceLine;
        this.sourcePosition = sourcePosition;
    }

    public TokenTypes getType() {
        return type;
    }

    public void setType(TokenTypes type) {
        this.type = type;
    }

    public int getSourceLine() {
        return sourceLine;
    }

    public void setSourceLine(int sourceLine) {
        this.sourceLine = sourceLine;
    }

    public int getSourcePosition() {
        return sourcePosition;
    }

    public void setSourcePosition(int sourcePosition) {
        this.sourcePosition = sourcePosition;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }
}


