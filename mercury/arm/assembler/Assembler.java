package mercury.arm.assembler;

import mercury.ARMProgram;
import mercury.ErrorList;
import mercury.ErrorMessage;
import mercury.ProgramStatement;
import mercury.arm.Constants;
import mercury.arm.instruction.*;

import java.io.*;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;


/**
 * Created by manzumbado on 04/04/16.
 */
public class Assembler {


    private int mPC_Counter;
    private ErrorList errorList;
    private boolean errorfound;
    private HashMap<String,Integer> labelsHashMap;
    private final HashMap<String,String> registersHashMap;
    private final HashMap<String,String> conditionalHashMap;
    private final HashMap<String,String> mnemonicHashMap;
    private final HashMap<String,String> shiftHashMap;
    private final HashMap<String,String[]> memOpHashmap;
    private static final int INIT_PROGRAM_MEM_ADDRES=0x0;
    private static final int MAX_PROGRAM_MEM_ADDRES=0x3FF;



    public Assembler() {
        this.mPC_Counter=0x0;
        this.errorfound=false;
        labelsHashMap = new HashMap<>();
        registersHashMap=initRegisterHash();
        conditionalHashMap=initConditionalHash();
        mnemonicHashMap=initMnemonicHashmap();
        shiftHashMap=initshHashrHash();
        memOpHashmap=initMemOpHashMap();
    }

    private HashMap<String,String> initRegisterHash(){
        HashMap<String,String> regHash = new HashMap<>();
        regHash.put("R0","0000");
        regHash.put("R1","0001");
        regHash.put("R2","0010");
        regHash.put("R3","0011");
        regHash.put("R4","0100");
        regHash.put("R5","0101");
        regHash.put("R6","0110");
        regHash.put("R7","0111");
        regHash.put("R8","1000");
        regHash.put("R9","1001");
        regHash.put("R10","1010");
        regHash.put("R11","1011");
        regHash.put("R12","1100");
        regHash.put("R13","1101");
        regHash.put("R14","1110");
        regHash.put("LR","1110");
        regHash.put("R15","1111");
        regHash.put("PC","1111");
        return regHash;
    }

    private HashMap<String,String> initshHashrHash(){
        HashMap<String,String> shHash = new HashMap<>();
        shHash.put("LSL","00");
        shHash.put("ASR","10");
        shHash.put("RRX","11");
        shHash.put("ROR","11");
        return shHash;
    }

    private HashMap<String,String> initMnemonicHashmap(){
        HashMap<String,String> mnemonicHash = new HashMap<>();
        mnemonicHash.put(Constants.AND_MNEMONIC,"0000");
        mnemonicHash.put(Constants.EOR_MNEMONIC,"0001");
        mnemonicHash.put(Constants.SUB_MNEMONIC,"0010");
        mnemonicHash.put(Constants.RSB_MNEMONIC,"0011");
        mnemonicHash.put(Constants.ADD_MNEMONIC,"0100");
        mnemonicHash.put(Constants.ADC_MNEMONIC,"0101");
        mnemonicHash.put(Constants.SBC_MNEMONIC,"0110");
        mnemonicHash.put(Constants.RSC_MNEMONIC,"0111");
        mnemonicHash.put(Constants.CMP_MNEMONIC,"1010");
        mnemonicHash.put(Constants.CMN_MNEMONIC,"1011");
        mnemonicHash.put(Constants.ORR_MNEMONIC,"1100");
        mnemonicHash.put(Constants.MOV_MNEMONIC,"1101");
        mnemonicHash.put(Constants.LSL_MNEMONIC,"1101");
        mnemonicHash.put(Constants.ASR_MNEMONIC,"1101");
        mnemonicHash.put(Constants.RRX_MNEMONIC,"1101");
        mnemonicHash.put(Constants.ROR_MNEMONIC,"1101");
        mnemonicHash.put(Constants.BIC_MNEMONIC,"1110");
        mnemonicHash.put(Constants.MVN_MNEMONIC,"1111");
        return mnemonicHash;
    }

