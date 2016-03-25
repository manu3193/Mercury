package mercury;

/**
 * Created by manzumbado on 22/03/16.
 */
public class ErrorMessage {

    private int line;
    private int pos;
    private String message;
    private String type;

    public ErrorMessage(int line, int pos, String message, String type) {
        this.line = line;
        this.pos = pos;
        this.message = message;
        this.type = type;
    }

    public int getLine() {
        return line;
    }

    public int getPos() {
        return pos;
    }

    public String getType(){ return type;}

    public String getMessage() {
        return message;
    }
}
