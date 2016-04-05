package mercury.arm.instruction;

/**
 * Created by manzumbado on 03/04/16.
 */
public final class Constants {

    public static final String INMEDIATE_DATA_INSTRUCTION_TYPE="1";
    public static final String REG_DATA_INSTRUCTION_TYPE="0";
    public static final String IMMEDIATE_MEMORY_INSTRUCTION_TYPE="0";
    public static final String PREINDEX_CONTROL_BIT_LOW="0";
    public static final String PREINDEX_CONTROL_BIT_HIGH="1";
    public static final String WRITEBACK_CONTROL_BIT_LOW="0";
    public static final String WRITEBACK_CONTROL_BIT_HIGH="1";
    public static final String ADD_BASE_CONTROL_BIT="1";

    //Instruction types
    public static final String DP_TYPE="DP";
    public static final String MEM_TYPE="MEM";
    public static final String B_TYPE="B";

    //DataProcessing Addressing Modes
    public static final String DP_NORMAL_ADDRESING="DP_Normal";
    public static final String DP_IMM_ADDRESSING="DP_IMM";
    //Compare Adressing
    public static final String DP_CMV_REG_ADDRESSING="DP_CMV_NORMAL";
    public static final String DP_CMV_IMM_ADDRESSING="DP_CMV_IMM";
    //MUL Adressing
    public static final String DP_MLA_NORMAL_ADDRESSING="DP_MLA_NORMAL";
    public static final String DP_MUL_NORMAL_ADDRESSING="DP_MUL_NORMAL";
    //RRX Addressing
    public static final String DP_RRX_NORMAL_ADDRESSING="DP_RRX_NORMAL";
    //SHIFT Addressing
    public static final String DP_SHIFT_NORMAL_ADDRESSING="DP_SHIFT_NORMAL";
    public static final String DP_SHIFT_SHAMT5_ADDRESSING="DP_SHIFT_SHAMT5";
    //MEMORY Addressing
    public static final String MEM_OFFSET_IMM_ADDRESSING="MEM_OFFSET_IMM";
    public static final String MEM_OFFSET_REG_ADDRESSING="MEM_OFFSET_REG";
    public static final String MEM_REG_ADDRESSING="MEM_REG";



}
