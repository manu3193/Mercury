package mercury.arm.hardware;

/**
 * Created by Kevin on 05/04/2016.
 */
public class Bank_Of_Registers {

    private static final int NUMBER_OF_REGISTERS = 16;

    private Register[] registerArray;

    public Bank_Of_Registers() {
        initializeRegisters();
    }

    public Bank_Of_Registers(Register[] bankOfRegisters) {
        registerArray = bankOfRegisters;
    }

    private void initializeRegisters() {
        registerArray = new Register[NUMBER_OF_REGISTERS];
        Register R0 = new Register("R0", "", "");
        Register R1 = new Register("R1", "", "");
        Register R2 = new Register("R2", "", "");
        Register R3 = new Register("R3", "", "");
        Register R4 = new Register("R4", "", "");
        Register R5 = new Register("R5", "", "");
        Register R6 = new Register("R6", "", "");
        Register R7 = new Register("R7", "", "");
        Register R8 = new Register("R8", "", "");
        Register R9 = new Register("R9", "", "");
        Register R10 = new Register("R10", "", "");
        Register R11 = new Register("R11", "", "");
        Register R12 = new Register("R12", "", "");
        Register R13 = new Register("R13", "", "");
        Register R14 = new Register("R14", "LR", "");
        Register R15 = new Register("R15", "PC", "");
        registerArray[0] = R0;
        registerArray[1] = R1;
        registerArray[2] = R2;
        registerArray[3] = R3;
        registerArray[4] = R4;
        registerArray[5] = R5;
        registerArray[6] = R6;
        registerArray[7] = R7;
        registerArray[8] = R8;
        registerArray[9] = R9;
        registerArray[10] = R10;
        registerArray[11] = R11;
        registerArray[12] = R12;
        registerArray[13] = R13;
        registerArray[14] = R14;
        registerArray[15] = R15;
    }

    public Register[] getRegisterArray() {
        return registerArray;
    }

    public Register getRegisterByName(String regName) {
        Register result = new Register();
        for (Register register : registerArray) {
            if (regName.equals(register.getName())) ;
            result = register;
        }
        return result;
    }


    public void setRegisterArray(Register[] registerArray) {
        this.registerArray = registerArray;
    }
}
