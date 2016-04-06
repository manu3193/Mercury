package mercury.simulator;

import mercury.ARMProgram;
import mercury.ProgramStatement;
import mercury.arm.Constants;
import mercury.arm.hardware.Bank_Of_Registers;
import mercury.arm.hardware.CPSR;
import mercury.arm.hardware.Memory;
import mercury.arm.hardware.Register;

import java.math.BigInteger;
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

    public Simulation_Manager(Bank_Of_Registers bankOfRegisters, String[] memoryArray) {
        this.bankOfRegisters = bankOfRegisters;
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
            boolean isConditionValid = isConditionValid(currentStatement.getConditional());
            if (isConditionValid) {
                switch (regularMnemonic) {
                    case Constants.AND_MNEMONIC:
                        And(currentStatement);
                        break;
                    case Constants.EOR_MNEMONIC:
                        break;
                    case Constants.SUB_MNEMONIC:
                        Sub(currentStatement);
                        break;
                    case Constants.RSB_MNEMONIC:
                        break;
                    case Constants.ADD_MNEMONIC:
                        Add(currentStatement);
                        break;
                    case Constants.ADC_MNEMONIC:
                        break;
                    case Constants.SBC_MNEMONIC:
                        break;
                    case Constants.RSC_MNEMONIC:
                        break;
                    case Constants.CMP_MNEMONIC:
                        Cmp(currentStatement);
                        break;
                    case Constants.CMN_MNEMONIC:
                        break;
                    case Constants.ORR_MNEMONIC:
                        break;
                    case Constants.MOV_MNEMONIC:
                        Mov(currentStatement);
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
                        Str(currentStatement);
                        break;
                    case Constants.LDR_MNEMONIC:
                        Ldr(currentStatement);
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
    }


    private void And(ProgramStatement instruction) {
        String addrMode = instruction.getAddressingMode();
        Register op1 = bankOfRegisters.getRegisterByName(instruction.getRn());
        BigInteger op1val = new BigInteger(op1.getValue());
        Register result = bankOfRegisters.getRegisterByName(instruction.getRd());
        switch (addrMode) {

            case Constants.DP_NORMAL_ADDRESING:
                Register op2 = bankOfRegisters.getRegisterByName(instruction.getRm());
                BigInteger op2val = new BigInteger(op2.getValue());
                BigInteger res = op1val.and(op2val);
                String and = res.toString();
                result.setValue(and);
                if (instruction.HasSetFlagsSufix()) {
                    setFlags(res);
                }
                break;

            case Constants.DP_IMM_ADDRESSING:
                String immediate = instruction.getImm();
                BigInteger secondOperand = new BigInteger(immediate);
                BigInteger immResult = op1val.and(secondOperand);
                String strImmResult = immResult.toString();
                result.setValue(strImmResult);
                if (instruction.HasSetFlagsSufix()) {
                   setFlags(immResult);
                }
                break;
        }
    }

    private void Mov(ProgramStatement instruction) {
        String addrMode = instruction.getAddressingMode();
        Register result = bankOfRegisters.getRegisterByName(instruction.getRd());
        switch (addrMode) {
            case Constants.DP_CMV_REG_ADDRESSING:
                Register operand2 = bankOfRegisters.getRegisterByName(instruction.getRm());
                result.setValue(operand2.getValue());
                BigInteger res = new BigInteger(operand2.getValue());
                if (instruction.HasSetFlagsSufix()) {
                   setFlags(res);
                }
                break;
            case Constants.DP_CMV_IMM_ADDRESSING:
                String op2 = instruction.getImm();
                BigInteger r = new BigInteger(op2);
                result.setValue(op2);
                if (instruction.HasSetFlagsSufix()) {
                setFlags(r);
                }
                break;
        }
    }

    private void Cmp(ProgramStatement instruction){
        String addrMode = instruction.getAddressingMode();
        Register operand1 = bankOfRegisters.getRegisterByName(instruction.getRn());
        BigInteger val1= new BigInteger(operand1.getValue());
        switch (addrMode) {
            case Constants.DP_CMV_REG_ADDRESSING:
                Register operand2 = bankOfRegisters.getRegisterByName(instruction.getRm());
                BigInteger val2 = new BigInteger(operand2.getValue());
                BigInteger difference = val1.subtract(val2);
                setFlags(difference);
                break;
            case Constants.DP_CMV_IMM_ADDRESSING:
                BigInteger op2 = new BigInteger(instruction.getImm());
                BigInteger resta = val1.subtract(op2);
                setFlags(resta);
                break;
        }
    }

    private void setFlags(BigInteger result){
        if (result.signum() == -1) {
            cpsr.setNegative(true);
        }
        if (result.signum() == 0) {
            cpsr.setZero(true);
        } if (result.signum() == 1){
            cpsr.setCarry(true);
        } if(result.bitCount()>32){
            cpsr.setOverflow(true);

        }
    }
    private void Str(ProgramStatement instruction) {
        String addrMode = instruction.getAddressingMode();
        Register rn = bankOfRegisters.getRegisterByName(instruction.getRn());
        Register rd = bankOfRegisters.getRegisterByName(instruction.getRd());
        switch (addrMode) {
            case Constants.MEM_OFFSET_IMM_ADDRESSING:
                String immediate = instruction.getImm();
                BigInteger base = new BigInteger(rn.getValue());
                BigInteger offset = new BigInteger(immediate);
                BigInteger index = base.add(offset);
                memoryArray[index.intValue()] = rd.getValue();
                break;
            case Constants.MEM_OFFSET_REG_ADDRESSING:
                Register rm = bankOfRegisters.getRegisterByName(instruction.getRm());
                BigInteger source = new BigInteger(rn.getValue());
                BigInteger rmVal = new BigInteger(rm.getValue());
                BigInteger memPos = source.add(rmVal);
                memoryArray[memPos.intValue()] = rd.getValue();
                break;
            case Constants.MEM_REG_ADDRESSING:
                BigInteger baseReg = new BigInteger(rn.getValue());
                memoryArray[baseReg.intValue()] = rd.getValue();
                break;
        }
    }

    private void Ldr(ProgramStatement instruction) {
        String addrMode = instruction.getAddressingMode();
        Register rn = bankOfRegisters.getRegisterByName(instruction.getRn());
        Register rd = bankOfRegisters.getRegisterByName(instruction.getRd());
        switch (addrMode) {
            case Constants.MEM_OFFSET_IMM_ADDRESSING:
                String immediate = instruction.getImm();
                BigInteger base = new BigInteger(rn.getValue());
                BigInteger offset = new BigInteger(immediate);
                BigInteger index = base.add(offset);
                String val = memoryArray[index.intValue()];
                rd.setValue(val);
                break;
            case Constants.MEM_OFFSET_REG_ADDRESSING:
                Register rm = bankOfRegisters.getRegisterByName(instruction.getRm());
                BigInteger source = new BigInteger(rn.getValue());
                BigInteger rmVal = new BigInteger(rm.getValue());
                BigInteger memPos = source.add(rmVal);
                String result = memoryArray[memPos.intValue()];
                rd.setValue(result);
                break;
            case Constants.MEM_REG_ADDRESSING:
                BigInteger baseReg = new BigInteger(rn.getValue());
                String baseRegVal = memoryArray[baseReg.intValue()];
                rd.setValue(baseRegVal);
                break;
        }
    }

    private void Add(ProgramStatement instruction) {
        String addrMode = instruction.getAddressingMode();
        Register op1 = bankOfRegisters.getRegisterByName(instruction.getRn());
        BigInteger op1val = new BigInteger(op1.getValue());
        Register result = bankOfRegisters.getRegisterByName(instruction.getRd());
        switch (addrMode) {
            case Constants.DP_NORMAL_ADDRESING:
                Register op2 = bankOfRegisters.getRegisterByName(instruction.getRm());
                BigInteger op2val = new BigInteger(op2.getValue());
                BigInteger res = op1val.add(op2val);
                String add = res.toString();
                result.setValue(add);
                if (instruction.HasSetFlagsSufix()) {
                    setFlags(res);
                }
                break;

            case Constants.DP_IMM_ADDRESSING:
                String immediate = instruction.getImm();
                BigInteger secondOperand = new BigInteger(immediate);
                BigInteger immResult = op1val.add(secondOperand);
                String strImmResult = immResult.toString();
                result.setValue(strImmResult);
                if (instruction.HasSetFlagsSufix()) {
                    setFlags(immResult);
                }
                break;
        }
    }

    private void Sub(ProgramStatement instruction) {
        String addrMode = instruction.getAddressingMode();
        Register op1 = bankOfRegisters.getRegisterByName(instruction.getRn());
        BigInteger op1val = new BigInteger(op1.getValue());
        Register result = bankOfRegisters.getRegisterByName(instruction.getRd());
        switch (addrMode) {
            case Constants.DP_NORMAL_ADDRESING:
                Register op2 = bankOfRegisters.getRegisterByName(instruction.getRm());
                BigInteger op2val = new BigInteger(op2.getValue());
                BigInteger res = op1val.subtract(op2val);
                String sub = res.toString();
                result.setValue(sub);
                if (instruction.HasSetFlagsSufix()) {
                    setFlags(res);
                }
                break;
            case Constants.DP_IMM_ADDRESSING:
                String immediate = instruction.getImm();
                BigInteger secondOperand = new BigInteger(immediate);
                BigInteger immResult = op1val.subtract(secondOperand);
                String subtraction = immResult.toString();
                result.setValue(subtraction);
                if (instruction.HasSetFlagsSufix()) {
                   setFlags(immResult);
                }
                break;
        }
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

    private boolean isConditionValid(String conditionMnemonic) {
        boolean result;

        boolean zero = cpsr.getZero();
        boolean negative = cpsr.getNegative();
        boolean carry = cpsr.getCarry();
        boolean overflow = cpsr.getOverflow();

        switch (conditionMnemonic) {
            case "EQ":
                if (zero) {
                    result = true;
                } else {
                    result = false;
                }
                break;
            case "NE":
                if (!zero) {
                    result = true;
                } else {
                    result = false;
                }
                break;
            case "CS":
                if (carry) {
                    result = true;
                } else {
                    result = false;
                }
                break;
            case "HS":
                if (carry) {
                    result = true;
                } else {
                    result = false;
                }
                break;
            case "CC":
                if (!carry) {
                    result = true;
                } else {
                    result = false;
                }
                break;
            case "LO":
                if (!carry) {
                    result = true;
                } else {
                    result = false;
                }
                break;
            case "MI":
                if (negative) {
                    result = true;
                } else {
                    result = false;
                }
                break;
            case "PL":
                if (!negative) {
                    result = true;
                } else {
                    result = false;
                }
                break;
            case "VS":
                if (overflow) {
                    result = true;
                } else {
                    result = false;
                }
                break;
            case "VC":
                if (!overflow) {
                    result = true;
                } else {
                    result = false;
                }
                break;
            case "HI":
                if (!zero && carry) {
                    result = true;
                } else {
                    result = false;
                }
                break;
            case "LS":
                if (zero || !carry) {
                    result = true;
                } else {
                    result = false;
                }
                break;
            case "GE":
                if (negative == overflow) {
                    result = true;
                } else {
                    result = false;
                }
                break;
            case "LT":
                if (negative != overflow) {
                    result = true;
                } else {
                    result = false;
                }
                break;
            case "GT":
                if ((!zero) && (negative == overflow)) {
                    result = true;
                } else {
                    result = false;
                }
                break;
            case "LE":
                if (zero || (negative != overflow)) {
                    result = true;
                } else {
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