    private HashMap<String,String> initConditionalHash(){
        HashMap<String,String> condHash = new HashMap<>();
        condHash.put("EQ","0000");
        condHash.put("NE","0001");
        condHash.put("CS","0010");
        condHash.put("HS","0010");
        condHash.put("CC","0011");
        condHash.put("LO","0011");
        condHash.put("MI","0100");
        condHash.put("PL","0101");
        condHash.put("VS","0110");
        condHash.put("VC","0111");
        condHash.put("HI","1000");
        condHash.put("LS","1001");
        condHash.put("GE","1010");
        condHash.put("LT","1011");
        condHash.put("GT","1100");
        condHash.put("LE","1101");
        condHash.put("AL","1110");
        return condHash;
    }

    private HashMap<String,String[]> initMemOpHashMap(){
        HashMap<String,String[]> hashmap= new HashMap<>();
        hashmap.put("STR",new String[]{"0","0"});
        hashmap.put("LDR",new String[]{"0","1"});
        hashmap.put("STRB",new String[]{"1","0"});
        hashmap.put("LDRB", new String[]{"1","1"});
        return hashmap;
    }


    public void Assemble(ARMProgram program){
        this.errorList=program.getErrorList();
        LabelingProcess(program);
        ComputeBranchesTargetAddress(program);
        List<ProgramStatement> programStatementList = program.getStatementList();
        for (ProgramStatement currentStatement :
                programStatementList) {
            if(errorfound){
                break;
            }

            String instrMnemonic= currentStatement.getMnemonic().toUpperCase();
            int instrMnemonicLength= instrMnemonic.length();
            if(currentStatement.HasSetFlagsSufix() & !currentStatement.HasConditionalSufix()){
                instrMnemonic=instrMnemonic.substring(0,instrMnemonicLength-1);
                currentStatement.setConditional(currentStatement.getMnemonic().toUpperCase().substring(instrMnemonicLength-2));
            }
            else if(!currentStatement.HasSetFlagsSufix() & currentStatement.HasConditionalSufix()){
                instrMnemonic=instrMnemonic.substring(0,instrMnemonicLength-2);
                currentStatement.setConditional(currentStatement.getMnemonic().toUpperCase().substring(instrMnemonicLength-2,instrMnemonicLength));
            }
            else if(currentStatement.HasConditionalSufix() & currentStatement.HasSetFlagsSufix()){
                instrMnemonic=instrMnemonic.substring(0,instrMnemonicLength-3);
                currentStatement.setConditional(currentStatement.getMnemonic().toUpperCase().substring(instrMnemonicLength-2,instrMnemonicLength));
            }
            else{
                currentStatement.setConditional("AL");
            }

            switch (instrMnemonic){
                case Constants.AND_MNEMONIC:
                    EncodeDPInstruction(currentStatement,instrMnemonic);
                    break;
                case Constants.EOR_MNEMONIC:
                    EncodeDPInstruction(currentStatement,instrMnemonic);
                    break;
                case Constants.SUB_MNEMONIC:
                    EncodeDPInstruction(currentStatement,instrMnemonic);
                    break;
                case Constants.RSB_MNEMONIC:
                    EncodeDPInstruction(currentStatement,instrMnemonic);
                    break;
                case Constants.ADD_MNEMONIC:
                    EncodeDPInstruction(currentStatement,instrMnemonic);
                    break;
                case Constants.ADC_MNEMONIC:
                    EncodeDPInstruction(currentStatement,instrMnemonic);
                    break;
                case Constants.SBC_MNEMONIC:
                    EncodeDPInstruction(currentStatement,instrMnemonic);
                    break;
                case Constants.RSC_MNEMONIC:
                    EncodeDPInstruction(currentStatement,instrMnemonic);
                    break;
                case Constants.ORR_MNEMONIC:
                    EncodeDPInstruction(currentStatement,instrMnemonic);
                    break;
                case Constants.BIC_MNEMONIC:
                    EncodeDPInstruction(currentStatement,instrMnemonic);
                    break;
                case Constants.MVN_MNEMONIC:
                    EncodeDPInstruction(currentStatement,instrMnemonic);
                    break;
                case Constants.CMP_MNEMONIC:
                    EncodeCMVInstruction(currentStatement,instrMnemonic);
                    break;
                case Constants.CMN_MNEMONIC:
                    EncodeCMVInstruction(currentStatement,instrMnemonic);
                    break;
                case Constants.MOV_MNEMONIC:
                    EncodeMoveInstruction(currentStatement,instrMnemonic);
                    break;
                case Constants.LSL_MNEMONIC:
                    EncodeShiftInstruction(currentStatement,instrMnemonic);
                    break;
                case Constants.ASR_MNEMONIC:
                    EncodeShiftInstruction(currentStatement,instrMnemonic);
                    break;
                case Constants.ROR_MNEMONIC:
                    EncodeShiftInstruction(currentStatement,instrMnemonic);
                    break;
                case Constants.RRX_MNEMONIC:
                    EncodeRRXInstruction(currentStatement,instrMnemonic);
                    break;
                case Constants.MUL_MNEMONIC:
                    EncodeMulInstruction(currentStatement,instrMnemonic);
                    break;
                case Constants.MLA_MNEMONIC:
                    EncodeMlaInstruction(currentStatement,instrMnemonic);
                    break;
                case Constants.STR_MNEMONIC:
                    EncodeMEMnstruction(currentStatement,instrMnemonic);
                    break;
                case Constants.STRB_MNEMONIC:
                    EncodeMEMnstruction(currentStatement,instrMnemonic);
                    break;
                case Constants.LDR_MNEMONIC:
                    EncodeMEMnstruction(currentStatement,instrMnemonic);
                    break;
                case Constants.LDRB_MNEMONIC:
                    EncodeMEMnstruction(currentStatement,instrMnemonic);
                    break;
                case Constants.B_MNEMONIC:
                    EncodeBInstruction(currentStatement,instrMnemonic);
                    break;
            }
        }
        if(!errorfound){
            GenerateOutputFile(programStatementList);
        }
    }


