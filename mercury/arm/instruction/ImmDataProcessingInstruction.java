package mercury.arm.instruction;

import mercury.arm.Constants;

/**
 * Created by manzumbado on 03/04/16.
 */
public class ImmDataProcessingInstruction extends DataProcessingInstruction  implements IBaseInstruction {

    private String mRot;
    private String mImm8;


    public ImmDataProcessingInstruction(){
        super.setI(Constants.INMEDIATE_DATA_INSTRUCTION_TYPE);
    }
    public String getRot() {
        return mRot;
    }

    public void setRot(String mRot) {
        this.mRot = mRot;
    }

    public String getImm8() {
        return mImm8;
    }

    public void setImm8(String mImm8) {
        this.mImm8 = mImm8;
    }

    @Override
    public String getBinaryInstruction() {
        return getCond()+getOp()+getI()+getCmd()+getS()+getRn()+getRd()+getRot()+getImm8();
    }
}
