package mercury;

import mercury.arm.instruction.IBaseInstruction;

import java.util.ArrayList;

/**
 * Created by manzumbado on 20/03/16.
 */
public class ProgramStatement {
    
    private String mInstructionType;
    private String mRd;
    private String mRn;
    private String mRm;
    private String mRa;
    private String mMnemonic;
    private String mImm;
    private String mRs;
    private String mShamt5;
    private String mConditional;
    private String mCallLabel;
    private boolean mHasConditionalSufix;
    private boolean mHasSetFlagsSufix;
    private String mAddressingMode;
    private int mMemoryAddress;
    private IBaseInstruction mEncodedInstruction;
    private int mLine;
    private String mBranchAdress;

    public ProgramStatement() {
        this.mHasSetFlagsSufix=false;
        this.mHasConditionalSufix=false;
    }

    public String getCallLabel() {
        return mCallLabel;
    }

    public void setCallLabel(String mCallLabel) {
        this.mCallLabel = mCallLabel;
    }

    public String getRd() {
        return mRd;
    }

    public String getRs() {
        return mRs;
    }

    public String getConditional() {
        return mConditional;
    }

    public void setConditional(String mConditional) {
        this.mConditional = mConditional;
    }

    public void setRs(String mRs) {
        this.mRs = mRs;
    }

    public String getShamt5() {
        return mShamt5;
    }

    public void setShamt5(String mShamt5) {
        this.mShamt5 = mShamt5;
    }

    public void setRd(String mRd) {
        this.mRd = mRd;
    }

    public String getRn() {
        return mRn;
    }

    public void setRn(String mRn) {
        this.mRn = mRn;
    }

    public String getRm() {
        return mRm;
    }

    public void setRm(String mRm) {
        this.mRm = mRm;
    }

    public String getRa() {
        return mRa;
    }

    public void setRa(String mRa) {
        this.mRa = mRa;
    }

    public String getMnemonic() {
        return mMnemonic;
    }

    public void setMnemonic(String mMnemonic) {
        this.mMnemonic = mMnemonic;
    }

    public String getImm() {
        return mImm;
    }

    public void setImm(String mImm) {
        this.mImm = mImm;
    }

    public boolean HasConditionalSufix() {
        return mHasConditionalSufix;
    }

    public String getBranchAdress() {
        return mBranchAdress;
    }

    public void setBranchAdress(String mBranchAdress) {
        this.mBranchAdress = mBranchAdress;
    }

    public void setHasConditionalSufix(boolean hasConditionalSufix) {
        this.mHasConditionalSufix = hasConditionalSufix;
    }

    public boolean HasSetFlagsSufix() {
        return mHasSetFlagsSufix;
    }

    public void setHasSetFlagsSufix(boolean hasSetFlagsSufix) {
        this.mHasSetFlagsSufix = hasSetFlagsSufix;
    }
    

    public String getInstructionType() {
        return mInstructionType;
    }

    public String getAddressingMode() {
        return mAddressingMode;
    }

    public int getMemoryAddress() {
        return mMemoryAddress;
    }

    public void setInstructionType(String mInstructionType) {
        this.mInstructionType = mInstructionType;
    }


    public void setAddressingMode(String mAddressingMode) {
        this.mAddressingMode = mAddressingMode;
    }


    public void setLine(int mLine) {
        this.mLine = mLine;
    }

    public void setMemoryAddress(int memoryAddres) {
        mMemoryAddress = memoryAddres;
    }

    public IBaseInstruction getEncodedInstruction() {
        return mEncodedInstruction;
    }

    public void setEncodedInstruction(IBaseInstruction encodedInstruction) {
        mEncodedInstruction = encodedInstruction;
    }

    public int getLine() {
        return mLine;
    }
}