    ///For common data processing
    private void EncodeDPInstruction(ProgramStatement statement,String mnemonic){
        String instrAddressingType = statement.getAddressingMode();
        switch (instrAddressingType){
            case Constants.DP_NORMAL_ADDRESING:
                RegDataProcessingInstruction dpNormalInstr= new RegDataProcessingInstruction();
                dpNormalInstr.setCond(conditionalHashMap.get(statement.getConditional()));
                dpNormalInstr.setCmd(mnemonicHashMap.get(mnemonic));
                dpNormalInstr.setS(statement.HasSetFlagsSufix() ? "1" : "0");
                dpNormalInstr.setRn(registersHashMap.get(statement.getRn()));
                dpNormalInstr.setRd(registersHashMap.get(statement.getRd()));
                dpNormalInstr.setShamt5("00000");
                dpNormalInstr.setSh("00");
                dpNormalInstr.setRm(registersHashMap.get(statement.getRm()));
                statement.setEncodedInstruction(dpNormalInstr);
                break;
            case Constants.DP_IMM_ADDRESSING:
                ImmDataProcessingInstruction dpImmInstr = new ImmDataProcessingInstruction();
                dpImmInstr.setCond(conditionalHashMap.get(statement.getConditional()));
                dpImmInstr.setCmd(mnemonicHashMap.get(mnemonic));
                dpImmInstr.setS(statement.HasSetFlagsSufix() ? "1" : "0");
                dpImmInstr.setRn(registersHashMap.get(statement.getRn()));
                dpImmInstr.setRd(registersHashMap.get(statement.getRd()));
                String[] rotationResult=ValidateImmediate(statement.getImm());
                if(rotationResult==null){
                    this.errorfound=true;
                    this.errorList.add(new ErrorMessage(statement.getLine(),0,"Error, the given immediate is not a valid value. ", Constants.PROGRAM_IMM8_NOT_VALID));
                    break;
                }else{
                    dpImmInstr.setRot(rotationResult[0]);
                    dpImmInstr.setImm8(rotationResult[1]);
                }
                statement.setEncodedInstruction(dpImmInstr);
                break;
        }
    }


