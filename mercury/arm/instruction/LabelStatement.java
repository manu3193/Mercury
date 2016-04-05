package mercury.arm.instruction;

/**
 * Created by manzumbado on 03/04/16.
 */
public class LabelStatement {

    private String mLabelText;
    private int mLine;

    public LabelStatement(String labelText, int line) {
        this.mLabelText = labelText;
        this.mLine = line;
    }

    public String getLabelText() {
        return mLabelText;
    }

    public int getLine() {
        return mLine;
    }
}
