package mercury.arm.instruction;

/**
 * Created by manzumbado on 03/04/16.
 */
public class ImmediateMemoryInstruction extends MemoryInstruction implements IBaseInstruction {

    private String mImm12;

    public ImmediateMemoryInstruction(){
        super.setI(Constants.IMMEDIATE_MEMORY_INSTRUCTION_TYPE);
        super.setP(Constants.PREINDEX_CONTROL_BIT_LOW);
        super.setU(Constants.ADD_BASE_CONTROL_BIT);
        super.setW(Constants.WRITEBACK_CONTROL_BIT_HIGH);
    }

    public String getImm12() {
        return mImm12;
    }

    public void setImm12(String mImm12) {
        this.mImm12 = mImm12;
    }

    @Override
    public String getBinaryInstruction() {
        return getCond()+getOp()+getI()+getP()+getU()+getB()+getW()+getL()+getRn()+getRd()+getImm12();
    }
}