    private void EncodeCMVInstruction(ProgramStatement statement,String mnemonic){
        String instrAddressingType = statement.getAddressingMode();
        switch (instrAddressingType){
            case Constants.DP_CMV_REG_ADDRESSING:
                RegDataProcessingInstruction dpNormalInstr= new RegDataProcessingInstruction();
                dpNormalInstr.setCond(conditionalHashMap.get(statement.getConditional()));
                dpNormalInstr.setCmd(mnemonicHashMap.get(mnemonic));
                dpNormalInstr.setS("1");
                dpNormalInstr.setRn(registersHashMap.get(statement.getRn()));
                dpNormalInstr.setRd("0000");
                dpNormalInstr.setShamt5("00000");
                dpNormalInstr.setSh("00");
                dpNormalInstr.setRm(registersHashMap.get(statement.getRm()));
                statement.setEncodedInstruction(dpNormalInstr);
                break;
            case Constants.DP_CMV_IMM_ADDRESSING:
                ImmDataProcessingInstruction dpImmInstr = new ImmDataProcessingInstruction();
                dpImmInstr.setCond(conditionalHashMap.get(statement.getConditional()));
                dpImmInstr.setCmd(mnemonicHashMap.get(mnemonic));
                dpImmInstr.setS("1");
                dpImmInstr.setRn(registersHashMap.get(statement.getRn()));
                dpImmInstr.setRd("0000");
                String[] rotationResult=ValidateImmediate(statement.getImm());
                if(rotationResult==null){
                    this.errorfound=true;
                    this.errorList.add(new ErrorMessage(statement.getLine(),0,"Error, the given immediate is not a valid value. ", Constants.PROGRAM_IMM8_NOT_VALID));
                    break;
                }else{
                    dpImmInstr.setRot(rotationResult[0]);
                    dpImmInstr.setImm8(rotationResult[1]);
                }
                statement.setEncodedInstruction(dpImmInstr);
                break;
        }
    }

    private void EncodeMoveInstruction(ProgramStatement statement,String mnemonic){
        String instrAddressingType = statement.getAddressingMode();
        switch (instrAddressingType){
            case Constants.DP_CMV_REG_ADDRESSING:
                RegDataProcessingInstruction dpNormalInstr= new RegDataProcessingInstruction();
                dpNormalInstr.setCond(conditionalHashMap.get(statement.getConditional()));
                dpNormalInstr.setCmd(mnemonicHashMap.get(mnemonic));
                dpNormalInstr.setS(statement.HasSetFlagsSufix() ? "1" : "0");
                dpNormalInstr.setRn("0000");
                dpNormalInstr.setRd(registersHashMap.get(statement.getRd()));
                dpNormalInstr.setShamt5("00000");
                dpNormalInstr.setSh("00");
                dpNormalInstr.setRm(registersHashMap.get(statement.getRm()));
                statement.setEncodedInstruction(dpNormalInstr);
                break;
            case Constants.DP_CMV_IMM_ADDRESSING:
                ImmDataProcessingInstruction dpImmInstr = new ImmDataProcessingInstruction();
                dpImmInstr.setCond(conditionalHashMap.get(statement.getConditional()));
                dpImmInstr.setCmd(mnemonicHashMap.get(mnemonic));
                dpImmInstr.setS(statement.HasSetFlagsSufix() ? "1" : "0");
                dpImmInstr.setRn("0000");
                dpImmInstr.setRd(registersHashMap.get(statement.getRd()));
                String[] rotationResult=ValidateImmediate(statement.getImm());
                if(rotationResult==null){
                    this.errorfound=true;
                    this.errorList.add(new ErrorMessage(statement.getLine(),0,"Error, the given immediate is not a valid value. ", Constants.PROGRAM_IMM8_NOT_VALID));
                    break;
                }else{
                    dpImmInstr.setRot(rotationResult[0]);
                    dpImmInstr.setImm8(rotationResult[1]);
                }
                statement.setEncodedInstruction(dpImmInstr);
                break;
        }
    }

