package mercury;

import mercury.arm.instruction.LabelStatement;

import java.util.List;

/**
 * Created by manzumbado on 19/03/16.
 */
public class ARMProgram {

    private String mFilename;
    private List<ProgramStatement> mStatementList;
    private List<LabelStatement> mSetLabelList;
    private ErrorList mErrorList;


    public ARMProgram(String mFilename) {
        this.mFilename = mFilename;
    }

    public List<ProgramStatement> getStatementList() {
        return mStatementList;
    }

    public String getFilename() {
        return mFilename;
    }

    public void setStatementList(List<ProgramStatement> mStatementList) {
        this.mStatementList = mStatementList;
    }

    public ErrorList getErrorList() {
        return mErrorList;
    }

    public void setErrorList(ErrorList mErrorList) {
        this.mErrorList = mErrorList;
    }

    public List<LabelStatement> getSetLabelList() {
        return mSetLabelList;
    }

    public void setSetLabelList(List<LabelStatement> mSetLabelList) {
        this.mSetLabelList = mSetLabelList;
    }
}
