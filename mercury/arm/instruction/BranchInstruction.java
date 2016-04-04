package mercury.arm.instruction;

/**
 * Created by manzumbado on 03/04/16.
 */
public class BranchInstruction implements IBaseInstruction{

    private String mCond;
    private String mImm24;
    private final String mOp="10";
    private final String mFunct="10";

    public String getCond() {
        return mCond;
    }

    public void setCond(String mCond) {
        this.mCond = mCond;
    }

    public String getImm24() {
        return mImm24;
    }

    public void setImm24(String mImm24) {
        this.mImm24 = mImm24;
    }

    public String getOp() {
        return mOp;
    }

    public String getFunct() {
        return mFunct;
    }

    @Override
    public String getBinaryInstruction() {
        return getCond()+getOp()+getFunct()+getFunct();
    }
}