    private void EncodeShiftInstruction(ProgramStatement statement,String mnemonic){
        String instrAddressingType = statement.getAddressingMode();
        switch (instrAddressingType){
            case Constants.DP_SHIFT_SHAMT5_ADDRESSING:
                RegDataProcessingInstruction dpNormalInstr= new RegDataProcessingInstruction();
                dpNormalInstr.setCond(conditionalHashMap.get(statement.getConditional()));
                dpNormalInstr.setCmd(mnemonicHashMap.get(mnemonic));
                dpNormalInstr.setS(statement.HasSetFlagsSufix() ? "1" : "0");
                dpNormalInstr.setRn("0000");
                dpNormalInstr.setRd(registersHashMap.get(statement.getRd()));
                dpNormalInstr.setI("0");
                dpNormalInstr.setSh(shiftHashMap.get(statement.getMnemonic()));
                dpNormalInstr.setRm(registersHashMap.get(statement.getRm()));
                String shamt5=ValidateShamt5(statement.getShamt5());
                if(shamt5!=null){
                    dpNormalInstr.setShamt5(shamt5);
                }else{
                    ///error
                    this.errorfound=true;
                    this.errorList.add(new ErrorMessage(statement.getLine(),0,"Error, the given shift amount is not a valid value. ", Constants.PROGRAM_SHAMT5_NOT_VALID));
                    break;
                }
                statement.setEncodedInstruction(dpNormalInstr);
                break;
            case Constants.DP_SHIFT_NORMAL_ADDRESSING:
                RegShDataProcessigInstruction dpShRegInstr = new RegShDataProcessigInstruction();
                dpShRegInstr.setCond(conditionalHashMap.get(statement.getConditional()));
                dpShRegInstr.setI("0");
                dpShRegInstr.setCmd(mnemonicHashMap.get(mnemonic));
                dpShRegInstr.setSh(shiftHashMap.get(statement.getMnemonic()));
                dpShRegInstr.setS(statement.HasSetFlagsSufix() ? "1" : "0");
                dpShRegInstr.setRn("0000");
                dpShRegInstr.setRs(registersHashMap.get(statement.getRs()));
                dpShRegInstr.setRm(registersHashMap.get(statement.getRm()));
                dpShRegInstr.setRd(registersHashMap.get(statement.getRd()));
                statement.setEncodedInstruction(dpShRegInstr);
                break;
        }
    }


    private void EncodeRRXInstruction(ProgramStatement statement,String mnemonic){
        RegShDataProcessigInstruction dpShRegInstr = new RegShDataProcessigInstruction();
        dpShRegInstr.setCond(conditionalHashMap.get(statement.getConditional()));
        dpShRegInstr.setI("0");
        dpShRegInstr.setCmd(mnemonicHashMap.get(mnemonic));
        dpShRegInstr.setSh(shiftHashMap.get(statement.getMnemonic()));
        dpShRegInstr.setS(statement.HasSetFlagsSufix() ? "1" : "0");
        dpShRegInstr.setRn("0000");
        dpShRegInstr.setRs("0000");
        dpShRegInstr.setRm(registersHashMap.get(statement.getRm()));
        dpShRegInstr.setRd(registersHashMap.get(statement.getRd()));
        statement.setEncodedInstruction(dpShRegInstr);
    }


    private void EncodeMulInstruction(ProgramStatement statement,String mnemonic){
        MultDataProcessingInstruction mulDPInstr = new MultDataProcessingInstruction();
        mulDPInstr.setCond(conditionalHashMap.get(statement.getConditional()));
        mulDPInstr.setCmd("000");
        mulDPInstr.setS(statement.HasSetFlagsSufix() ? "1" : "0");
        mulDPInstr.setRd(registersHashMap.get(statement.getRd()));
        mulDPInstr.setRa("0000");
        mulDPInstr.setRm(registersHashMap.get(statement.getRm()));
        mulDPInstr.setRn(registersHashMap.get(statement.getRn()));
        statement.setEncodedInstruction(mulDPInstr);
    }

