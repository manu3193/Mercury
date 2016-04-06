package mercury.arm.instruction;

/**
 * Created by manzumbado on 03/04/16.
 */
public abstract class DataProcessingInstruction {
    
    private String mCond;
    private final String mOp="00";
    private String mI;
    private String mCmd;
    private String mS;
    private String mRn;
    private String mRd;

    public String getCond() {
        return mCond;
    }

    public void setCond(String mCond) {
        this.mCond = mCond;
    }

    public String getOp() {
        return mOp;
    }

    public String getI() {
        return mI;
    }

    public void setI(String mI) {
        this.mI = mI;
    }

    public String getCmd() {
        return mCmd;
    }

    public void setCmd(String mCmd) {
        this.mCmd = mCmd;
    }

    public String getS() {
        return mS;
    }

    public void setS(String mS) {
        this.mS = mS;
    }

    public String getRn() {
        return mRn;
    }

    public void setRn(String mRn) {
        this.mRn = mRn;
    }

    public String getRd() {
        return mRd;
    }

    public void setRd(String mRd) {
        this.mRd = mRd;
    }
}
