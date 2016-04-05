package mercury.arm.hardware;

/**
 * Created by Kevin on 05/04/2016.
 */
public class Register {

    private static final int NUMBER_OF_REGISTERS = 16;

    private Register[] registerArray;
    private String name;
    private String alternativeName;
    private int memoryAddress;
    private String value;

    public Register() {
        name = "";
        alternativeName = "";
        memoryAddress = 0;
        value = "";
    }

    public Register(String name, String alternativeName, int memoryAddress, String value) {
        this.name = name;
        this.alternativeName = alternativeName;
        this.memoryAddress = memoryAddress;
        this.value = value;
    }

    private void initializeRegisters() {
        registerArray = new Register[NUMBER_OF_REGISTERS];
        Register R0 = new Register();
        Register R1 = new Register();
        Register R2 = new Register();
        Register R3 = new Register();
        Register R4 = new Register();
        Register R5 = new Register();
        Register R6 = new Register();
        Register R7 = new Register();
        Register R8 = new Register();
        Register R9 = new Register();
        Register R10 = new Register();
        Register R11 = new Register();
        Register R12 = new Register();
        Register R13 = new Register();
        Register R14 = new Register();
        Register R15 = new Register();
    }

    public String getName() {
        return name;
    }

    public int getMemoryAddress() {
        return memoryAddress;
    }

    public void setMemoryAddress(int memoryAddress) {
        this.memoryAddress = memoryAddress;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
