package mercury.simulator;

import mercury.ARMProgram;
import mercury.ProgramStatement;
import mercury.arm.Constants;
import mercury.arm.hardware.Bank_Of_Registers;
import mercury.arm.hardware.CPSR;
import mercury.arm.hardware.Memory;
import java.util.List;

/**
 * Created by Kevin on 03/04/2016.
 */

public class Simulation_Manager {

    private Bank_Of_Registers bankOfRegisters;
    private String[] memoryArray;
    private CPSR cpsr;

    public Simulation_Manager() {
        bankOfRegisters = new Bank_Of_Registers();
        Memory memory = new Memory();
        memoryArray = memory.getMemoryArray();
        cpsr = new CPSR();
    }

    public Simulation_Manager(Bank_Of_Registers bankOfRegisters, String[] memoryArray){
        this.bankOfRegisters= bankOfRegisters;
        this.memoryArray = memoryArray;
    }

    public void Simulate(ARMProgram mainProgram) {

        List<ProgramStatement> instructionSet;
        instructionSet = mainProgram.getStatementList();
        ProgramStatement currentStatement;
        String regularMnemonic;

        for (int i = 0; i < instructionSet.size(); i++) {
            currentStatement = instructionSet.get(i);
            regularMnemonic = GetRegularMnemonic(currentStatement);
            switch (regularMnemonic) {
                case Constants.AND_MNEMONIC:
                    AndInstruction(currentStatement);
                    break;
                case Constants.EOR_MNEMONIC:
                    break;
                case Constants.SUB_MNEMONIC:
                    break;
                case Constants.RSB_MNEMONIC:
                    break;
                case Constants.ADD_MNEMONIC:
                    break;
                case Constants.ADC_MNEMONIC:
                    break;
                case Constants.SBC_MNEMONIC:
                    break;
                case Constants.RSC_MNEMONIC:
                    break;
                case Constants.CMP_MNEMONIC:
                    break;
                case Constants.CMN_MNEMONIC:
                    break;
                case Constants.ORR_MNEMONIC:
                    break;
                case Constants.MOV_MNEMONIC:
                    break;
                case Constants.LSL_MNEMONIC:
                    break;
                case Constants.ASR_MNEMONIC:
                    break;
                case Constants.RRX_MNEMONIC:
                    break;
                case Constants.ROR_MNEMONIC:
                    break;
                case Constants.BIC_MNEMONIC:
                    break;
                case Constants.MVN_MNEMONIC:
                    break;
                case Constants.MUL_MNEMONIC:
                    break;
                case Constants.MLA_MNEMONIC:
                    break;
                case Constants.STR_MNEMONIC:
                    break;
                case Constants.LDR_MNEMONIC:
                    break;
                case Constants.STRB_MNEMONIC:
                    break;
                case Constants.LDRB_MNEMONIC:
                    break;
                case Constants.B_MNEMONIC:
                    break;
            }
        }
    }

    private void AndInstruction(ProgramStatement instruction) {

    }


    private String GetRegularMnemonic(ProgramStatement instruction) {
        String instrMnemonic = instruction.getMnemonic();
        int instrMnemonicLength = instrMnemonic.length();
        if (instruction.HasSetFlagsSufix() & !instruction.HasConditionalSufix()) {
            instrMnemonic = instrMnemonic.substring(0, instrMnemonicLength - 1);
        } else if (!instruction.HasSetFlagsSufix() & instruction.HasConditionalSufix()) {
            instrMnemonic = instrMnemonic.substring(0, instrMnemonicLength - 2);
        } else if (instruction.HasConditionalSufix() & instruction.HasSetFlagsSufix()) {
            instrMnemonic = instrMnemonic.substring(0, instrMnemonicLength - 3);
        }
        return instrMnemonic;
    }

    private boolean isConditionValid(String conditionMnemonic){
        boolean result;

        boolean zero = cpsr.getZero();
        boolean negative = cpsr.getNegative();
        boolean carry = cpsr.getCarry();
        boolean overflow = cpsr.getOverflow();

        switch(conditionMnemonic){
            case "EQ":
                if(zero){
                    result = true;
                }else{
                    result = false;
                }
                break;
            case "NE":
                if(!zero){
                    result = true;
                }else{
                    result=false;
                }
                break;
            case "CS":
                if(carry){
                    result = true;
                }else{
                    result = false;
                }
                break;
            case "HS":
                if(carry){
                    result = true;
                }else{
                    result = false;
                }
                break;
            case "CC":
                if(!carry){
                    result = true;
                }else{
                    result = false;
                }
                break;
            case "LO":
                if(!carry){
                    result = true;
                }else{
                    result = false;
                }
                break;
            case "MI":
                if(negative){
                    result = true;
                }else{
                    result = false;
                }
                break;
            case "PL":
                if(!negative){
                    result = true;
                }else{
                    result = false;
                }
                break;
            case "VS":
                if(overflow){
                    result = true;
                }else{
                    result = false;
                }
                break;
            case "VC":
                if(!overflow){
                    result = true;
                }else{
                    result = false;
                }
                break;
            case "HI":
                if(!zero && carry){
                    result = true;
                }else{
                    result = false;
                }
                break;
            case "LS":
                if(zero || !carry){
                    result = true;
                }else{
                    result = false;
                }
                break;
            case "GE":
                if(negative==overflow){
                    result = true;
                }else{
                    result = false;
                }
                break;
            case "LT":
                if(negative!=overflow){
                    result = true;
                }else{
                    result = false;
                }
                break;
            case "GT":
                if((!zero) &&(negative==overflow)){
                    result = true;
                }else{
                    result = false;
                }
                break;
            case "LE":
                if(zero || (negative!=overflow)){
                    result = true;
                }else{
                    result = false;
                }
                break;
            case "AL":
                result = true;
                break;
            default:
                result = true;
        }
        return result;
    }
}