    private void EncodeMlaInstruction(ProgramStatement statement,String mnemonic){
        MultDataProcessingInstruction mlaDPInstr = new MultDataProcessingInstruction();
        mlaDPInstr.setCond(conditionalHashMap.get(statement.getConditional()));
        mlaDPInstr.setCmd("001");
        mlaDPInstr.setS(statement.HasSetFlagsSufix() ? "1" : "0");
        mlaDPInstr.setRd(registersHashMap.get(statement.getRd()));
        mlaDPInstr.setRa(registersHashMap.get(statement.getRd()));
        mlaDPInstr.setRm(registersHashMap.get(statement.getRm()));
        mlaDPInstr.setRn(registersHashMap.get(statement.getRn()));
        statement.setEncodedInstruction(mlaDPInstr);
    }


    private void EncodeMEMnstruction(ProgramStatement statement,String mnemonic){
        String instrAddressingType = statement.getAddressingMode();
        switch (instrAddressingType){
            case Constants.MEM_OFFSET_REG_ADDRESSING:
                RegisterMemoryInstruction memOffRegInstr= new RegisterMemoryInstruction();
                memOffRegInstr.setCond(conditionalHashMap.get(statement.getConditional()));
                memOffRegInstr.setP("1");
                memOffRegInstr.setU("1");
                memOffRegInstr.setB(memOpHashmap.get(mnemonic)[0]);
                memOffRegInstr.setW("0");
                memOffRegInstr.setL(memOpHashmap.get(mnemonic)[1]);
                memOffRegInstr.setRn(registersHashMap.get(statement.getRn()));
                memOffRegInstr.setRd(registersHashMap.get(statement.getRd()));
                memOffRegInstr.setShamt5("00000");
                memOffRegInstr.setSh("00");
                memOffRegInstr.setRm(registersHashMap.get(statement.getRm()));
                statement.setEncodedInstruction(memOffRegInstr);
                break;
            case Constants.MEM_OFFSET_IMM_ADDRESSING:
                ImmediateMemoryInstruction memOffImmInstr = new ImmediateMemoryInstruction();
                memOffImmInstr.setCond(conditionalHashMap.get(statement.getConditional()));
                memOffImmInstr.setP("1");
                memOffImmInstr.setU("1");
                memOffImmInstr.setB(memOpHashmap.get(mnemonic)[0]);
                memOffImmInstr.setW("0");
                memOffImmInstr.setL(memOpHashmap.get(mnemonic)[1]);
                memOffImmInstr.setRn(registersHashMap.get(statement.getRn()));
                memOffImmInstr.setRd(registersHashMap.get(statement.getRd()));
                String imm12=ValidateImm12(statement.getImm(),memOpHashmap.get(statement.getMnemonic())[0]);
                if(imm12!=null){
                    memOffImmInstr.setImm12(imm12);
                }else{
                    ///error
                    this.errorfound=true;
                    this.errorList.add(new ErrorMessage(statement.getLine(),0,"Error, the given immediate is not a valid address. ", Constants.PROGRAM_IMM12_NOT_VALID));
                    break;
                }
                statement.setEncodedInstruction(memOffImmInstr);
                break;
            case Constants.MEM_REG_ADDRESSING:
                RegisterMemoryInstruction memRegInstr= new RegisterMemoryInstruction();
                memRegInstr.setCond(conditionalHashMap.get(statement.getConditional()));
                memRegInstr.setP("0");
                memRegInstr.setU("1");
                memRegInstr.setB(memOpHashmap.get(mnemonic)[0]);
                memRegInstr.setW("1");
                memRegInstr.setL(memOpHashmap.get(mnemonic)[1]);
                memRegInstr.setRn(registersHashMap.get(statement.getRn()));
                memRegInstr.setRd(registersHashMap.get(statement.getRd()));
                memRegInstr.setShamt5("00000");
                memRegInstr.setSh("00");
                memRegInstr.setRm(registersHashMap.get(statement.getRm()));
                statement.setEncodedInstruction(memRegInstr);
                break;
        }
    }

    private void EncodeBInstruction(ProgramStatement statement,String mnemonic){
        BranchInstruction bInstr = new BranchInstruction();
        bInstr.setCond(conditionalHashMap.get(statement.getConditional()));
        bInstr.setImm24(statement.getBranchAdress());
        statement.setEncodedInstruction(bInstr);
    }

