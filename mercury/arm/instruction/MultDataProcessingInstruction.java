package mercury.arm.instruction;

/**
 * Created by manzumbado on 06/04/16.
 */
public class MultDataProcessingInstruction extends DataProcessingInstruction implements IBaseInstruction {

    private static final String mBitsFrom24to25="00";
    private String mRm;
    private String mRa;
    private static final String mBitsfrom4to7="1001";


    public static String getBitsFrom24to25() {
        return mBitsFrom24to25;
    }

    public String getRm() {
        return mRm;
    }

    public String getRa() {
        return mRa;
    }

    public void setRm(String mRm) {
        this.mRm = mRm;
    }

    public void setRa(String mRa) {
        this.mRa = mRa;
    }

    public static String getBitsfrom4to7() {
        return mBitsfrom4to7;
    }

    @Override
    public String getBinaryInstruction() {
        return getCond()+getOp()+getBitsFrom24to25()+getCmd()+getS()+getRd()+getRa()+getRm()+getBitsfrom4to7()+getRn();
    }
}
