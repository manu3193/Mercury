package mercury.arm.instruction;

/**
 * Created by manzumbado on 03/04/16.
 */
public class RegShDataProcessigInstruction extends DataProcessingInstruction implements IBaseInstruction {

    private String mRs;
    private String mBit7;
    private String mSh;
    private final String mBit4="1";
    private String mRm;

    public RegShDataProcessigInstruction(){
        super.setI(Constants.REG_DATA_INSTRUCTION_TYPE);
    }

    public String getRs() {
        return mRs;
    }

    public void setRs(String mRs) {
        this.mRs = mRs;
    }

    public String getBit7() {
        return mBit7;
    }

    public void setBit7(String mBit7) {
        this.mBit7 = mBit7;
    }

    public String getSh() {
        return mSh;
    }

    public void setSh(String mSh) {
        this.mSh = mSh;
    }

    public String getBit4() {
        return mBit4;
    }

    public String getRm() {
        return mRm;
    }

    public void setRm(String mRm) {
        this.mRm = mRm;
    }

    @Override
    public String getBinaryInstruction() {
        return getCond()+getOp()+getI()+getCmd()+getS()+getRn()+getRd()+getRs()+getBit7()+getSh()+getBit4()+getRm();
    }
}