    private String ValidateImm12(String immm, String mnemonicType){
        String result;
        int numericShamt= Integer.decode(immm);
        if(numericShamt>0x7FF){
            result=null;
        }
        else
            result=Integer.toString(numericShamt & 0b11111,2);
        return result;
    }


    private void NexPCAddress(){
        this.mPC_Counter=mPC_Counter+0x4;
    }


    private void ComputeBranchesTargetAddress(ARMProgram program){
        List<ProgramStatement> programStatementList = program.getStatementList();
        for (ProgramStatement currentStatement :
                programStatementList) {
            if(currentStatement.getInstructionType()==Constants.B_TYPE){
                String targetLabel=currentStatement.getCallLabel();
                if(labelsHashMap.containsKey(targetLabel)){
                    int branchTargetAddress=labelsHashMap.get(targetLabel);
                    int imm24=(branchTargetAddress-(currentStatement.getMemoryAddress()+8))/4;
                    String imm24String=Integer.toString(imm24 & 0xFFFFFF,2);
                    for(int n=imm24String.length(); n<24; n++) {
                        imm24String="0"+imm24String;
                    }
                    currentStatement.setBranchAdress(imm24String);
                }else{
                    this.errorList.add(new ErrorMessage(0,0,"Error assigning target label address, the given label is not declared.", Constants.PROGRAM_MEMORY_ADDRESSING_ERROR));

                }
            }
        }
    }




    private void LabelingProcess(ARMProgram program){
        List<ProgramStatement> programStatementList = program.getStatementList();
        List<LabelStatement> labelsList = program.getSetLabelList();
        int statementAddress=INIT_PROGRAM_MEM_ADDRES;
        for (int counter = 0; counter < programStatementList.size();counter++) {
            if (statementAddress<=MAX_PROGRAM_MEM_ADDRES) {
                ProgramStatement currentStatement =programStatementList.get(counter);
                currentStatement.setMemoryAddress(statementAddress);
                statementAddress+=0x4;
            } else {
                errorfound=true;
                this.errorList.add(new ErrorMessage(0,0,"Error assigning program statement memory, the program exceeds the maximum address size.", Constants.PROGRAM_MEMORY_ADDRESSING_ERROR));
            }
        }
        if (!errorfound) {
            int statementListSize=programStatementList.size();
            for(int labelCounter=0; labelCounter<labelsList.size();labelCounter++){
                LabelStatement currentLabelStatement=labelsList.get(labelCounter);
                int labelStatementLine=currentLabelStatement.getLine();

                for (int statementCounter = 0; statementCounter < statementListSize;statementCounter++) {
                    ProgramStatement currentProgramStatement =programStatementList.get(statementCounter);
                    int statementLabelLength=currentLabelStatement.getLabelText().length();
                    String statementLabel=currentLabelStatement.getLabelText().substring(0,statementLabelLength-1);
                    int currentPSLine=currentProgramStatement.getLine();
                    if(currentPSLine>labelStatementLine){
                        if (!labelsHashMap.containsKey(statementLabel)){
                            labelsHashMap.put(statementLabel,currentProgramStatement.getMemoryAddress());
                            break;
                        }else{
                            this.errorList.add(new ErrorMessage(currentPSLine,0,"Error assigning label address, you cant have duplicate labels.", Constants.PROGRAM_LABEL_ADDRESSING_ERROR));
                            errorfound=true;
                            break;
                        }

                    }   if(labelStatementLine>programStatementList.get(statementListSize-1).getLine()){
                        if (!labelsHashMap.containsKey(statementLabel)){
                            labelsHashMap.put(statementLabel,programStatementList.get(statementListSize-1).getMemoryAddress()+0x4);
                            break;
                        }else{
                            this.errorList.add(new ErrorMessage(currentPSLine,0,"Error assigning program statement memory, the program exceeds the maximum address size.", Constants.PROGRAM_LABEL_ADDRESSING_ERROR));
                            errorfound=true;
                            break;
                        }
                    }
                }
            }
        }

    }


