package mercury.arm.instruction;

/**
 * Created by manzumbado on 03/04/16.
 */
public abstract class MemoryInstruction {
    
    private String mCond;
    private final String mOp="01";
    private String mI;
    private String mP;
    private String mU;
    private String mB;
    private String mW;
    private String mL;
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

    public String getP() {
        return mP;
    }

    public void setP(String mP) {
        this.mP = mP;
    }

    public String getU() {
        return mU;
    }

    public void setU(String mU) {
        this.mU = mU;
    }

    public String getB() {
        return mB;
    }

    public void setB(String mB) {
        this.mB = mB;
    }

    public String getW() {
        return mW;
    }

    public void setW(String mW) {
        this.mW = mW;
    }

    public String getL() {
        return mL;
    }

    public void setL(String mL) {
        this.mL = mL;
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
