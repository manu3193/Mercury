package mercury.arm.instruction;

/**
 * Created by manzumbado on 03/04/16.
 */
public class RegisterMemoryInstruction extends MemoryInstruction implements IBaseInstruction {
    
    private String mShamt5;
    private String mSh;
    private final String mBit4="1";
    private String mRm;

    public RegisterMemoryInstruction(){
        super.setP(Constants.PREINDEX_CONTROL_BIT_HIGH);
        super.setW(Constants.WRITEBACK_CONTROL_BIT_LOW);
    }
    public String getShamt5() {
        return mShamt5;
    }

    public void setShamt5(String mShamt5) {
        this.mShamt5 = mShamt5;
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
        return null;
    }
}