    public String ValidateShamt5(String shamt){
        String result;
        int numericShamt= Integer.decode(shamt);
        if(numericShamt>31){
            result=null;
        }
        else
            result=Integer.toString(numericShamt & 0b11111,2);
        return result;
    }


    private String[] ValidateImmediate(String imm){
        String[] result = new String[2];
        int numericImm= Integer.decode(imm);
        String imm32String=Integer.toString(numericImm & 0xFFFFFFFF,2);
        if(numericImm<=255){
            result[0]="0000";
            result[1]=Integer.toString(numericImm & 0xFF,2);
        }
        else if(imm32String.substring(2,25)=="000000000000000000000000"){
            result[0]="0001";
            result[1]=imm32String.substring(26,31)+imm32String.substring(0,1);
        }
        else if(imm32String.substring(4,27)=="000000000000000000000000"){
            result[0]="0010";
            result[1]=imm32String.substring(28,31)+imm32String.substring(0,3);
        }
        else if(imm32String.substring(6,29)=="000000000000000000000000"){
            result[0]="0011";
            result[1]=imm32String.substring(30,31)+imm32String.substring(0,3);
        }
        else if(imm32String.substring(8,31)=="000000000000000000000000"){
            result[0]="0100";
            result[1]=imm32String.substring(0,7);
        }
        else if(imm32String.substring(10,31)=="0000000000000000000000" & imm32String.substring(0,1)=="00"){
            result[0]="0101";
            result[1]=imm32String.substring(2,9);
        }
        else if(imm32String.substring(12,31)=="00000000000000000000" & imm32String.substring(0,3)=="0000"){
            result[0]="0110";
            result[1]=imm32String.substring(4,11);
        }
        else if(imm32String.substring(14,31)=="000000000000000000" & imm32String.substring(0,5)=="000000"){
            result[0]="0111";
            result[1]=imm32String.substring(6,13);
        }
        else if(imm32String.substring(16,31)=="0000000000000000" & imm32String.substring(0,7)=="00000000"){
            result[0]="1000";
            result[1]=imm32String.substring(8,15);
        }
        else if(imm32String.substring(18,31)=="00000000000000" & imm32String.substring(0,9)=="0000000000"){
            result[0]="1001";
            result[1]=imm32String.substring(10,17);
        }
        else if(imm32String.substring(20,31)=="000000000000" & imm32String.substring(0,11)=="000000000000"){
            result[0]="1010";
            result[1]=imm32String.substring(12,19);
        }
        else if(imm32String.substring(22,31)=="0000000000" & imm32String.substring(0,13)=="00000000000000"){
            result[0]="1011";
            result[1]=imm32String.substring(14,21);
        }
        else if(imm32String.substring(24,31)=="00000000" & imm32String.substring(0,15)=="0000000000000000"){
            result[0]="1100";
            result[1]=imm32String.substring(16,23);
        }
        else if(imm32String.substring(26,31)=="000000" & imm32String.substring(0,17)=="000000000000000000"){
            result[0]="1101";
            result[1]=imm32String.substring(18,25);
        }
        else if(imm32String.substring(28,31)=="0000" & imm32String.substring(0,19)=="00000000000000000000"){
            result[0]="1110";
            result[1]=imm32String.substring(20,27);
        }
        else if(imm32String.substring(30,31)=="00" & imm32String.substring(0,21)=="0000000000000000000000"){
            result[0]="1111";
            result[1]=imm32String.substring(22,29);
        }
        else
        {
            result= null;
        }
        return result;
    }


    private void GenerateOutputFile(List<ProgramStatement> program){
        Writer writer= null;
        List<ProgramStatement> programStatementList = program;
        try {
            writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("out.txt"), "utf-8")) ;

            for (ProgramStatement statement:
                 programStatementList) {

                writer.write(Integer.toString(statement.getMemoryAddress() & 0xFFFFFFFF,16)+"  "+ConvertBinStrToHexStr(statement.getEncodedInstruction().getBinaryInstruction())+"\n");
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            }
        }
    }


    private String ConvertBinStrToHexStr(String binaryStr){
        BigInteger b = new BigInteger(binaryStr, 2);
        return b.toString(16);
    }
}
