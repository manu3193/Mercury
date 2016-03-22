package mercury;

/**
 * Created by manzumbado on 22/03/16.
 */
public class ErrorMessage {

    private int line;
    private int pos;
    private String message;

    public ErrorMessage(int line, int pos, String message) {
        this.line = line;
        this.pos = pos;
        this.message = message;
    }

    public int getLine() {
        return line;
    }

    public int getPos() {
        return pos;
    }

    public String getMessage() {
        return message;
    }
}
